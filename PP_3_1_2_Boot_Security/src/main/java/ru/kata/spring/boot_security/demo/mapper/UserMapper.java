package ru.kata.spring.boot_security.demo.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.record.RequestRecordEdit;
import ru.kata.spring.boot_security.demo.record.RequestRecordReg;
import ru.kata.spring.boot_security.demo.record.ResponseRecord;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapRoles")
    ResponseRecord response(User user);
    User requestReg(RequestRecordReg requestRecordReg);

    @Named("mapRoles")
    default Set<String> mapRoles(Set<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .map(role -> role.replace("ROLE_", ""))
                .collect(Collectors.toSet());
    }
    default User requestEdit(RequestRecordEdit requestRecordEdit, User user) {
        if (requestRecordEdit.email() != null && !requestRecordEdit.email().isBlank()) {
            user.setEmail(requestRecordEdit.email());
        }
        if (requestRecordEdit.name() != null && !requestRecordEdit.name().isBlank()) {
            user.setName(requestRecordEdit.name());
        }
        if (requestRecordEdit.secondName() != null && !requestRecordEdit.secondName().isBlank()) {
            user.setSecondName(requestRecordEdit.secondName());
        }
        if (requestRecordEdit.age() != null) {
            user.setAge(requestRecordEdit.age());
        }
        if (requestRecordEdit.eyeColor() != null && !requestRecordEdit.eyeColor().isBlank()) {
            user.setEyeColor(requestRecordEdit.eyeColor());
        }
        return user;
    }
}


