package cn.miaosha.loginnicroservice.server.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class LoginForm {
@NotNull
@IsMobile
private String mobile;
@NotNull
@Length(min=32)
private String password;
public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
@Override
public String toString() {
	// TODO Auto-generated method stub
	return super.toString();
}

}
