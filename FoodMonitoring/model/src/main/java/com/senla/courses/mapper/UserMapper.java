package com.senla.courses.mapper;

import com.senla.courses.dto.UserDto;
import com.senla.courses.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);

    List<UserDto> userListToUserDtoList(List<User> users);

    List<User> userDtoListToUserList(List<UserDto> userDtos);

}
