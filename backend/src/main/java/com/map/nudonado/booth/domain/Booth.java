package com.map.nudonado.booth.domain;

import com.map.nudonado.common.BaseEntity;
import com.map.nudonado.member.domain.Member;
import com.map.nudonado.trace.domain.Trace;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

import javax.persistence.*;
import java.util.List;

@Getter
@Table(name = "booths")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Booth extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column
    private Point point;

    @Builder
    public Booth(Member member, String category, Location location, Point point) {
        this.member = member;
        this.category = Category.from(category);
        this.location = location;
        this.point = point;
    }
}
