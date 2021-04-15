package com.ezen.question_bank.viewmodel;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserForm {
	
	@NotBlank(message= "필수 입력입니다")
	@Email(message= "이메일 주소가 아닙니다.")
	private String user_id;
	
	@Size(min= 4, message= "비밀번호는 4자리 이상입니다.")
	private String password;
	
	// @Pattern(regexp= "^01(?:0|1|[6-9])-(?:[0-9]{3}|[0-9]{4})-[0-9]{4}$", message= " \'01X-XXXX-XXXX\' 형식으로 입력하세요")
	@Pattern(regexp= "^010-[0-9]{4}-[0-9]{4}$", message= " \'010-XXXX-XXXX\' 형식입니다.")
	private String tel;

	public UserForm() {
	}

	public UserForm(@NotBlank(message = "필수 입력입니다") @Email(message = "이메일 주소가 아닙니다.") String user_id,
			@Size(min = 4, message = "비밀번호는 4자리 이상입니다.") String password,
			@Pattern(regexp = "^010-[0-9]{4}-[0-9]{4}$", message = " '010-XXXX-XXXX' 형식입니다.") String tel) {
		super();
		this.user_id = user_id;
		this.password = password;
		this.tel = tel;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}
