package com.map.nudonado.member.presentation;


import com.map.nudonado.auth.dto.LoginMember;
import com.map.nudonado.auth.presentation.AuthenticationPrincipal;
import com.map.nudonado.member.application.MemberService;
import com.map.nudonado.member.dto.MemberResponse;
import com.map.nudonado.member.dto.MemberUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/members")
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/me")
    public ResponseEntity<MemberResponse> findMe(@AuthenticationPrincipal LoginMember loginMember){
        MemberResponse response = memberService.findById(loginMember.getId());
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/me")
    public ResponseEntity<Void> update(
            @AuthenticationPrincipal LoginMember loginMember,
            @Valid @RequestBody MemberUpdateRequest request
            ){
        memberService.update(loginMember.getId(), request);
        return ResponseEntity.noContent().build();
    }
}
