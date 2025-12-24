package examspring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

public class MemberRowMapper implements RowMapper<Member>{ // rowNum은 일종의 DB의 데이터베이스에서 시퀀스(sequence) 와 비슷하다. 각행마다 고유 번호를 지정함 
	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		Member member = new Member(
				rs.getString("EMAIL"),
				rs.getString("PASSWORD"),
				rs.getString("NAME"),
				LocalDateTime.ofInstant(
					new Date(rs.getDate("REGDATE").getTime()).toInstant(),
					ZoneId.systemDefault()) //쉽게 말해, 데이터베이스에서 가져온 일자 값을 시스템 시간대에 맞추어 LocalDateTime 객체로 변환하는 코드입니다.
				);
		member.setId(rs.getLong("ID"));
		return member;
	}
}
