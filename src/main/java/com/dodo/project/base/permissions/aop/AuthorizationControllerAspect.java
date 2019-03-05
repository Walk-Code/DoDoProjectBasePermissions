package com.dodo.project.base.permissions.aop;

import com.dodo.project.base.exception.enums.ResponseStatusEnum;
import com.dodo.project.base.exception.utils.AssertHelper;
import com.dodo.project.base.exception.utils.JsonHelper;
import com.dodo.project.base.exception.utils.ResponseHelper;
import com.dodo.project.base.permissions.config.UserAuthorizationInfoConfiguration;
import com.dodo.project.base.permissions.utils.AuthorizationHelp;
import com.dodo.project.base.web.controller.AbstractController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/*
 * <b>AuthorizationControllerAspect</b></br>
 *
 * <pre>
 * aop - 后台相关控制器权限校验
 * </pre>
 *
 * @Author xqyjjq walk_code@163.com
 * @Date 2018/11/21 14:28
 * @Since JDK 1.8
 */
@Aspect
@Component
public class AuthorizationControllerAspect {
	private static final Logger logger = LoggerFactory.getLogger(AuthorizationControllerAspect.class);

	@Resource
	private UserAuthorizationInfoConfiguration userAuthorizationInfoConfiguration;

	@Resource
	private HttpServletRequest httpServletRequest;

	@Resource
	private AuthorizationHelp authorizationHelp;

	@Around(value = "execution(* com.dodo..*.controller..*(..))")
	public Object validateController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Object result     = null;
		String currentUrl = this.httpServletRequest.getRequestURI();
		logger.info("全局路由鉴权-路由={}", currentUrl);
		// 校验是否在路由白名单内
		for (String whiteUrl : this.userAuthorizationInfoConfiguration.getWhiteAccessUrlMap().keySet()) {
			if (!currentUrl.contains(whiteUrl)) {
				continue;
			}
			logger.info("当前路由={}，whileUrl={}，配置在授权白名单中，无需校验直接通过。", currentUrl, whiteUrl);

			result = proceedingJoinPoint.proceed();

			return result;
		}

		// 拦截当前执行的controller
		AbstractController abstractController = null;
		abstractController = (AbstractController) proceedingJoinPoint.getTarget();

		// 拦截当前执行方法名称
		String methodName = proceedingJoinPoint.getSignature().getName();
		logger.info("当前执行方法名称：{}", methodName);

		// 拦截当前请求的参数
		Object[] params = proceedingJoinPoint.getArgs();
		logger.info("当请求的参数：{}", JsonHelper.toJson(params));

		// 进行权限校验
		boolean isLogin = authorizationHelp.isLogin();
		logger.info("当前路由：{}", currentUrl + " 登录验证路由：" + userAuthorizationInfoConfiguration.getSignInValidateUrl());
		if (!isLogin) {
			logger.info("登陆失败，跳转到登录页");
			if (abstractController.isAjax()) {
				logger.info("使用ajax获取数据，直接返回401");
				ResponseHelper.response(ResponseStatusEnum.STATUS_401.getStatus(), "您可能太久没操作了。登录失效，请重新登录！");
				return result;
			} else {
				abstractController.redirect302(this.userAuthorizationInfoConfiguration.getLoginRoute());
				return result;
			}
		}

		logger.info("当前用户已经登陆，自动跳转到首页。");
		// 路由在白名单中过滤路由
		boolean isInTheWhiteList = this.userAuthorizationInfoConfiguration.getWhiteAccessUrlMap().keySet().contains(currentUrl);
		if (isInTheWhiteList) {
			logger.info("路由在白名单中放行。");
			result = proceedingJoinPoint.proceed();
			return result;
		}

		// 校验路由是否是根路由
		if (currentUrl.equals(this.userAuthorizationInfoConfiguration.getLoginSuccessRoute())) {
			logger.info("校验路由是否是根路由");
			result = proceedingJoinPoint.proceed();
			return result;
		}

		// 校验用户角色
		if (this.authorizationHelp.isRole(new String[]{"root"})) {
			logger.info("当前用户为超级管理员，无需进行验证，默认放行。");
			result = proceedingJoinPoint.proceed();
			return result;
		}

		// 校验路由是否在授权列表中
		if (this.authorizationHelp.isRequireAuthorize(new String[]{currentUrl})) {
			logger.info("请求地址：" + currentUrl + "不在授权列表中，直接放行。");
			result = proceedingJoinPoint.proceed();
			return result;
		}

		// 校验路由权限
		if (this.authorizationHelp.isPermission(new String[]{currentUrl})) {
			logger.info("请求地址：" + currentUrl + "在用户的权限列表中，授权通过可放行。");
			result = proceedingJoinPoint.proceed();
			return result;
		}

		logger.warn("请求地址：" + currentUrl + ",没有权限，直接返回。");
		if (abstractController.isAjax()) {
			AssertHelper.isTrue(false, "您没有权限访问该功能。");
		} else {
			abstractController.renderHtml("<h1>您没有权限操作该功能！</h1>");
		}
		// result = proceedingJoinPoint.proceed();

		return result;
	}


}
