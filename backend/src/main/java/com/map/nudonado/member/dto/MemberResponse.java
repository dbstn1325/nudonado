package com.map.nudonado.member.dto;

import lombok.Getter;

@Getter
public class MemberResponse {

    private Long id;
    private String email;
    private String displayName;
    private String profileImage;

    private MemberResponse() {}

    public MemberResponse(Long id, String email, String displayName, String profileImage) {
        this.id = id;
        this.email = email;
        this.displayName = displayName;
        this.profileImage = profileImage;
    }
}
