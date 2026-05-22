package ru.kata.spring.boot_security.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.dto.RequestUserDtoEdit;
import ru.kata.spring.boot_security.demo.dto.RequestUserDtoRegistration;
import ru.kata.spring.boot_security.demo.dto.ResponseUserDto;
import ru.kata.spring.boot_security.demo.dto.ResponseUserDtoForAdmin;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ResponseUserDto responseUserToUserDto(User user) {
        ResponseUserDto responseUserDto = modelMapper.map(user, ResponseUserDto.class);
        responseUserDto.setRoles(user.getRoles().stream().map(Role::getName).map(role -> role.replace("ROLE_", "")).collect(Collectors.toSet()));
        return responseUserDto;
    }

    public User requestUserDtoToUserReg(RequestUserDtoRegistration requestUserDto) {
        return modelMapper.map(requestUserDto, User.class);
    }

    public User requestUserDtoToUserUpdate(RequestUserDtoEdit requestUserDtoEdit, User user) {
        if (requestUserDtoEdit.getEmail() != null && !requestUserDtoEdit.getEmail().isBlank()) {
            user.setEmail(requestUserDtoEdit.getEmail());
        }
        if (requestUserDtoEdit.getName() != null && !requestUserDtoEdit.getName().isBlank()) {
            user.setName(requestUserDtoEdit.getName());
        }
        if (requestUserDtoEdit.getSecondName() != null && !requestUserDtoEdit.getSecondName().isBlank()) {
            user.setSecondName(requestUserDtoEdit.getSecondName());
        }
        if (requestUserDtoEdit.getAge() != null) {
            user.setAge(requestUserDtoEdit.getAge());
        }
        if (requestUserDtoEdit.getEyeColor() != null && !requestUserDtoEdit.getEyeColor().isBlank()) {
            user.setEyeColor(requestUserDtoEdit.getEyeColor());
        }
        return user;
    }

    public ResponseUserDtoForAdmin userToResponseUserDtoForAdmin(User user) {
        ResponseUserDtoForAdmin responseUserDtoForAdmin = modelMapper.map(user, ResponseUserDtoForAdmin.class);
        responseUserDtoForAdmin.setRoles(user.getRoles().stream().map(Role::getName).map(role -> role.replace("ROLE_", "")).collect(Collectors.toSet()));
        return responseUserDtoForAdmin;
    }
}


