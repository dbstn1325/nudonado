package com.map.nudonado.booth.presentation;


import com.map.nudonado.auth.dto.LoginMember;
import com.map.nudonado.auth.presentation.AuthenticationPrincipal;
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

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class BoothController {

    private final BoothService boothService;

    @PostMapping("/booths")
    public ResponseEntity<BoothIdResponse> save(
            @AuthenticationPrincipal final LoginMember loginMember,
            @Valid @RequestBody final BoothCreateRequest request
    ) throws ParseException {
        BoothIdResponse boothIdResponse = boothService.save(loginMember.getId(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(boothIdResponse);
    }

    @GetMapping("/near/booths")
    public List<BoothDetail> getNearbyBooths(@RequestParam Double latitude, @RequestParam Double longitude) {
        return boothService.findBoothsNearLocation(latitude, longitude);
    }

    @GetMapping("/category/near/booths")
    public List<BoothDetail> getNearbyBooths(@RequestParam Double latitude, @RequestParam Double longitude, @RequestParam String category) {
        return boothService.findBoothsNearLocationByCategory(latitude, longitude, category);
    }



}
