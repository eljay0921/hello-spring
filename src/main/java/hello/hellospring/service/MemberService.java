package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository repository = new MemoryMemberRepository();

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        validateMember(member);
        repository.save(member);
        return member.getId();
    }

    private void validateMember(Member member) {
        // 중복된 이름의 회원이 존재하면 안된다는 비즈니스 요구사항이 있다면,
        Optional<Member> resultMember = repository.findByName(member.getName());
        resultMember.ifPresent(mem -> {
            throw new IllegalStateException("이미 존재하는 회원");
        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return repository.findAll();
    }

    /**
     * id로 회원 조회
     */
    public Optional<Member> findMember(Long id) {
        return repository.findById(id);
    }
}
