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
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.hamcrest.Matchers.hasSize;

public class WebControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MemberService memberService;

    @InjectMocks
    private WebController webController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        // Configure Thymeleaf View Resolver
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".xhtml");

        this.mockMvc = MockMvcBuilders.standaloneSetup(webController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void testIndex() throws Exception {
        Member member1 = new Member(1L, "John Doe", "john.doe@example.com", "1234567890");
        Member member2 = new Member(2L, "Jane Doe", "jane.doe@example.com", "0987654321");
        when(memberService.getAllMembers()).thenReturn(Arrays.asList(member1, member2));

        mockMvc.perform(get("/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("newMember"))
                .andExpect(model().attribute("members", hasSize(2)));
    }

    @Test
    public void testAddMember() throws Exception {
        mockMvc.perform(post("/members")
                        .param("name", "John Doe")
                        .param("email", "john.doe@example.com")
                        .param("phoneNumber", "1234567890"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/index"));
    }
}



