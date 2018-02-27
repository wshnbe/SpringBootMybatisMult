package com.basjoo.SpringBootMybatisMult.module.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

	@Select("select * from user where name = #{name}")
	User findByName(@Param("name") String name);
	
	/* @param 传递参数 */
	@Insert("insert into user(age,name) values(#{age},#{name})")
	int insertByParam(@Param("age") int age,@Param("name") String name);
	
	/* Map传递参数 */
	@Insert("insert into user(age,name) values(#{age,jdbcType=INTEGER},#{name,jdbcType=VARCHAR})")
	int insertByMap(Map<String,Object> map);
	
	/* 对象当做传递参数 */
	@Insert("insert into user(age,name) values(#{age},#{name})")
	int insertByObject(User user);
	
	/* 更改 */
	@Update("update user set name = #{name} where age = #{age}")
	int update(User user);
	
	/* 删除 */
	@Delete("delete from user where id = #{id}")
	int delete(@Param("id") Long id);
	
	/* 进行列映射 */
	@Results({
		@Result(property="age" , column="age"),
		@Result(property="name", column="name")
	})
	@Select("select age,name from user")
	List<User> findAll();
}
