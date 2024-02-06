package com.bankus.banking.Controllers;

import com.bankus.banking.dto.UserDto;
import com.bankus.banking.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(
            // serialize / desarialize request
            @RequestBody UserDto userDto
    ) {
        return ResponseEntity.ok(service.save(userDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<UserDto> findById(
            @PathVariable("user-id") Integer userId
    ) {
        return ResponseEntity.ok(service.findById(userId));
    }

    // action of altering -> use Patch
    @PatchMapping("/validate/{user-id}")
    public ResponseEntity<Integer> validateAccount(
            @PathVariable("user-id") Integer userId
    ) {
        return ResponseEntity.ok(service.validateAccount(userId));
    }

    @PatchMapping("/invalidate/{user-id}")
    public ResponseEntity<Integer> invalidateAccount(
            @PathVariable("user-id") Integer userId
    ) {
        return ResponseEntity.ok(service.invalidateAccount(userId));
    }

    @DeleteMapping("/delete/{user-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("user-id") Integer userId
    ) {
        service.delete(userId);
        return ResponseEntity.accepted().build();
    }

    /*@PostMapping("/")
    public void save() {}

    @GetMapping("/")
    public void findAll() {}

    @GetMapping("{idUser}")
    public void findById(
        @PathVariable("idUser") Integer id
    ) {}

    @GetMapping("/find")
    public void search(
        @RequestParam(required = true ) String firstname,
        @RequestParam(required = true ) String lastname,
        @RequestParam(required = true ) int age
    ) {}

    @GetMapping("/find/{uni_criteria}")
    public void searchUnique(
            @PathVariable("uni_criteria") String criteria
    ){}*/

}
