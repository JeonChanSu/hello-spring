package hello.hellospring.vo;

import hello.hellospring.paging.Criteria;
import hello.hellospring.paging.PaginationInfo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberVo extends Criteria {

    private Long id;

    private Long boardId;

    private String name;

    /** 번호 (PK) */
    private Long idx;

    /** 제목 */
    private String title;

    /** 내용 */
    private String content;

    /** 작성자 */
    private String writer;

    /** 조회 수 */
    private int viewCnt;

    /** 공지 여부 */
    private String noticeYn;

    /** 비밀 여부 */
    private String secretYn;

    private PaginationInfo paginationInfo;

    /** 삭제 여부 */
    private String deleteYn;

    /** 등록일 */
    private String insertTime;

    /** 수정일 */
    private String updateTime;

    /** 삭제일 */
    private String deleteTime;

    /*행번호*/
    private int rownum;


}
