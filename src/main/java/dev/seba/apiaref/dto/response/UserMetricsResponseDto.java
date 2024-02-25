package dev.seba.apiaref.dto.response;

import lombok.Data;

@Data
public class UserMetricsResponseDto {
    Integer userId;
    Integer postCount;
    String userInfoPath;
    String userPostsPath;
}
