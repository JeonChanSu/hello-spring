package hello.hellospring.mapper;

import hello.hellospring.vo.MemberVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {


    List<MemberVo> finsAllList();


}