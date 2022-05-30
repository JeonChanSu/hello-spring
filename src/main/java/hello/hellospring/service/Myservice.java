package hello.hellospring.service;

import java.util.List;

import hello.hellospring.controller.MemberController;
import hello.hellospring.dao.MemberDao;
import hello.hellospring.vo.MemberVo;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
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

        List<MemberVo> list = this.sqlSession.selectList("MemberDao.findAllList", null);

        return list;
    }

    public List<MemberVo> SearchName(String name){

        List<MemberVo> list = this.sqlSession.selectList("MemberDao.searchName" ,name);

/*        for (int i = 0; i < list.size(); i++) {
            logger.info("myService = "+ list.get(i).getName());
        }*/
        return list;
    }

    public void deleteId(Long id){

        this.sqlSession.delete("MemberDao.deleteId" ,id);
    }

    public void create(String name){
        this.sqlSession.insert("MemberDao.insertName",name);
    }








}
