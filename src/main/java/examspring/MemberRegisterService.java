package examspring;

import java.time.LocalDateTime;

import org.springframework.transaction.annotation.Transactional;

public class MemberRegisterService {
	
	private MemberDao memberDao;
	
	public MemberRegisterService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	@Transactional
	public void regist(RegisterCommand cmd) {
		Member member = memberDao.selectByEmail(cmd.getEmail());
		if(member != null) {
			throw new AlreadyExistingMemberException(cmd.getEmail()+"은 존재하는 아이디");
		}
		Member newMember = new Member(
				cmd.getEmail(), 
				cmd.getPassword(), 
				cmd.getName(), 
				LocalDateTime.now());

		memberDao.insert(newMember);
	}
}
