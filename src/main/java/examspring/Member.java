package examspring;

import java.time.LocalDateTime;

public class Member {
	private Long id;
	private String email;
	private String password;
	private String name;
	private LocalDateTime regdate;
	
	public Member(String email, String password, String name, LocalDateTime regdate) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.regdate = regdate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getRegdate() {
		return regdate;
	}

	public void setRegdate(LocalDateTime regdate) {
		this.regdate = regdate;
	}
	
	//암호 변경
	public void changePassword(String oldpwd, String newpwd) {
		if(!password.equals(oldpwd)) {
			throw new IdPasswordNotMatchingException();
		}
		this.password = newpwd;
	}

	public boolean matchPassword(String password) {
		return this.password.equals(password);
	}
	
	@Override
	public String toString() {
		return "Member [id=" + id + ", email=" + email + ", password=" + password + ", name=" + name + ", regdate="
				+ regdate + "]";
	}
	
	
}





