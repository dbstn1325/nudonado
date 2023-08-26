package com.map.nudonado.trace.domain;

import com.map.nudonado.booth.domain.Booth;
import com.map.nudonado.member.domain.Member;
import com.map.nudonado.trace.dto.response.IntegrationTrace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Collectors;

public interface TraceRepository extends JpaRepository<Trace, Long> {

    List<Trace> findByMemberAndBooth(Member member, Booth booth);

    default List<IntegrationTrace>  getByMemberAndBooth(final Member member, final Booth booth){

        List<Trace> traces = findByMemberAndBooth(member, booth);
        return traces.stream()
                .map(IntegrationTrace::new)
                .collect(Collectors.toList());
    }
}
