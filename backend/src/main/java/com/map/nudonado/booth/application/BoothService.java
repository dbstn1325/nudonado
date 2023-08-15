package com.map.nudonado.booth.application;

import com.map.nudonado.booth.domain.Booth;
import com.map.nudonado.booth.domain.BoothIdResponse;
import com.map.nudonado.booth.domain.BoothRepository;
import com.map.nudonado.booth.dto.request.BoothCreateRequest;
import com.map.nudonado.member.domain.Member;
import com.map.nudonado.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BoothService {

    private final BoothRepository boothRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public BoothIdResponse save(final Long memberId, final BoothCreateRequest request){
        Member member = memberRepository.getById(memberId);
        Booth booth = request.toBooth(member);

        return new BoothIdResponse(boothRepository.save(booth));
    }
}
