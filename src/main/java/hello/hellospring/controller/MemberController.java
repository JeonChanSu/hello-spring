package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.serivce.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;


    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberFrom";
    }

    @PostMapping("/members/new")
    public String create(MemberForm from){
        Member member =  new Member();
        member.setName(from.getName());
        System.out.println("member test = "+ member.getName());
        memberService.join(member);
        return"redirect:/";

    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMemberList();
        model.addAttribute("members",members);
        return "members/memberList";

    }
}
