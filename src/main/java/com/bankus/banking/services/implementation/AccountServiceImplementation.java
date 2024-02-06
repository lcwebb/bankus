package com.bankus.banking.services.implementation;

import com.bankus.banking.dto.AccountDto;
import com.bankus.banking.exceptions.OperationNonPermittedException;
import com.bankus.banking.models.Account;
import com.bankus.banking.repositories.AccountRepository;
import com.bankus.banking.services.AccountService;
import com.bankus.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImplementation implements AccountService  {


    private final AccountRepository repository;
    private final ObjectsValidator<AccountDto> validator;


    @Override
    public Integer save(AccountDto dto) {
        // block account update -> iban cannot be changed
        /*if(dto.getId() != null) {
            throw new OperationNonPermittedException(
                "Account cannot be updated",
                "Save account",
                "Account",
                "Update not permitted"
            );
        }*/
        validator.validate(dto);
        Account account = AccountDto.toEntity(dto);
        boolean userHasalreadyAnAccount = repository.findByUserId(account.getUser().getId()).isPresent();

        if (userHasalreadyAnAccount) {
            throw new OperationNonPermittedException(
                    "The selected user has already an active account",
                    "Create account",
                    "Account service",
                    "Account creation"
            );
        }

        // generate random IBAN when creating new account, else do not update the iban
        if(dto.getId() == null) {
            account.setIban(generateRandomIban());
        }
        return repository.save(account).getId();
    }

    @Override
    public List<AccountDto> findAll() {
        return repository.findAll()
            .stream()
            .map(AccountDto::fromEntity)
            .collect(Collectors.toList());
    }

    @Override
    public AccountDto findById(Integer id) {
        return repository.findById(id)
            .map(AccountDto::fromEntity)
            .orElseThrow(() -> new EntityNotFoundException("No account found with this id : " + id));
    }

    @Override
    public void delete(Integer id) {
        // todo check delete account
        repository.deleteById(id);
    }

    private String generateRandomIban() {
        // generate an iban
        String iban = Iban.random(CountryCode.FR).toFormattedString();

        // check if iban already exists
        boolean ibanExists  = repository.findByIban(iban).isPresent();

        // if exists -> generate new random iban
        if(ibanExists) {
            generateRandomIban();
        }

        // if not exist return generated iban
        return iban;

    }
}
