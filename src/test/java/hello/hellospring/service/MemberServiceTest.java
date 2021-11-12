package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MemberServiceTest {

    MemoryMemberRepository repository;
    MemberService service;

    @BeforeEach
    void beforeEach() {
        repository = new MemoryMemberRepository();
        service = new MemberService(repository);
    }

    @AfterEach
    void afterEach() {
        repository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("jinnn");

        // when
        Long resultId = service.join(member);

        // then
        Member resultMember = service.findMember(resultId).get(); // 실무에서는 바로 get() 하는 것은 비추
        assertThat(resultMember.getName()).isEqualTo(member.getName());
    }

    @Test
    void 회원가입_예외() {
        // given
        Member member1 = new Member();
        member1.setName("jin");

        Member member2 = new Member();
        member2.setName("jin");
        
        // when
        service.join(member1);

        // then
        // 검증 방법 1
/*
        try {
            service.join(member2);
        } catch (IllegalStateException ex) {
            assertThat(ex.getMessage()).isEqualTo("이미 존재하는 회원");
        }
*/

        // 검증 방법 2
        IllegalStateException illegalStateException = assertThrows(IllegalStateException.class, () -> service.join(member2));
        assertThat(illegalStateException.getMessage()).isEqualTo("이미 존재하는 회원");

    }

    @Test
    void findAllMembers() {

    }

    @Test
    void findOneMember() {

    }
}
