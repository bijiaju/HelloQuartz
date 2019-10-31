package com.beet.test.HelloQuartz;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
/**
 * 测试不同数据源
 * @author bee
 *
 */
public class IniRealmTest {
	
	
	
	@Test
	public void testAuthencationTest(){
		IniRealm iniRealm = new IniRealm("classpath:user.ini");
		//1.构建SecurityManager环境
		DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
		defaultSecurityManager.setRealm(iniRealm);
		
		// 加密
		/*HashedCredentialsMatcher matcher = new  HashedCredentialsMatcher();
		matcher.setHashAlgorithmName("md5");
		matcher.setHashIterations(1);//加密次数
		iniRealm.setCredentialsMatcher(matcher);*/
		
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
