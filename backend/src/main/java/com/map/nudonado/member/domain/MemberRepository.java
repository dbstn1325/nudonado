package com.map.nudonado.member.domain;

import com.map.nudonado.member.exception.MemberNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {


    Optional<Member> findByEmail(final String email);

    boolean existsByEmail(final String email);

    default Member getById(final Long id) {
        return findById(id)
                .orElseThrow(MemberNotFoundException::new);
    }

    default Member getByEmail(final String email) {
        return findByEmail(email)
                .orElseThrow(MemberNotFoundException::new);

    }

    default void validateExistsById(final Long id) {
        if (!existsById(id)) {
            throw new MemberNotFoundException();
        }
    }
}
