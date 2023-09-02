package com.map.nudonado.member.domain;

import com.map.nudonado.common.BaseEntity;
import com.map.nudonado.member.exception.InvalidMemberException;
import lombok.*;


import javax.persistence.*;

@Entity
@Getter
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    private static final int MAX_DISPLAY_NAME_LENGTH = 100;

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

    public void change(final String displayName) {
        validationDisplayNameLength(displayName);
        this.displayName = displayName;
    }

    private void validationDisplayNameLength(final String displayName) {
        if (displayName.trim().length() > MAX_DISPLAY_NAME_LENGTH) {
            throw new InvalidMemberException(String.format("이름은 1자 이상 %d 이하여야 합니다.", MAX_DISPLAY_NAME_LENGTH));
        }
    }

    public static int getMaxDisplayNameLength() {
        return MAX_DISPLAY_NAME_LENGTH;
    }
}
