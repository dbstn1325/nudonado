package com.map.nudonado.booth.domain;


import com.map.nudonado.booth.domain.exception.BoothCategoryNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


import static com.map.nudonado.common.fixtures.BoothFixtures.테스트_없는_카테고리;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CategoryTest {
    @Test
    void 없는_카테고리를_찾을_경우_예외를_발생시킨다(){
        String category = 테스트_없는_카테고리;

        assertThatThrownBy(()-> Category.from(category))
                .isExactlyInstanceOf(BoothCategoryNotFoundException.class)
                .hasMessage("존재하지 않는 카테고리입니다.");
    }
}
