package com.map.nudonado.trace.application;


import com.map.nudonado.booth.domain.Booth;
import com.map.nudonado.booth.domain.BoothRepository;
import com.map.nudonado.member.domain.Member;
import com.map.nudonado.member.domain.MemberRepository;
import com.map.nudonado.trace.domain.Trace;
import com.map.nudonado.trace.domain.TraceRepository;
import com.map.nudonado.trace.dto.request.TraceCreateRequest;
import com.map.nudonado.trace.dto.response.TraceCreateResponse;
import com.map.nudonado.trace.dto.response.IntegrationTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class TraceService {

    private final MemberRepository memberRepository;
    private final BoothRepository boothRepository;
    private final TraceRepository traceRepository;

    @Transactional
    public TraceCreateResponse save(final Long memberId, final Long boothId, TraceCreateRequest request){
        Member member = memberRepository.getById(memberId);
        Booth booth = boothRepository.getById(boothId);

        Trace trace = createTrace(member, booth, request);

        return new TraceCreateResponse(traceRepository.save(trace).getId());
    }

    public List<IntegrationTrace> findBoothTraces(final Long memberId, final Long boothId){
        Member member = memberRepository.getById(memberId);
        Booth booth = boothRepository.getById(boothId);

        return traceRepository.getByMemberAndBooth(member, booth);
    }

    private Trace createTrace(final Member member, final Booth booth, TraceCreateRequest request){
        return traceRepository.save(request.toTrace(member, booth));
    }

}
