package com.ezen.question_bank.entity;

 
import java.sql.Timestamp;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

 

@Entity
@Table(name="user")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String user_id;
	
	private String password;
	
	private String tel;
	
	private Timestamp registered_date;

	public UserEntity() {
	}
	
	

	public UserEntity(String user_id, String password, String tel) {
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

	public Timestamp getRegistered_date() {
		return registered_date;
	}

	public void setRegistered_date(Timestamp registered_date) {
		this.registered_date = registered_date;
	}

	
	
}	