package com.bankus.banking.Controllers;

import com.bankus.banking.dto.AccountDto;
import com.bankus.banking.dto.ContactDto;
import com.bankus.banking.services.AccountService;
import com.bankus.banking.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contact")
@RequiredArgsConstructor
public class ContactController {

    private ContactService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(
            @RequestBody ContactDto contactDto
    ) {
        return ResponseEntity.ok(service.save(contactDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<ContactDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{contact-id}")
    public ResponseEntity<ContactDto> findById(
            @PathVariable("contact-id") Integer contactId
    ) {
        return ResponseEntity.ok(service.findById(contactId));
    }


    @GetMapping("/user/{user-id}")
    public ResponseEntity<List<ContactDto>> findByUserId(
            @PathVariable("user-id") Integer userId
    ) {
        return ResponseEntity.ok(service.findAllByUserId(userId));
    }

    @DeleteMapping("/{contact-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("contact-id") Integer contactId
    ) {
        service.delete(contactId);
        return ResponseEntity.accepted().build();
    }

}
