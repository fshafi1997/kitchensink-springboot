package kitchensinkspringboot.demo.controller;

import java.util.List;

import kitchensinkspringboot.demo.model.Member;
import kitchensinkspringboot.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }
}