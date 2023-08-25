package com.map.nudonado.member.presentation;


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

    @GetMapping("/me/{memberId}")
    public ResponseEntity<MemberResponse> findMe(@PathVariable Long memberId){
        MemberResponse response = memberService.findById(memberId);
        return ResponseEntity.ok(response);
    }
}
