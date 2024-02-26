package dev.seba.apiaref.service;

import dev.seba.apiaref.dto.response.PostReportResponseDto;

public interface IReportService {
    PostReportResponseDto getTop10PostByComment();
}
