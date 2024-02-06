package com.bankus.banking.services.implementation;

import com.bankus.banking.dto.ContactDto;
import com.bankus.banking.models.Contact;
import com.bankus.banking.repositories.ContactRepository;
import com.bankus.banking.services.ContactService;
import com.bankus.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceImplementation implements ContactService {

    private final ContactRepository repository;
    private final ObjectsValidator<ContactDto> validator;
    @Override
    public Integer save(ContactDto dto) {
        validator.validate(dto);
        Contact contact = ContactDto.toEntity(dto);
        return repository.save(contact).getId();
    }

    @Override
    public List<ContactDto> findAll() {
        return repository.findAll()
            .stream()
            .map(ContactDto::fromEntity)
            .collect(Collectors.toList());
    }

    @Override
    public ContactDto findById(Integer id) {
        return repository.findById(id)
            .map(ContactDto::fromEntity)
            .orElseThrow(() -> new EntityNotFoundException("No contact was found with id : " + id));
    }

    @Override
    public void delete(Integer id) {
        //todo check delete
        repository.deleteById(id);
    }

    @Override
    public List<ContactDto> findAllByUserId(Integer userId) {
        return repository.findAllByUserId(userId)
                .stream()
                .map(ContactDto::fromEntity)
                .collect(Collectors.toList());
    }
}
