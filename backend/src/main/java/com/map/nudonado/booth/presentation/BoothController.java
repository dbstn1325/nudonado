package com.map.nudonado.booth.presentation;


import com.map.nudonado.booth.application.BoothService;
import com.map.nudonado.booth.domain.BoothDetail;
import com.map.nudonado.booth.dto.response.BoothIdResponse;
import com.map.nudonado.booth.dto.request.BoothCreateRequest;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.io.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/booths")
@RestController
@RequiredArgsConstructor
public class BoothController {

    private final BoothService boothService;

    @PostMapping("/{memberId}")
    public ResponseEntity<BoothIdResponse> save(
            @PathVariable Long memberId,
            @Valid @RequestBody BoothCreateRequest request
    ) throws ParseException {
        BoothIdResponse boothIdResponse = boothService.save(memberId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(boothIdResponse);
    }

    @GetMapping("/nearbyBooths")
    public List<BoothDetail> getNearbyBooths(@RequestParam Double latitude, @RequestParam Double longitude) {
        return boothService.findBoothsNearLocation(latitude, longitude);
    }

    @GetMapping("/nearbyBooths/category")
    public List<BoothDetail> getNearbyBooths(@RequestParam Double latitude, @RequestParam Double longitude, @RequestParam String category) {
        return boothService.findBoothsNearLocationByCategory(latitude, longitude, category);
    }



}
