package dev.seba.apiaref.controller;

import dev.seba.apiaref.dto.PostMetric;
import dev.seba.apiaref.dto.response.PostReportResponseDto;
import dev.seba.apiaref.service.Impl.ReportServiceImpl;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@WebMvcTest(controllers = ReportController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class ReportControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReportServiceImpl reportService;

    @Test
    void testThatGetTop10PostReturnsReportDto() throws Exception {
        List<PostMetric> postMetric = List.of(
                new PostMetric(1,5),
                new PostMetric(2,5),
                new PostMetric(3,5)
        );
        PostReportResponseDto reportDto = new PostReportResponseDto();
        reportDto.setCount(3);
        reportDto.setResults(postMetric);
        when(reportService.getTop10PostByComment()).thenReturn(reportDto);
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/reports/posts/top")
                .contentType(APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.count", CoreMatchers.is(postMetric.size())));
    }
}