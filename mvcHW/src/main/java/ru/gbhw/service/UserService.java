package ru.gbhw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gbhw.converters.UserConverter;
import ru.gbhw.dto.UserDto;
import ru.gbhw.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    public final UserConverter converter;
    public String getAllUsers() {
        List<UserDto> users = repository.findAll()
                .stream()
                .map(converter::fromEntity)
                .collect(Collectors.toList());
        StringBuilder builder = new StringBuilder();
        for (UserDto user: users) {
            builder.append("<td>id: "+user.getId()+" name: "+user.getUsername()+"</td><br>");
        }
        return builder.toString();
    }
}
