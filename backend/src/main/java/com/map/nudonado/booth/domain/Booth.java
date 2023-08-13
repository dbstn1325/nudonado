package com.map.nudonado.booth.domain;

import com.map.nudonado.common.BaseEntity;
import com.map.nudonado.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@Table(name = "booths")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Booth extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Embedded
    private Location location;

    @Column
    @ColumnDefault("0")
    private int removeReq;

    @Builder
    public Booth(Member member, Category category, Location location) {
        this.member = member;
        this.category = category;
        this.location = location;
    }
}
