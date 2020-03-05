package cn.miaosha.loginnicroservice.server.result;

public class CodeMsg {
public static final CodeMsg STOCK_ERROR = new CodeMsg(500310,"库存不足");
public static final CodeMsg REPEAT_ORDER_ERROR = new CodeMsg(500311,"重复下单");
public static final CodeMsg PATH_ERROR = new CodeMsg(500312, "路径不匹配");
public static final CodeMsg MIAOSHA_FAIL = new CodeMsg(500313, "秒杀失败");
public static final CodeMsg YANZHENGMA_ERROR = new CodeMsg(500314, "验证码错误");
public static final CodeMsg XIANLIU_ERROR =new CodeMsg(500315, "操作过于频繁");
private int code;
public static CodeMsg SUCCESS = new CodeMsg(0, "success");
public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s");
//登录模块 5002XX
public static CodeMsg SESSION_ERROR = new CodeMsg(500210, "Session不存在或者已经失效");
public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500211, "登录密码不能为空");
public static CodeMsg MOBILE_EMPTY = new CodeMsg(500212, "手机号不能为空");
public static CodeMsg MOBILE_ERROR = new CodeMsg(500213, "手机号格式错误");
public static CodeMsg MOBILE_NOT_EXIST = new CodeMsg(500214, "手机号不存在");
public static CodeMsg PASSWORD_ERROR = new CodeMsg(500215, "密码错误");
public int getCode() {
	return code;
}
public void setCode(int code) {
	this.code = code;
}
private CodeMsg(int code, String msg) {
	super();
	this.code = code;
	this.msg = msg;
}
public String getMsg() {
	return msg;
}
public void setMsg(String msg) {
	this.msg = msg;
}
private String msg;
public CodeMsg fillArgs(String... msg2) {
	// TODO Auto-generated method stub
	return new CodeMsg(2, "bindException"+msg2);
}
}
