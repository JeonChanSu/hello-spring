package hello.hellospring.serivce;


public class MemberService {
/*
    private final MemberRepository memberRepository;

    @Autowired
    public  MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }


    //회원가입
    public Long join(Member member){
        validateDuplicateMember(member); //중복회원 검증
        try {
            memberRepository.save(member);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(member1 -> {
                     throw new IllformedLocaleException("이미 존재하는 회원입니다.");
                });
    }

    public List<Member> findMemberList(){
        try {
            return memberRepository.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Member> findOne(Long memberId){
        try {
            return memberRepository.findById(memberId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/


/*    public Optional<Member> deleteMember(Long id){
        try {
            return memberRepository.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/


}
