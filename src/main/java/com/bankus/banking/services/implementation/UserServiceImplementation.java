package com.bankus.banking.services.implementation;

import com.bankus.banking.dto.AccountDto;
import com.bankus.banking.dto.UserDto;
import com.bankus.banking.models.Account;
import com.bankus.banking.models.User;
import com.bankus.banking.repositories.AccountRepository;
import com.bankus.banking.repositories.UserRepository;
import com.bankus.banking.services.AccountService;
import com.bankus.banking.services.UserService;
import com.bankus.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {


    //@Autowired
    private final UserRepository repository;
    private final ObjectsValidator<UserDto> validator;
    private final AccountService accountService;

    /*public UserServiceImplementation(UserRepository repository){
        this.repository = repository;
    }*/
    @Override
    public Integer save(UserDto dto) {
        validator.validate(dto);

        // before persist we need to transform UserDto into a User Object
        User user = UserDto.toEntity(dto);

        // data persist
        User savedUser = repository.save(user);
        return savedUser.getId();
    }

    @Override
    public List<UserDto> findAll() {
        return repository.findAll()
                .stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Integer id) {
        return repository.findById(id)
                .map(UserDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No user was found with provided ID" + id));
    }

    @Override
    public void delete(Integer id) {
        // todo check before delete
        repository.deleteById(id);
    }

    @Override
    public Integer validateAccount(Integer id) {
        User user = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User not found for user account validation with id : " + id));
        user.setActive(true);

        // Create a bank account for this user
        AccountDto account = AccountDto.builder()
                .user(UserDto.fromEntity(user))
                .build();
        accountService.save(account);
        repository.save(user);
        return user.getId();
    }

    @Override
    public Integer invalidateAccount(Integer id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found for user account deletion with id : " + id));
        user.setActive(false);

        repository.save(user);
        return user.getId();
    }
}
