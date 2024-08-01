package kitchensinkspringboot.demo.controller;

import java.util.List;

import kitchensinkspringboot.demo.model.Member;
import kitchensinkspringboot.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("newMember", new Member());
        List<Member> members = memberService.getAllMembers();
        model.addAttribute("members", members);

        // Log the members to ensure they are being retrieved correctly
        members.forEach(member -> System.out.println("Member: " + member.getId() + ", " + member.getName()));

        return "index";
    }

    @PostMapping("/members")
    public String addMember(@ModelAttribute Member member) {
        memberService.addMember(member);
        return "redirect:/index";
    }

    @PostMapping("/members/{id}/delete")
    public String deleteMember(@PathVariable Long id) {
        memberService.deleteMemberById(id);
        return "redirect:/index";
    }

    @GetMapping("/default")
    public String defaultPage(Model model) {
        return "default";
    }
}