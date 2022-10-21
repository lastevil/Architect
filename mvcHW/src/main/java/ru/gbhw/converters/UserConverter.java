package ru.gbhw.converters;

import org.springframework.stereotype.Component;
import ru.gbhw.dto.UserDto;
import ru.gbhw.entity.User;

@Component
public class UserConverter {
    public UserDto fromEntity(User user){
        return new UserDto(user.getId(), user.getUsername());
    }
}
