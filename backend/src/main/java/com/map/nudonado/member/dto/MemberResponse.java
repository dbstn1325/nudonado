package com.map.nudonado.member.dto;

import com.map.nudonado.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberResponse {

    private Long id;
    private String email;
    private String displayName;
    private String profileImage;

    @Builder
    public MemberResponse(Long id, String email, String displayName, String profileImage) {
        this.id = id;
        this.email = email;
        this.displayName = displayName;
        this.profileImage = profileImage;
    }

    public MemberResponse(final Member member) {
        this(member.getId(), member.getEmail(), member.getDisplayName(), member.getProfileImageUrl());
    }


}
