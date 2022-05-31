package hello.hellospring.controller;


import com.fasterxml.jackson.annotation.JsonFormat;
import hello.hellospring.service.Myservice;
import hello.hellospring.vo.MemberVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.IllformedLocaleException;
import java.util.List;
import java.util.Map;

@Controller
public class MemberController {
    @Autowired
     Myservice myservice;
    private Logger logger = LoggerFactory.getLogger(MemberController.class);

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
//        System.out.println("test1");



        List<MemberVo> members = myservice.findAllList();
        model.addAttribute("members",members);

        return "members/memberList";
    }

/*    @RequestMapping("members/memberSearch")
    public String searchName(Model model){
//        System.out.println("test1");
        logger.info("test1");

        String members = myservice.searchName("name");
        model.addAttribute("members",members);

        return "members/memberList";
    }*/


    @RequestMapping("/members/new")
    public String createForm(){
        logger.info("createFormController");
        return "members/createMemberFrom";
    }

    @PostMapping("/members/createMemberFrom")
    public String create(String name){

        logger.info("createFormController1");
        List<MemberVo> names = myservice.SearchName(name);
        logger.info("createFormController2");

        if(names.size() == 0){
            myservice.create(name);
            return "redirect:/";
        }else{
            return "members/createMemberFrom";
        }

    }

    @RequestMapping("/members/memberSearch")
    public String SearchForm(){
        logger.info("SearchForm");
        return "members/searchForm";

    }

    @PostMapping("/members/searchForm")
    public String search(MemberVo member){

        List<MemberVo> name = myservice.SearchName(member.getName());

        if(name.size() ==0) {
            return "redirect:/";
        }else{
            return "members/searchForm";
        }
    }


    @PostMapping("/members/memberList")
    public String deleteMember(Long id){
        logger.info("deleteMember");
        myservice.deleteId(id);

        /*if(name.size() ==0) {
            return "redirect:/";
        }else{
            return "members/searchForm";
        }*/
        return "redirect:/";
    }





    @PostMapping("/updateMember")
    public String update(HttpServletRequest request,HttpServletRequest response,Model model){
        logger.info("update");
        String name2 = request.getParameter("testmember");
        String name3 = response.getParameter("testmember");

        model.addAttribute("name",name2);
        return "members/update";
    }

    @PostMapping("/members/update")
    public String updateMember(String name,String changeName){
        logger.info("updateMember");
        Map map = new HashMap();
        map.put("name",name);
        map.put("changeName",changeName);


        myservice.updateMember(map);
        return "redirect:/";
    }




}
