package com.beet.test.HelloQuartz;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
/**
 * 这还没使用
 * @author bee
 *
 */
public class CustomRelam extends AuthorizingRealm{
	
	Map<String,String> userMap = new HashMap<String,String>();
	
	{
		userMap.put("bee", "123456");
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 1.从主题传来的认证信息中，获得用户名
		String userName = (String) token.getPrincipal();
		//2.通过用户名到数据库中获取凭证
		String password = getPasswordByUserName(userName);
		if(password == null){
			return null;
		}
		SimpleAuthenticationInfo authentication = new SimpleAuthenticationInfo("bee","123456","customRealm");
		return authentication;
	}

	/**
	 *  模拟数据库
	 * @param userName
	 * @return
	 */
	private String getPasswordByUserName(String userName) {
		return userMap.get(userName);
	}

}
