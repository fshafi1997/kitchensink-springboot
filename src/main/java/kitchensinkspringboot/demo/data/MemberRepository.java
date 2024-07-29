package kitchensinkspringboot.demo.data;

import kitchensinkspringboot.demo.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}