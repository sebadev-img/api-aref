package dev.seba.apiaref.service.Impl;

import dev.seba.apiaref.dto.response.PostReportResponseDto;
import dev.seba.apiaref.service.IReportService;
import org.springframework.stereotype.Service;

@Service
public class ReportService implements IReportService {
    @Override
    public PostReportResponseDto getTop10PostByComment() {
        return null;
    }
}
