package com.map.nudonado.booth.domain;

import com.map.nudonado.booth.domain.exception.BoothBackgroundColorDiversityNotFoundException;
import com.map.nudonado.booth.domain.exception.BoothCategoryNotFoundException;
import com.map.nudonado.common.exception.ExceptionType;
import org.junit.jupiter.api.Test;

import static com.map.nudonado.common.fixtures.BoothFixtures.테스트_없는_부스_다양성_유무;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class BackgroundColorDiversityTest {
    @Test
    void 없는_부스의_배경_다양성_유무를_찾을_경우_예외를_발생시킨다(){
        String diversity = 테스트_없는_부스_다양성_유무;

        assertThatThrownBy(()-> BackgroundColorDiversity.from(diversity))
                .isExactlyInstanceOf(BoothBackgroundColorDiversityNotFoundException.class)
                .hasMessage("존재하지 않는 배경색 다양 유무입니다.");
    }

}