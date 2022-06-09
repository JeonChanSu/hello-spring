package hello.hellospring.controller;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.JsonObject;
import hello.hellospring.paging.PaginationInfo;
import hello.hellospring.service.Myservice;
import hello.hellospring.vo.MemberVo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import java.lang.reflect.Member;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.*;

@Controller
public class MemberController {
    @Autowired
    Myservice myservice;
    MemberVo memberVo;
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
    }a
*/

    //목록조회
    @RequestMapping("members")
    public String list(Model model, MemberVo params) {
//        System.out.println("test1");
        logger.info("list");
        List<MemberVo> boardList = Collections.emptyList();

        //페이징처리 준비 시작
        int boardTotalCount = myservice.pageCount();

        PaginationInfo paginationInfo = new PaginationInfo(params);
        paginationInfo.setTotalRecordCount(boardTotalCount);

        params.setPaginationInfo(paginationInfo);
        //페이징처리 준비 끝

        if (boardTotalCount > 0) {
            boardList = myservice.findAllList(params);
        }
/*        for (int i = 0; i < boardList.size(); i++) {
            logger.info(String.valueOf(boardList.get(i).getIdx()));
        }*/
/*        Map map = new HashMap();
        ArrayList<Map> arrmap = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        ArrayList<JSONObject> arrayJson = new ArrayList<JSONObject>();
        for (int i = 0; i < boardList.size(); i++) {
            jsonObject.put("id",boardList.get(i).getId());
            jsonObject.put("title",boardList.get(i).getTitle());
            jsonObject.put("writer",boardList.get(i).getWriter());
            jsonObject.put("content",boardList.get(i).getContent());
            arrmap.add(i,jsonObject);
            jsonArray.add(i,jsonObject);

//            logger.info("jsonArray"+jsonArray.toJSONString());

        }
        jsonArray.add(arrmap);
        logger.info("arrmap"+arrmap.toString());
        logger.info("arrmap"+jsonArray.toJSONString());*/


/*        jsonObject.put("id","id1");
        jsonObject.put("title","title1");
        *//*jsonArray.add(0,jsonObject);*//*
        arrayJson.add(0,jsonObject);
        jsonObject.put("id","id2");
        jsonObject.put("title","title2");
        *//*jsonArray.add(1,jsonObject);*//*
        arrayJson.add(1,jsonObject);
        jsonArray.add(arrayJson);
        logger.info("jsonArray"+jsonArray.toJSONString());*/

        /*logger.info("jsonArray"+jsonArray.toJSONString());*/






        model.addAttribute("params",params);
        model.addAttribute("boardList", boardList);

        return "members/memberList";
    }


    @RequestMapping("/members/new")
    public String createForm() {
        logger.info("createFormController");
        return "members/write";
     //   return "members/createMemberFrom";
    }


    //글작성
    @PostMapping("/members/writeSave")
    public String writeSave(MemberVo memberVo){
        logger.info("writeSave");
        Map map = new HashMap();

        map.put("title", memberVo.getTitle());
        map.put("writer", memberVo.getWriter());
        map.put("content", memberVo.getContent());
/*
        logger.info("title ="+(String) map.get("title"));
        logger.info("writer ="+(String) map.get("writer"));
        logger.info("content ="+(String) map.get("content"));*/

        myservice.writeSave(map);
        return "redirect:/members/";
    }

    //상세조회
    @PostMapping("view")
    public HashMap view(@RequestBody HashMap map){
        logger.info("view="+(String) map.get("boardId"));
        String param = (String) map.get("boardId");

        map.put("id",param);

        Map map2 = new HashMap();
        map2 = myservice.view(map);
        logger.info("title="+map2.get("title"));

    return map;
    }



    @PostMapping("/members/createMemberFrom")
    public String create(String newName, HttpServletResponse response) throws IOException {
        logger.info("createFormController");


        List<MemberVo> names = myservice.SearchName(newName);


        if (names.size() == 0) {
            myservice.create(newName);
            return "redirect:/";
        } else {

            /*model.addAttribute("resurt","중복된 이름입니다.");*/

            init(response);
            PrintWriter viewout = response.getWriter();

            viewout.println("<script>alert('중복된 이름입니다.');</script>");

            /*ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("중복된 이름입니다.");
            modelAndView.addObject("respose");*/

            return "members/createMemberFrom";
        }

    }

    public static void init(HttpServletResponse response) {
        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("utf-8");
    }


    @RequestMapping("/members/memberSearch")
    public String SearchForm() {
        logger.info("SearchForm");
        return "members/searchForm";

    }

    @PostMapping("/members/searchForm")
    public String search(MemberVo member) {

        List<MemberVo> name = myservice.SearchName(member.getName());

        if (name.size() == 0) {
            return "redirect:/";
        } else {
            return "members/searchForm";
        }
    }


    @PostMapping("/members/memberList")
    public String deleteMember(Long id) {
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
    public String update(HttpServletRequest request, HttpServletRequest response, Model model) {
        logger.info("update");
        String name2 = request.getParameter("testmember");

        model.addAttribute("name", name2);
        return "members/update";
    }

    @PostMapping("/members/update")
    public String updateMember(String name, String changeName) {
        logger.info("updateMember");
        if ("".equals(changeName) || changeName == null) {
            return "redirect:/";
        }

        Map map = new HashMap();
        map.put("name", name);
        map.put("changeName", changeName);


        myservice.updateMember(map);
        return "redirect:/";
    }

    // JSON을 파싱하기 위해서 ParseException가 필수적으로 필요하다


    @PostMapping("test2")
    public JSONObject test2(HttpServletRequest httpServletRequest, MemberVo params){
        logger.info("test1");

        List<MemberVo> boardList = Collections.emptyList();
        //페이징처리 준비 시작
        int boardTotalCount = myservice.pageCount();
        logger.info("test3");
        PaginationInfo paginationInfo = new PaginationInfo(params);
        paginationInfo.setTotalRecordCount(boardTotalCount);
        logger.info("test4");
        params.setPaginationInfo(paginationInfo);
        //페이징처리 준비 끝
        logger.info("test5");
        if (boardTotalCount > 0) {
            boardList = myservice.findAllList(params);
        }
        logger.info("test6");
        JSONObject jsonObject = new JSONObject();
        logger.info("test7");
        jsonObject.put("boardList",boardList);
        logger.info("test8="+jsonObject.toJSONString());
        logger.info("test9="+jsonObject.toString());
        logger.info("test10="+jsonObject.get("id"));

    return jsonObject;
    }









    @PostMapping("test")
    public JSONArray test() throws ParseException {
        // JSONObject 생성
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("name", "재횬");
        jsonObject1.put("gender", "남");
        jsonObject1.put("age", 28);

        // JSONObject 생성
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("name", "다욘");
        jsonObject2.put("gender", "여");
        jsonObject2.put("age", 26);

        // JSONArray 생성 및 array에 JSONObject 추가
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(jsonObject1);
        jsonArray.add(jsonObject2);

        // JSONArray를 String으로 변환
        String jsonStr = jsonArray.toJSONString();
        logger.info("test1");
        System.out.println(jsonStr);
        logger.info("test2");
        /*
        출력:
        [{"gender":"남","name":"재횬","age":28},{"gender":"여","name":"다욘","age":26}]
        */

        // JSONParser를 이용해서 String으로된 JSON을 다시 JSONObject로 변환
        JSONParser parser = new JSONParser();
        JSONArray parsedJsonArray = null;
        try {
            parsedJsonArray = (JSONArray) parser.parse(jsonStr);
        } catch (org.json.simple.parser.ParseException e) {
            throw new RuntimeException(e);
        }

        for (Object o: parsedJsonArray){
            JSONObject jo = (JSONObject) o;
            logger.info("test3");
            System.out.println(jo.get("name"));
            logger.info("test4");
        }

        return jsonArray;
        /*
        출력:
        재횬
        다욘
        */

    }

/*    @PostMapping("test2")
    public ResponseEntity getBoardList(MemberVo memberVo,MemberVo params) throws Exception {
        logger.info("getBoardList");
        List<MemberVo> boardList = Collections.emptyList();

        //페이징처리 준비 시작
        int boardTotalCount = myservice.pageCount();

        PaginationInfo paginationInfo = new PaginationInfo(params);
        paginationInfo.setTotalRecordCount(boardTotalCount);

        params.setPaginationInfo(paginationInfo);
        //페이징처리 준비 끝

        if (boardTotalCount > 0) {
            boardList = myservice.findAllList(params);
        }


        return ResponseEntity.ok(boardList);
    }*/

    @GetMapping("test2")
    public ResponseEntity<List<MemberVo>> getAllUser(MemberVo params) {
        logger.info("getBoardList");
        List<MemberVo> boardList = Collections.emptyList();

        //페이징처리 준비 시작
        int boardTotalCount = myservice.pageCount();

        PaginationInfo paginationInfo = new PaginationInfo(params);
        paginationInfo.setTotalRecordCount(boardTotalCount);

        params.setPaginationInfo(paginationInfo);
        //페이징처리 준비 끝

        if (boardTotalCount > 0) {
            boardList = myservice.findAllList(params);
        }

        List<MemberVo> userList = myservice.findAllList(params);
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }
@PostMapping("test3")
    public ResponseEntity<List<MemberVo>> getAllUser2(MemberVo params) {
        logger.info("getBoardList");
        List<MemberVo> boardList = Collections.emptyList();

        //페이징처리 준비 시작
        int boardTotalCount = myservice.pageCount();

        PaginationInfo paginationInfo = new PaginationInfo(params);
        paginationInfo.setTotalRecordCount(boardTotalCount);

        params.setPaginationInfo(paginationInfo);
        //페이징처리 준비 끝

        if (boardTotalCount > 0) {
            boardList = myservice.findAllList(params);
        }

        List<MemberVo> userList = myservice.findAllList(params);
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

}
