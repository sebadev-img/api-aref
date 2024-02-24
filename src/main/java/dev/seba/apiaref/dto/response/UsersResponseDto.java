package dev.seba.apiaref.dto.response;

import dev.seba.apiaref.model.User;
import lombok.Data;

import java.util.List;

@Data
public class UsersResponseDto {
    int count;
    List<User> results;
}
