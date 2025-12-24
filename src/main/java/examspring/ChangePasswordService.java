package examspring;

import org.springframework.transaction.annotation.Transactional;

public class ChangePasswordService {
	private MemberDao memberDao;
	
	public ChangePasswordService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	@Transactional // 데이터베이스 관련작업일때 주로쓰인다. 
	public void changePassword(String email, String oldpwd, String newpwd) {
		Member member = memberDao.selectByEmail(email);
		if(member == null) {
			throw new MemberNotFoundException();
		}
		member.changePassword(oldpwd, newpwd);
		memberDao.update(member);
	}
}