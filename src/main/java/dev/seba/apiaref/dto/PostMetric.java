package dev.seba.apiaref.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostMetric {
    private Integer postId;
    private Integer commentCount;
}
