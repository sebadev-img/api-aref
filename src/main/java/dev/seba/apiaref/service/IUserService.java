package dev.seba.apiaref.service;

import dev.seba.apiaref.model.User;

public interface IUserService {
    User getUserById(int id);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
}
