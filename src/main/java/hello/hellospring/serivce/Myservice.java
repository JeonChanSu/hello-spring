package hello.hellospring.serivce;

import java.util.List;

import hello.hellospring.dao.MemberDao;
import hello.hellospring.vo.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Myservice {
    MemberDao memberDao;

    @Autowired
    public Myservice(MemberDao memberDao){
        this.memberDao = memberDao;
    }

    public List<MemberVo> finsAllList() {
        System.out.println("test2");
        List<MemberVo> list = memberDao.finsAllList();
        System.out.println("test3");
        return list;
    }


}
