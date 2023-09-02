package com.map.nudonado.member.presentation;


import com.map.nudonado.auth.dto.LoginMember;
import com.map.nudonado.auth.presentation.AuthenticationPrincipal;
import com.map.nudonado.member.application.MemberService;
import com.map.nudonado.member.dto.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
