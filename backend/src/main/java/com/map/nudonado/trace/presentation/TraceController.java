package com.map.nudonado.trace.presentation;

import com.map.nudonado.trace.application.TraceService;
import com.map.nudonado.trace.dto.request.TraceCreateRequest;
import com.map.nudonado.trace.dto.response.IntegrationTrace;
import com.map.nudonado.trace.dto.response.TraceCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/traces")
@RestController
@RequiredArgsConstructor
public class TraceController {

    private final TraceService traceService;

    @PostMapping("/{memberId}/{boothId}")
    public ResponseEntity<TraceCreateResponse> save(
            @PathVariable Long memberId,
            @PathVariable Long boothId,
            @Valid @RequestBody final TraceCreateRequest request
    ) {
        TraceCreateResponse traceCreateResponse = traceService.save(memberId, boothId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(traceCreateResponse);

    }


    @GetMapping("/{memberId}/{boothId}")
    public ResponseEntity<List<IntegrationTrace>> findBoothTraces(
            @PathVariable Long memberId,
            @PathVariable Long boothId
    ) {
        List<IntegrationTrace> boothTraces = traceService.findBoothTraces(memberId, boothId);
        return ResponseEntity.ok().body(boothTraces);
    }

}
