package kitchensinkspringboot.demo.service;

import kitchensinkspringboot.demo.data.MemberRepository;
import kitchensinkspringboot.demo.model.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllMembers() {
        Member member1 = new Member(1L, "John Doe", "john.doe@example.com", "1234567890");
        Member member2 = new Member(2L, "Jane Doe", "jane.doe@example.com", "0987654321");
        when(memberRepository.findAll()).thenReturn(Arrays.asList(member1, member2));

        List<Member> members = memberService.getAllMembers();
        assertEquals(2, members.size());
        assertEquals("John Doe", members.get(0).getName());
        assertEquals("Jane Doe", members.get(1).getName());
    }

    @Test
    public void testAddMember() {
        Member member = new Member(null, "John Doe", "john.doe@example.com", "1234567890");
        memberService.addMember(member);
        verify(memberRepository).save(member);
    }
}
