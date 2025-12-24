package examspring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class MemberDao {
	private JdbcTemplate jdbcTemplate;
	
	public MemberDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public Member selectByEmail(String email) {
		String sql = "SELECT * FROM MEMBER2 WHERE EMAIL=?";
		List<Member> result = jdbcTemplate.query(sql, new MemberRowMapper(), email); //jdbcTemplate.query은 List 반환타입이다.
		
		return result.isEmpty() ? null : result.get(0);
	}
	
	public List<Member> selectAll(){
		String sql = "SELECT * FROM MEMBER2";
		List<Member> result = jdbcTemplate.query(sql, new MemberRowMapper());
		return result;
	}
	
	public int count() {
		String sql = "SELECT COUNT(*) FROM MEMBER2";
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class);// 쿼리 실행 결과로 단일 값 하나만 반환되는 경우에 사용됩니다.
		return count;
	}
	
//	public void update(Member member) {
//		String sql = "UPDATE MEMBER2 SET NAME=?, PASSWORD=? WHERE EMAIL=?";
//		jdbcTemplate.update(sql, member.getName(), member.getPassword(), member.getEmail());
//	}
	
	public void update(Member member) {
		String sql = "UPDATE MEMBER2 SET NAME=?, PASSWORD=? WHERE EMAIL=?";
		jdbcTemplate.update(new PreparedStatementCreator() { //jdbcTemplate.update 반환 타입은 int타입이다.
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, member.getName());
				pstmt.setString(2, member.getPassword());
				pstmt.setString(3, member.getEmail());

				return pstmt;
			}
		});
	}

//	public void insert(Member member) {
//		String sql = "INSERT INTO MEMBER VALUES (MEMBER2_SEQ.NEXTVAL, ?, ?, ?, ?)";
//		jdbcTemplate.update(
//				sql, 
//				member.getEmail(), 
//				member.getPassword(), 
//				member.getName(),
//				member.getRegdate());
//	}
	
	public void insert(final Member member) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO MEMBER2 VALUES (MEMBER2_SEQ.NEXTVAL, ?, ?, ?, ?)";
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				
				PreparedStatement pstmt = con.prepareStatement(sql, new String[] {"ID"});
				
				pstmt.setString(1, member.getEmail());
				pstmt.setString(2, member.getPassword());
				pstmt.setString(3, member.getName());
				pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegdate()));
				
				return pstmt;
			}
		}, keyHolder);
		
		Number keyValue = keyHolder.getKey(); //쿼리에 사용된 값을 가져옴 Number 클래스는 (byte, short, int, long, float, double)의 슈퍼클래스입니다. 
		member.setId(keyValue.longValue()); // longValue()을 쓸수있는 이유는 Number 클래스 때문이다.
	}
}








