package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional  // 트랜잭션 옵션으로, 테스트 한 데이터를 커밋하지 않고, 롤백한다. 덕분에 반복적인 테스트가 가능함.
public class MemberServiceIntegrationTest {

    @Autowired MemberService service;
    @Autowired MemberRepository repository;

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

        IllegalStateException illegalStateException = assertThrows(IllegalStateException.class, () -> service.join(member2));
        assertThat(illegalStateException.getMessage()).isEqualTo("이미 존재하는 회원");

    }

}
