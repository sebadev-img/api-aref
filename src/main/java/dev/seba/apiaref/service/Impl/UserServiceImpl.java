package dev.seba.apiaref.service.Impl;

import dev.seba.apiaref.client.UserRestClient;
import dev.seba.apiaref.dto.response.UsersResponseDto;
import dev.seba.apiaref.exception.UserNotFoundException;
import dev.seba.apiaref.model.User;
import dev.seba.apiaref.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    public UsersResponseDto getUsersByCity(String city) {
        List<User> users= userClient.findByCity(city);
        UsersResponseDto usersDto = new UsersResponseDto();
        usersDto.setCount(users.size());
        usersDto.setResults(users);
        return usersDto;
    }

    @Override
    public User searchUser(String username, String email) {
        User userByUsername = null;
        User userByEmail = null;
        if(username != null){
            userByUsername = getUserByUsername(username);
        }
        if(email != null){
            userByEmail = getUserByEmail(email);
        }
        if(userByUsername != null && userByEmail != null){
            if(Objects.equals(userByUsername.id(), userByEmail.id())){
                return userByUsername;
            }
        }
        if(userByUsername != null){
            return userByUsername;
        }
        else return userByEmail;
    }

    public User getUserByUsername(String username) {
        List<User> users = userClient.findByUsername(username);
        Optional<User> user = users.stream().findFirst();
        if(user.isEmpty()){
            throw new UserNotFoundException("User Not Found");
        }
        return user.orElse(null);
    }


    public User getUserByEmail(String email) {
        List<User> users = userClient.findByEmail(email);
        Optional<User> user = users.stream().findFirst();
        if(user.isEmpty()){
            throw new UserNotFoundException("User Not Found");
        }
        return user.orElse(null);
    }
}
