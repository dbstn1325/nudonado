package com.map.nudonado.member.dto;

import com.map.nudonado.member.domain.Member;
import lombok.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class JoinDto {
    @NotEmpty
    @Email(message = "유효하지 않은 이메일 형식입니다.",
            regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    private String email;

    @NotEmpty
    private String displayName;

    public static Member toEntity(Member member) {
        return Member.builder()
                .email(member.getEmail())
                .displayName(member.getDisplayName())
                .build();
    }
}
