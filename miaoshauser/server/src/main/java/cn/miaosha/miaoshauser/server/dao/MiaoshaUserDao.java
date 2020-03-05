package cn.miaosha.miaoshauser.server.dao;

import cn.miaosha.miaoshauser.server.domain.MiaoshaUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

@Service
@Mapper
public interface MiaoshaUserDao {
@Select("select * from user where id=#{id}")
public MiaoshaUser getByid(@Param("id") long id);
@Update("update user set password=#{password} where id = #{id}")
public void updatePassword(MiaoshaUser msuu);
}
