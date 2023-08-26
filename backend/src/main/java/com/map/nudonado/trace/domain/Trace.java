package com.map.nudonado.trace.domain;

import com.map.nudonado.booth.domain.Booth;
import com.map.nudonado.common.BaseEntity;
import com.map.nudonado.member.domain.Member;
import lombok.*;

import javax.persistence.*;

@Getter
@Table(name = "traces")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Trace extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booth_id")
    private Booth booth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column
    private Memo memo;

    @Builder
    public Trace(Booth booth, Member member, String memo) {
        this.booth = booth;
        this.member = member;
        this.memo = new Memo(memo);
    }
}
