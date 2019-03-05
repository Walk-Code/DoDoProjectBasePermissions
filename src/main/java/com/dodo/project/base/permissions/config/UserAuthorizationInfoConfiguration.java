package com.dodo.project.base.permissions.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * <b>UserAuthorizationInfoConfiguration</b></br>
 *
 * <pre>
 * 用户授权信息配置类
 * </pre>
 *
 * @Author xqyjjq walk_code@163.com
 * @Date 2018/10/29 11:01
 * @Since JDK 1.8
 */
@Configuration
public class UserAuthorizationInfoConfiguration {
	// 用户白名单登录map
	protected Map<String, String> whiteAccessUrlMap = new HashMap<>();

	// 登录成功跳转路由
	protected String loginSuccessRoute = "/";

	// 登录页面路由
	protected String loginRoute = "/login";

	// 登录页面验证路由
	protected String signInValidateUrl = "/postLogin";

	// 登出路由
	protected String signOutValidateUrl = "/logout";

	public UserAuthorizationInfoConfiguration() {
		this.whiteAccessUrlMap.put("/logger/api/", "内置日志相关控制器");
		this.whiteAccessUrlMap.put("/api/captcha/createCode", "内置验证码相关控制器");
		this.whiteAccessUrlMap.put(this.loginRoute, "登陆页面");
		this.whiteAccessUrlMap.put(this.signInValidateUrl, "登陆路由");
		this.whiteAccessUrlMap.put(this.signOutValidateUrl, "登出路由");
		// this.whiteAccessUrlMap.put(this.loginSuccessRoute, "登录成功"); // 测试
	}

	public Map<String, String> getWhiteAccessUrlMap() {
		return whiteAccessUrlMap;
	}

	public void setWhiteAccessUrlMap(Map<String, String> whiteAccessUrlMap) {
		this.whiteAccessUrlMap = whiteAccessUrlMap;
	}

	public String getLoginSuccessRoute() {
		return loginSuccessRoute;
	}

	public void setLoginSuccessRoute(String loginSuccessRoute) {
		this.loginSuccessRoute = loginSuccessRoute;
	}

	public String getLoginRoute() {
		return loginRoute;
	}

	public void setLoginRoute(String loginRoute) {
		this.loginRoute = loginRoute;
	}

	public String getSignInValidateUrl() {
		return signInValidateUrl;
	}

	public void setSignInValidateUrl(String signInValidateUrl) {
		this.signInValidateUrl = signInValidateUrl;
	}

	public String getSignOutValidateUrl() {
		return signOutValidateUrl;
	}

	public void setSignOutValidateUrl(String signOutValidateUrl) {
		this.signOutValidateUrl = signOutValidateUrl;
	}
}
