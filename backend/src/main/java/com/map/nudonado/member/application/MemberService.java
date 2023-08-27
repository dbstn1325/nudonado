package com.map.nudonado.member.application;


import com.map.nudonado.member.domain.Member;
import com.map.nudonado.member.domain.MemberRepository;
import com.map.nudonado.member.dto.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponse findById(final Long id){
        return new MemberResponse(memberRepository.getById(id));
    }

}
