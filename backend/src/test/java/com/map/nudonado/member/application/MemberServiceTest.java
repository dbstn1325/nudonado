package com.map.nudonado.member.application;

import com.map.nudonado.auth.domain.OAuthToken;
import com.map.nudonado.auth.domain.OAuthTokenRepository;
import com.map.nudonado.common.config.ExternalApiConfig;
import com.map.nudonado.common.fixtures.MemberFixtures;
import com.map.nudonado.member.domain.Member;
import com.map.nudonado.member.domain.MemberRepository;
import com.map.nudonado.member.dto.MemberUpdateRequest;
import com.map.nudonado.member.exception.InvalidMemberException;
import com.map.nudonado.trace.domain.exception.TraceMemoTooLongException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.map.nudonado.common.fixtures.MemberFixtures.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ExternalApiConfig.class)
@ActiveProfiles("test")
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private OAuthTokenRepository oAuthTokenRepository;

    private Member member;

    @DisplayName("회원을 조회한다.")
    @Test
    void 회원을_조회한다() {
        //given
        this.member = memberRepository.save(테스트_멤버());

        //when & then
        assertThat(memberService.findById(this.member.getId()).getId())
                .isEqualTo(this.member.getId());
    }


    @DisplayName("회원의 이름을 수정한다.")
    @Test
    void 회원의_이름을_수정한다() {
        //given
        MemberUpdateRequest request = 테스트_멤버_수정_요청();
        this.member = memberRepository.save(테스트_멤버());

        //when
        memberService.update(this.member.getId(), request);

        //then
        Member actual = memberRepository.getById(this.member.getId());
        assertThat(actual.getDisplayName()).isEqualTo(테스트_수정_이름);
    }

    @DisplayName("회원의 이름을 수정할 때 100글자를 넘기면 에러를 반환한다.")
    @Test
    void 회원의_이름을_수정할_때_100글자를_넘기면_에러를_반환한다() {
        //given
        MemberUpdateRequest request = 테스트_멤버_수정_요청_길이초과();
        this.member = memberRepository.save(테스트_멤버());

        //when & then
        assertThatThrownBy(() -> memberService.update(this.member.getId(), request))
                .isInstanceOf((InvalidMemberException.class));
    }

}