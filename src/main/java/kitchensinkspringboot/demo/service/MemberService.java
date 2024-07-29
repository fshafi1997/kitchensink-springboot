package kitchensinkspringboot.demo.service;

import kitchensinkspringboot.demo.data.MemberRepository;
import kitchensinkspringboot.demo.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public void addMember(Member member) {
        memberRepository.save(member);
    }
}