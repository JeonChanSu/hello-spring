package hello.hellospring.repository;

public class JdbcMemberRepository /*implements MemberRepository*/{
/*
    private final DataSource dataSource;

    public JdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Member save(Member member) throws SQLException {
        String sql = "insert into test_schema.test_table ( name) VALUES (?);";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
    System.out.println("진입테스트~");
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, member.getName());
            pstmt.executeUpdate();
            rs=pstmt.getGeneratedKeys();

            if(rs.next()){
                member.setId(rs.getLong(1));
            }else{
                throw new SQLException("id 조회 실패");
            }
            return member;
        }catch (Exception e){
            throw new IllegalStateException(e);

        }finally{
            close(conn,pstmt,rs);
        }

    }




    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }

    private void close(Connection conn,PreparedStatement pstmt, ResultSet rs) throws SQLException{
        try {
            if(rs != null){
                rs.close();
            }
        }catch (SQLException e){
            e.printStackTrace();;
        }

        try {
            if(pstmt != null){
                pstmt.close();
            }
        }catch (SQLException e){
            e.printStackTrace();;
        }

        try {
            if(conn != null){
                conn.close();
            }
        }catch (SQLException e){
            e.printStackTrace();;
        }

    }

    @Override
    public Optional<Member> findById(Long id) throws SQLException {
        String sql = "select * from test_table where id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1,id);

            rs=pstmt.executeQuery();

            if(rs.next()){
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            }else{
                throw new SQLException("id 조회 실패");
            }

        }catch (Exception e){
            throw new IllegalStateException(e);

        }finally{
            close(conn,pstmt,rs);
        }
    }

    @Override
    public Optional<Member> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() throws SQLException {
        String sql = "select * from test_table ";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            rs=pstmt.executeQuery();
            List<Member> members = new ArrayList<>();
            while (rs.next()){
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                members.add(member);

            }
            return members;
        }catch (Exception e){
            throw new IllegalStateException(e);

        }finally{
            close(conn,pstmt,rs);
        }
    }*/
}
