package com.map.nudonado.trace.presentation;

import com.map.nudonado.auth.dto.LoginMember;
import com.map.nudonado.auth.presentation.AuthenticationPrincipal;
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

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class TraceController {

    private final TraceService traceService;

    @PostMapping("/{boothId}/traces")
    public ResponseEntity<TraceCreateResponse> save(
            @AuthenticationPrincipal LoginMember loginMember,
            @PathVariable Long boothId,
            @Valid @RequestBody final TraceCreateRequest request
    ) {
        TraceCreateResponse traceCreateResponse = traceService.save(loginMember.getId(), boothId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(traceCreateResponse);

    }


    @GetMapping("/{boothId}/traces")
    public ResponseEntity<List<IntegrationTrace>> findBoothTraces(
            @AuthenticationPrincipal LoginMember loginMember,
            @PathVariable Long boothId
    ) {
        List<IntegrationTrace> boothTraces = traceService.findBoothTraces(loginMember.getId(), boothId);
        return ResponseEntity.ok().body(boothTraces);
    }

}
