package hello.hellospring.controller;


import hello.hellospring.dao.MemberDao;
import hello.hellospring.serivce.Myservice;
import hello.hellospring.vo.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MemberController {
     Myservice myservice;

     @Autowired
     public MemberController(Myservice myservice){
         this.myservice = myservice;
     }

/*
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

    }*/

/*
    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMemberList();
        model.addAttribute("members",members);
        return "members/memberList";
    }
*/


    @RequestMapping("members")
    public String list(Model model){
        System.out.println("test1");
        List<MemberVo> members = myservice.finsAllList();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
