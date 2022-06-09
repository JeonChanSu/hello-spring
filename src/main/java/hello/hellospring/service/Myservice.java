package hello.hellospring.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import hello.hellospring.controller.MemberController;
import hello.hellospring.vo.MemberVo;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;

@Service
public class Myservice {
//    MemberDao memberDao;

    private Logger logger = LoggerFactory.getLogger(MemberController.class);
    @Resource(name="SessionTemplate")
    private SqlSession sqlSession;

/*    @Autowired
    MemberDao memberDao;*/
//    public Myservice(MemberDao memberDao){
//        this.memberDao = memberDao;
//    }

    public List<MemberVo> findAllList(MemberVo params) {
        logger.info("findAllList");
        /*HashMap map = new HashMap<>();*/
        /*logger.info("startPage1"+ );
        logger.info("startPage1"+recordsPerPage);*/
        /*if(startPage <= 0 || recordsPerPage <= 0){
            startPage=0;
            recordsPerPage=9;
        }*/

        /*map.put("startPage",startPage);
        map.put("recordsPerPage",recordsPerPage);*/
        /*logger.info("startPage2"+map.get("startPage"));
        logger.info("recordsPerPage2"+map.get("recordsPerPage"));*/
/*        logger.info("recordsPerPage1="+params.getPaginationInfo().getFirstRecordIndex());
        logger.info("recordsPerPage2="+params.getRecordsPerPage());
        logger.info("recordsPerPage3="+params.getPaginationInfo().getFirstPage());
        logger.info("recordsPerPage4="+params.getPaginationInfo().getLastPage());
        logger.info("recordsPerPage5="+params.getPaginationInfo().getTotalRecordCount());
        logger.info("recordsPerPage6="+params.getPaginationInfo().getCriteria().getRecordsPerPage());*/


        List<MemberVo> list = this.sqlSession.selectList("MemberDao.findAllList", params);

        return list;
    }

    public int pageCount(){
        int boardTotalCount = this.sqlSession.selectOne("MemberDao.findAllListCount");
        return boardTotalCount;
    }

    public List<MemberVo> SearchName(String name){
        logger.info("SearchName");
        List<MemberVo> list = this.sqlSession.selectList("MemberDao.searchName" ,name);

/*        for (int i = 0; i < list.size(); i++) {
            logger.info("myService = "+ list.get(i).getName());
        }*/
        return list;
    }

    public void deleteId(Long id){
        logger.info("deleteId");
        this.sqlSession.delete("MemberDao.deleteId" ,id);
    }

    public void create(String name){
        logger.info("create");
        this.sqlSession.insert("MemberDao.insertName",name);
    }
    public void updateMember(Map map){
        logger.info("updateMember Service");
        /*this.sqlSession.update("MemberDao.updateName",);*/
        this.sqlSession.update("MemberDao.updateName",map);
    }

    public void writeSave(Map map){
        logger.info("writeSave Service");
        this.sqlSession.update("MemberDao.writeSave",map);
    }

    public Map view(Map map){
        logger.info("view Service");
        List<MemberVo> list =this.sqlSession.selectList("MemberDao.writeView" ,map);

        Map returnMap = new HashMap<>();

        returnMap.put("id",list.get(0).getId());
        returnMap.put("title",list.get(0).getTitle());
        returnMap.put("writer",list.get(0).getTitle());
        returnMap.put("CONTENT",list.get(0).getContent());
        returnMap.put("insertTime",list.get(0).getInsertTime());
        returnMap.put("deleteTime",list.get(0).getDeleteTime());

        return returnMap;
    }


    public List<MemberVo> testList(MemberVo params) {
        List<MemberVo> list = this.sqlSession.selectList("MemberDao.testList", params);
        return list;

    }





}
