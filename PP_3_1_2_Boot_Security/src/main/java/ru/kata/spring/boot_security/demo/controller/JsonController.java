package ru.kata.spring.boot_security.demo.controller;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.mapper.UserMapper;
import ru.kata.spring.boot_security.demo.record.RequestRecordEdit;
import ru.kata.spring.boot_security.demo.record.RequestRecordReg;
import ru.kata.spring.boot_security.demo.record.ResponseRecord;
import ru.kata.spring.boot_security.demo.security.UserDetailsImp;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class JsonController {
    private final UserService userService;
    private final UserMapper userMapper;

    public JsonController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<List<ResponseRecord>> getUsers() {
        return ResponseEntity.ok(userService.getUsers().stream()
                .map(userMapper::response)
                .collect(Collectors.toList()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users/{id}")
    public ResponseEntity<ResponseRecord> getUser(@PathVariable Integer id) {
        return ResponseEntity.ok(userMapper.response(userService.getUserById(id)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/users/{id}")
    public ResponseEntity<HttpStatus> edit(@RequestBody @Valid RequestRecordEdit requestRecordEdit,
                                           BindingResult bindingResult,
                                           @PathVariable Integer id) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException(errorBindingResult(bindingResult));
        }

        userService.update(requestRecordEdit, id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping({"/users", "/registration"})
    public ResponseEntity<HttpStatus> createUser(@RequestBody @Valid RequestRecordReg requestRecordReg,
                                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException(errorBindingResult(bindingResult));
        }

        userService.add(requestRecordReg);
        System.out.println("controller ok");
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public ResponseEntity<ResponseRecord> getUser(@AuthenticationPrincipal UserDetailsImp userDetailsImp) {
        return ResponseEntity.ok(userMapper.response(userDetailsImp.getUser()));
    }

    static String errorBindingResult(BindingResult bindingResult) {
        StringBuilder errorMsg = new StringBuilder();
        List<FieldError> errors = bindingResult.getFieldErrors();
        for(FieldError error : errors) {
            errorMsg.append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage())
                    .append(";");
        }
        return errorMsg.toString();
    }


}
