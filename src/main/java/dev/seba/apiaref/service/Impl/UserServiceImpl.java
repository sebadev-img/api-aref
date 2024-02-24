package dev.seba.apiaref.service.Impl;

import dev.seba.apiaref.client.UserRestClient;
import dev.seba.apiaref.dto.response.UsersResponseDto;
import dev.seba.apiaref.model.User;
import dev.seba.apiaref.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRestClient userClient;

    public UserServiceImpl(UserRestClient userClient){
        this.userClient = userClient;
    }

    @Override
    public UsersResponseDto getAll() {
        List<User> users= userClient.findAll();
        UsersResponseDto usersDto = new UsersResponseDto();
        usersDto.setCount(users.size());
        usersDto.setResults(users);
        return usersDto;
    }

    @Override
    public User getUserById(int id) {
        return userClient.findById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userClient.findByUsername(username);
    }

    @Override
    public User getUserByEmail(String email) {
        return userClient.findByEmail(email);
    }

    @Override
    public UsersResponseDto getUsersByCity(String city) {
        List<User> users= userClient.findByCity(city);
        UsersResponseDto usersDto = new UsersResponseDto();
        usersDto.setCount(users.size());
        usersDto.setResults(users);
        return usersDto;
    }
}
