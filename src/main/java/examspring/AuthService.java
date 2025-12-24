package examspring;

public class AuthService {
	private MemberDao memberDao;
	
	public void setMemberDao(MemberDao memberDao) {//Spring은 setter 메서드를 우선시하므로, XML에서 프로퍼티를 설정해도 setter 메서드에서 설정한 값으로 대체됩니다.
		this.memberDao = memberDao;
	}
	
	public AuthInfo authenticate(String email, String password) {
		Member member = memberDao.selectByEmail(email);
		if(member == null) {
			throw new IdPasswordNotMatchingException();
		}
		if(!member.matchPassword(password)) {
			throw new IdPasswordNotMatchingException();
		}
		
		return new AuthInfo(
				member.getId(), 
				member.getEmail(),
				member.getName());
	}
}




