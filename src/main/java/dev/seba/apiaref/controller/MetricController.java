package dev.seba.apiaref.controller;

import dev.seba.apiaref.dto.response.UserMetricsResponseDto;
import dev.seba.apiaref.service.IMetricService;
import dev.seba.apiaref.service.Impl.MetricServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/metrics")
public class MetricController {

    private final IMetricService metricService;

    public MetricController(MetricServiceImpl metricService){
        this.metricService = metricService;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserMetricsResponseDto> getUserMetric(@PathVariable Integer id){
        UserMetricsResponseDto response = metricService.getUserMetrics(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
