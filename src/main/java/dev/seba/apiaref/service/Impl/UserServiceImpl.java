package dev.seba.apiaref.service.Impl;

import dev.seba.apiaref.model.User;
import dev.seba.apiaref.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }
}
