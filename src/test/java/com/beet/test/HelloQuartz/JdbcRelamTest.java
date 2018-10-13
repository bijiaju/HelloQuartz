package com.beet.test.HelloQuartz;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import com.alibaba.druid.pool.DruidDataSource;

public class JdbcRelamTest {
	
	DruidDataSource dataSource = new DruidDataSource();
	
	{
		dataSource.setUrl("jdbc:mysql://localhost:3306/test");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
	}
	
	@Test
	public void testAuthencationTest(){
		JdbcRealm jdbcRelam = new JdbcRealm();
		jdbcRelam.setDataSource(dataSource);
		jdbcRelam.setPermissionsLookupEnabled(true);//记得把这个开关开启
		
		String sql = "select password from test_user where user_name = ?";
		jdbcRelam.setAuthenticationQuery(sql);//执行自己的查询语句
		
		//1.构建SecurityManager环境
		DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
		defaultSecurityManager.setRealm(jdbcRelam);
	//	defaultSecurityManager.setr
		//2.主体提交认证请求
		SecurityUtils.setSecurityManager(defaultSecurityManager);
		Subject subject = SecurityUtils.getSubject();
		
		UsernamePasswordToken token = new UsernamePasswordToken("bee", "123456");
		subject.login(token);
		
		System.out.println("isAuthencation--"+subject.isAuthenticated());
		subject.checkRole("role1");//是否有这个角色
		subject.checkPermission("user:delete");//是否有这个权限
		
		subject.logout();
		System.out.println("isAuthencation--"+subject.isAuthenticated());
	}

}
