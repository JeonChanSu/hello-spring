package hello.hellospring.vo;

public class MemberVo {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Long id;
    private String name;

    public String getChangeName() {
        return changeName;
    }

    public void setChangeName(String changeName) {
        this.changeName = changeName;
    }

    private String changeName;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    private int pageNum;

    public int getROWNUM() {
        return ROWNUM;
    }

    public void setROWNUM(int ROWNUM) {
        this.ROWNUM = ROWNUM;
    }

    private  int ROWNUM;

    public String getResurt() {
        return resurt;
    }

    public void setResurt(String resurt) {
        this.resurt = resurt;
    }

    private String resurt;
}
