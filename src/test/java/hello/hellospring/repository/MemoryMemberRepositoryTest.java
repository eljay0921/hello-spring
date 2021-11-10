package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 각 테스트 이후에 동작할 코드 작성
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member resultMember = repository.findById(member.getId()).get();    // 바로 get()으로 꺼내는게 실무에서는 좋지 않다고 함. 서치 필요

        // org.junit.jupiter.api
        // Assertions.assertEquals(member, resultMember);

        // org.assertj.core.api : 이걸 더 많이 쓴다 (?)
        // Assertions.assertThat(member).isEqualTo(resultMember);
        assertThat(member).isEqualTo(resultMember);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member resultMember1 = repository.findByName("spring1").get();
        assertThat(resultMember1).isEqualTo(member1);

        Member resultMember2 = repository.findByName("spring2").get();
        assertThat(resultMember2).isEqualTo(member2);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> resultMembers = repository.findAll();

        assertThat(resultMembers.size()).isEqualTo(2);
    }
}
