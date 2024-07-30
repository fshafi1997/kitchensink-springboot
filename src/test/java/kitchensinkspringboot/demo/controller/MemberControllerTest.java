package kitchensinkspringboot.demo.controller;

import kitchensinkspringboot.demo.model.Member;
import kitchensinkspringboot.demo.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

public class MemberControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MemberService memberService;

    @InjectMocks
    private MemberController memberController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    }

    @Test
    public void testGetAllMembers() throws Exception {
        Member member1 = new Member(1L, "John Doe", "john.doe@example.com", "1234567890");
        Member member2 = new Member(2L, "Jane Doe", "jane.doe@example.com", "0987654321");
        when(memberService.getAllMembers()).thenReturn(Arrays.asList(member1, member2));

        mockMvc.perform(get("/members"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("John Doe")))
                .andExpect(jsonPath("$[1].name", is("Jane Doe")));
    }

}
