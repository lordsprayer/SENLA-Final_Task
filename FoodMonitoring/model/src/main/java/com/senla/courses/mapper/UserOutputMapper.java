package com.senla.courses.mapper;

import com.senla.courses.dto.UserDtoInput;
import com.senla.courses.dto.UserDtoOutput;
import com.senla.courses.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserOutputMapper {

    UserDtoOutput userToUserDtoOutput(User user);

    User userDtoOutputToUser(UserDtoInput userDtoOutput);

    List<UserDtoOutput> userListToUserDtoOutputList(List<User> users);

    List<User> userDtoOutputListToUserList(List<UserDtoOutput> userDtoOutputs);
}
