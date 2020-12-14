package com.lisz.dao;

import com.lisz.bean.User;

public interface UserDao {
	// 可以写@Select/@Insert等注解，但是一旦xml文件里也有相同id（xml中id为这里的方法名）的设置，则会报错
	public Integer save(User user);
	public Integer update(User user);
	public Integer deleteById(Integer id);
	public User findById(Integer id);
}
