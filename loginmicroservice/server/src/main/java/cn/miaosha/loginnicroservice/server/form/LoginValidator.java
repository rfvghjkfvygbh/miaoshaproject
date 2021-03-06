package cn.miaosha.loginnicroservice.server.form;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


import cn.miaosha.loginnicroservice.server.utils.ValidatorUtil;
import com.alibaba.druid.util.StringUtils;

public class LoginValidator implements ConstraintValidator<IsMobile, String>{
	@Override
	public void initialize(IsMobile constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(value))
			return false;
		return ValidatorUtil.isMobile(value);
	}

}
