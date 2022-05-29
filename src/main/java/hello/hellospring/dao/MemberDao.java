package hello.hellospring.dao;


import hello.hellospring.vo.MemberVo;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface MemberDao {

    public List<MemberVo> finsAllList();

}
