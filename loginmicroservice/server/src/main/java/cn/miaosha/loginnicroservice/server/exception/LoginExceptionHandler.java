package cn.miaosha.loginnicroservice.server.exception;

import java.util.List;

import cn.miaosha.loginnicroservice.server.result.CodeMsg;
import cn.miaosha.loginnicroservice.server.result.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
@ResponseBody
public class LoginExceptionHandler {
@ExceptionHandler(value=Exception.class)
public Result<String> exceptionHandler(Exception e){
	if(e instanceof GlobalException){
		return Result.fail(((GlobalException) e).getCm());
	}
	if(e instanceof BindException) {
		BindException ex = (BindException)e;
		List<ObjectError> errors = ex.getAllErrors();
		ObjectError error = errors.get(0);
		String msg = error.getDefaultMessage();
		return Result.fail(CodeMsg.BIND_ERROR.fillArgs(msg));
	}
	e.printStackTrace();
		return Result.fail(CodeMsg.SERVER_ERROR);
}
}
