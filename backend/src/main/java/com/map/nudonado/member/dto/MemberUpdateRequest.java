package com.map.nudonado.member.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class MemberUpdateRequest {

    @NotBlank(message = "회원 이름은 공백일 수 없습니다.")
    private String displayName;

}
