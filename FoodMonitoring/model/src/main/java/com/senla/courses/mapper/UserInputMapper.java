package com.senla.courses.mapper;

import com.senla.courses.dto.UserDtoInput;
import com.senla.courses.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserInputMapper {

    UserDtoInput userToUserDtoInput(User user);

    User userDtoInputToUser(UserDtoInput userDtoInput);

    List<UserDtoInput> userListToUserDtoInputList(List<User> users);

    List<User> userDtoInputListToUserList(List<UserDtoInput> userDtoInputs);

}
