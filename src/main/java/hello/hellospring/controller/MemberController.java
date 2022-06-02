package hello.hellospring.controller;


import com.fasterxml.jackson.annotation.JsonFormat;
import hello.hellospring.dao.MemberDao;
import hello.hellospring.mapper.MemberMapper;
import hello.hellospring.paging.Criteria;
import hello.hellospring.service.Myservice;
import hello.hellospring.vo.MemberVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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

        logger.info("list");
        List<MemberVo> members = myservice.findAllList();
        /*List<MemberVo> members = MemberMapper.findAllList();*/

        model.addAttribute("members",members);

        return "members/memberList";
    }

/*    @GetMapping(value = "/board/list.do")
    public String openBoardList(@ModelAttribute("criteria") Criteria criteria, Model model) {
        List<MemberVo> boardList = boardService.getBoardList(criteria);
        model.addAttribute("boardList", boardList);

        return "board/list";
    }*/


    @RequestMapping("/members/new")
    public String createForm(){
        logger.info("createFormController");
        return "members/createMemberFrom";
    }

    @PostMapping("/members/createMemberFrom")
    public String create(String newName, HttpServletResponse response) throws IOException {
        logger.info("createFormController");


        List<MemberVo> names = myservice.SearchName(newName);


        if(names.size() == 0){
            myservice.create(newName);
            return "redirect:/";
        }else{
            logger.info("names size1");
            /*model.addAttribute("resurt","중복된 이름입니다.");*/
            logger.info("names size3");
            init(response);
            PrintWriter viewout = response.getWriter();

            viewout.println("<script>alert('중복된 이름입니다.');</script>");

            /*ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("중복된 이름입니다.");
            modelAndView.addObject("respose");*/
            
            return "members/createMemberFrom";
        }

    }

    public static void init(HttpServletResponse response)
    {
        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("utf-8");
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

        model.addAttribute("name",name2);
        return "members/update";
    }

    @PostMapping("/members/update")
    public String updateMember(String name,String changeName){
        logger.info("updateMember");
        if("".equals(changeName)|| changeName==null){
            return "redirect:/";
        }

        Map map = new HashMap();
        map.put("name",name);
        map.put("changeName",changeName);


        myservice.updateMember(map);
        return "redirect:/";
    }






}
