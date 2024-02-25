package dev.seba.apiaref.dto.response;

import dev.seba.apiaref.dto.PostMetric;
import lombok.Data;

import java.util.List;

@Data
public class PostReportResponseDto {
    private Integer count;
    private List<PostMetric> results;
}
