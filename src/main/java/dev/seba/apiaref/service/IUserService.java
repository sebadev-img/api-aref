package dev.seba.apiaref.service;

import dev.seba.apiaref.dto.response.UsersResponseDto;
import dev.seba.apiaref.model.User;

public interface IUserService {
    UsersResponseDto getAll();
    User getUserById(int id);
    UsersResponseDto getUsersByCity(String city);
    User searchUser(String username,String email);
}
