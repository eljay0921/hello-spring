package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemeberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // 이것만 작성해주면 끝난다 ... (?)
    // 이 외의 공통적인 API (findById, findAll, save, etc ...)들은 이미 공통으로 제공하고 있고,
    // 아래와 같이 비즈니스에 따라 새로 만들어야 하는 경우는
    // findBy{~} 형태로 이름을 작성하면 알아서 쿼리를 생성해준다고 보면 됨.
    // 그 외에 복잡한 쿼리가 필요하면, 일반적인 JPA를 사용하거나, JDBC Template을 활용하자.
    @Override
    Optional<Member> findByName(String name);
}
