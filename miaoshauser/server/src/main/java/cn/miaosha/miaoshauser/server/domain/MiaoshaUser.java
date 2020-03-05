package cn.miaosha.miaoshauser.server.domain;

import java.util.Date;

public class MiaoshaUser {
private long id;
private String nickname;
private String password;
private String salt;
private String head;
private Date redister_date;
private Date last_login_date;
private int login_count;
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getNickname() {
	return nickname;
}
public void setNickname(String nickname) {
	this.nickname = nickname;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getSalt() {
	return salt;
}
public void setSalt(String salt) {
	this.salt = salt;
}
public String getHead() {
	return head;
}
public void setHead(String head) {
	this.head = head;
}
public Date getRedister_date() {
	return redister_date;
}
public void setRedister_date(Date redister_date) {
	this.redister_date = redister_date;
}
public Date getLast_login_date() {
	return last_login_date;
}
public void setLast_login_date(Date last_login_date) {
	this.last_login_date = last_login_date;
}
public int getLogin_count() {
	return login_count;
}
public void setLogin_count(int login_count) {
	this.login_count = login_count;
}
@Override
public String toString() {
	return "MiaoshaUser [id=" + id + ", nickname=" + nickname + ", password="
			+ password + ", salt=" + salt + ", head=" + head
			+ ", redister_date=" + redister_date + ", last_login_date="
			+ last_login_date + ", login_count=" + login_count + "]";
}

}
