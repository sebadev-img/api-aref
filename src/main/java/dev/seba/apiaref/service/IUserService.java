package dev.seba.apiaref.service;

import dev.seba.apiaref.dto.response.UsersResponseDto;
import dev.seba.apiaref.model.User;

public interface IUserService {
    UsersResponseDto getAll();
    User getUserById(int id);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    UsersResponseDto getUsersByCity(String city);
}
