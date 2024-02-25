package dev.seba.apiaref.controller;

import dev.seba.apiaref.dto.response.PostReportResponseDto;
import dev.seba.apiaref.dto.response.PostsResponseDto;
import dev.seba.apiaref.service.IReportService;
import dev.seba.apiaref.service.Impl.ReportServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/reports")
public class ReportController {

    private final IReportService reportService;

    public ReportController(ReportServiceImpl reportService){
        this.reportService = reportService;
    }

    @GetMapping("/posts/top")
    public ResponseEntity<PostReportResponseDto> getTop10Post(){
        PostReportResponseDto response = reportService.getTop10PostByComment();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
