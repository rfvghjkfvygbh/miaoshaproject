package cn.miaosha.miaosha.server.result;

public class Result<T> {
private T data;
private String msg;
private int code;
public Result(T data2) {
	// TODO Auto-generated constructor stub
	data=data2;
	code=0;
	msg="success";
}
public Result(CodeMsg cm) {
	// TODO Auto-generated constructor stub
	if(cm==null)
		return ;
	code=cm.getCode();
	msg=cm.getMsg();
}
public static <T> Result<T> success(T data){
	return new Result<T>(data);
}
public static <T> Result<T> fail(CodeMsg cm){
	return new Result<T>(cm);
}
public T getData() {
	return data;
}
public void setData(T data) {
	this.data = data;
}
public String getMsg() {
	return msg;
}
public void setMsg(String msg) {
	this.msg = msg;
}
public int getCode() {
	return code;
}
public void setCode(int code) {
	this.code = code;
}
}
