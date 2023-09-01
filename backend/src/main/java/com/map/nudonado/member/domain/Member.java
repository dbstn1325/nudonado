package com.map.nudonado.member.domain;

import com.map.nudonado.booth.domain.Booth;
import com.map.nudonado.common.BaseEntity;
import lombok.*;


import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "display_name", nullable = false)
    private String displayName;

    @Column(name = "profile_image_url", nullable = true)
    private String profileImageUrl;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "social_type", nullable = false)
    private SocialType socialType;

    @Builder
    public Member(String email, String displayName, String profileImageUrl, SocialType socialType) {
        this.email = email;
        this.displayName = displayName;
        this.profileImageUrl = profileImageUrl;
        this.socialType = socialType;
    }
}
