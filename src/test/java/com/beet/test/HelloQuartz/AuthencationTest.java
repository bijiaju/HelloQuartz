package com.beet.test.HelloQuartz;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;


/**
 *  shrio认证测试
 * @author bee
 *
 */
public class AuthencationTest {
	
	 SimpleAccountRealm simpleAccountRealm =new  SimpleAccountRealm();//为了做演示，使用了simpleRealm  后续会使用jdbcRealm
	// JdbcRealm realm = new JdbcRealm();
	
	 /**
	  * 添加账户，到时候可以加载数据库
	  */
	@Before
	public void addUser(){
		simpleAccountRealm.addAccount("bee", "123456","admin");//提前注册一个账户
	}

	@Test
	public void testAuthencationTest(){
		//1.构建SecurityManager环境
		DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
		defaultSecurityManager.setRealm(simpleAccountRealm);//设置realm
		
		//2.主体提交认证请求
		SecurityUtils.setSecurityManager(defaultSecurityManager);
		Subject subject = SecurityUtils.getSubject();
		
		UsernamePasswordToken token = new UsernamePasswordToken("bee", "123456");
		subject.login(token);
		
		System.out.println("isAuthencation--"+subject.isAuthenticated());
		//检查权限
		subject.checkRoles("admin");
		
		subject.logout();
		System.out.println("isAuthencation--"+subject.isAuthenticated());
	}
}
