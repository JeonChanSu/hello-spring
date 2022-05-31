package hello.hellospring.service;

import java.util.List;
import java.util.Map;

import hello.hellospring.controller.MemberController;
import hello.hellospring.vo.MemberVo;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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

    public List<MemberVo> findAllList() {
        logger.info("findAllList");
        List<MemberVo> list = this.sqlSession.selectList("MemberDao.findAllList", null);

        return list;
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








}
