package examspring;

public class RegisterCommand {
	
	private String email;
	private String password;
	private String confirmPassword;
	private String name;
	
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
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//비밀번호 확인 기능
	public boolean isPasswordEqualsToConfirmPassword() { // 보통 boolean get은 is를 쓴다. 암묵적인규칙 
		return password.equals(confirmPassword);
	}
	
}





