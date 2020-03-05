package cn.miaosha.miaoshauser.server.form;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

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
