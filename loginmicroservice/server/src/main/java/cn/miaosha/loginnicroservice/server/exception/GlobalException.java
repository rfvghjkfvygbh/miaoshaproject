package cn.miaosha.loginnicroservice.server.exception;

import cn.miaosha.loginnicroservice.server.result.CodeMsg;

public class GlobalException extends RuntimeException{
//	private static final long serialVersionUID=1l;
private CodeMsg cm;
public GlobalException(CodeMsg cm){
	this.cm=cm;
}
public CodeMsg getCm() {
	return cm;
}


}
