package dev.seba.apiaref.dto.response;

import dev.seba.apiaref.dto.PostMetric;
import lombok.Data;

@Data
public class PostReportResponseDto {
    Integer count;
    PostMetric postMetric;

}
