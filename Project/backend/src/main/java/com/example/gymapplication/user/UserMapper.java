package com.example.gymapplication.user;

import com.example.gymapplication.user.model.User;
import com.example.gymapplication.user.model.dto.UserDTO;
import com.example.gymapplication.user.model.dto.UserListDTO;
import com.example.gymapplication.user.model.dto.UserMinimalDTO;
import org.mapstruct.*;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings({
            @Mapping(target = "username", source = "user.username")
    })
    UserMinimalDTO userMinimalFromUser(User user);

    @Mappings({
            @Mapping(target = "username", source = "user.username"),
            @Mapping(target = "roles", ignore = true)
    })
    UserListDTO userListDtoFromUser(User user);

    @Mappings({
            @Mapping(target = "role", ignore = true),
            @Mapping(target = "trainings", ignore = true),
            @Mapping(target = "regularTrainings", ignore = true)
    })
    UserDTO toDto(User user);

    @Mappings({
            @Mapping(target = "roles", ignore = true),
            @Mapping(target = "trainings", ignore = true),
            @Mapping(target = "regularTrainings", ignore = true)
    })
    User fromDto(UserDTO userDTO);


    @AfterMapping
    default void populateRoles(User user, @MappingTarget UserListDTO userListDTO) {
        userListDTO.setRoles(user.getRoles().stream().map(role -> role.getName().name()).collect(Collectors.toSet()));
    }

    @AfterMapping
    default void populateRegularTrainings(User user, @MappingTarget UserDTO userDTO) {
        userDTO.setRegularTrainings(user.getTrainings().stream().map(regularTraining -> regularTraining.getTitle()).collect(Collectors.toList()));
    }

    @AfterMapping
    default void populateTrainings(User user, @MappingTarget UserDTO userDTO) {
        userDTO.setTrainings(user.getTrainings().stream().map(training -> training.getTitle()).collect(Collectors.toList()));
    }

}
