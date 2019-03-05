package com.dodo.project.base.permissions.utils;

import com.dodo.project.base.web.utils.JsonHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <b>AuthorizationHelp</b></br>
 *
 * <pre>
 * 授权辅助类
 * </pre>
 *
 * @Author xqyjjq walk_code@163.com
 * @Date 2018/10/29 11:39
 * @Since JDK 1.8
 */
@Service
public class AuthorizationHelp {
	// 用户权限
	private static final String PERMISSION_KEY = "USER-PERMISSION";

	// 用户角色
	private static final String ROLE_KEY = "USER-ROLE";

	// 用户菜单列表
	private static final String MENU_KEY = "USER-MENU";

	// 用户信息
	private static final String ADMIN_USER_KEY = "USER-INFO";

	// 系统菜单列表
	private static final String SYSTEM_ALL_MENU_KEY = "SYSTEM-ALL-MENU-KEY";

	@Resource
	private HttpServletRequest httpServletRequest;

	/*
	 * @Description: 获取系统所有菜单列表
	 * @Author: walk_code walk_code@163.com
	 * @Param: []
	 * @return: java.util.List<java.lang.String>
	 * @Date: 2018/10/29 11:48
	 */
	public List<String> getSystemAllMemuList() {
		return (List<String>) this.httpServletRequest.getSession().getAttribute(SYSTEM_ALL_MENU_KEY);
	}

	/*
	 * @Description: 设置系统菜单
	 * @Author: walk_code walk_code@163.com
	 * @Param: [systemAllMenuList]
	 * @return: void
	 * @Date: 2018/10/29 11:49
	 */
	public void setSystemAllMenuList(List<String> systemAllMenuList) {
		this.httpServletRequest.getSession(true).setAttribute(SYSTEM_ALL_MENU_KEY, systemAllMenuList);
	}

	/*
	 * @Description: 获取菜单列表
	 * @Author: walk_code walk_code@163.com
	 * @Param: []
	 * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 * @Date: 2018/10/29 11:53
	 */
	public List<Map<String, Object>> getMenuMoudleList() {
		return (List<Map<String, Object>>) this.httpServletRequest.getSession(true).getAttribute(MENU_KEY);
	}

	/*
	 * @Description: 设置菜单列表
	 * @Author: walk_code walk_code@163.com
	 * @Param: [menuModuleList]
	 * @return: void
	 * @Date: 2018/10/29 11:55
	 */
	public void setMenuModuleList(List<Map<String, Object>> menuModuleList) {
		this.httpServletRequest.getSession(true).setAttribute(MENU_KEY, menuModuleList);
	}

	/*
	 * @Description: 设置用户信息
	 * @Author: walk_code walk_code@163.com
	 * @Param: [object]
	 * @return: void
	 * @Date: 2018/10/29 12:08
	 */
	public void setUserInfo(Object object) {
		this.httpServletRequest.getSession(true).setAttribute(ADMIN_USER_KEY, JsonHelper.toJson(object));
	}

	/*
	 * @Description: 获取用户信息
	 * @Author: walk_code walk_code@163.com
	 * @Param: [type]
	 * @return: T
	 * @Date: 2018/10/29 12:11
	 */
	public <T> T getUserInfo(Class<T> type) {
		String userInfoJsonStr = (String) this.httpServletRequest.getSession(true).getAttribute(ADMIN_USER_KEY);
		if (StringUtils.isBlank(userInfoJsonStr)) {
			return null;
		}

		return JsonHelper.parse(userInfoJsonStr, type);
	}

	/*
	 * @Description: 设置用户权限
	 * @Author: walk_code walk_code@163.com
	 * @Param: [permissionList]
	 * @return: void
	 * @Date: 2018/10/29 12:13
	 */
	public void setPermissionList(List<String> permissionList) {
		this.httpServletRequest.getSession(true).setAttribute(PERMISSION_KEY, permissionList);
	}
	
	/* 
	* @Description: 获取用户权限 
	* @Author: walk_code walk_code@163.com
	* @Param: [] 
	* @return: java.util.List<java.lang.String>  
	* @Date: 2019/1/10 10:33
	*/ 
	public List<String> getPermissionList(){
		return (List<String>) this.httpServletRequest.getSession(true).getAttribute(PERMISSION_KEY);
	}


	/*
	 * @Description: 设置角色列表
	 * @Author: walk_code walk_code@163.com
	 * @Param: [roleList]
	 * @return: void
	 * @Date: 2018/10/29 13:43
	 */
	public void setRoleList(List<String> roleList) {

	}

	/*
	 * @Description: 是否登录
	 * @Author: walk_code walk_code@163.com
	 * @Param: []
	 * @return: boolean
	 * @Date: 2018/10/29 13:44
	 */
	public boolean isLogin() {
		return this.httpServletRequest.getSession(true).getAttribute(ADMIN_USER_KEY) != null;
	}

	/*
	 * @Description: List中是否存在元素
	 * @Author: walk_code walk_code@163.com
	 * @Param: []
	 * @return: boolean
	 * @Date: 2018/10/29 13:47
	 */
	private static boolean isExistInList(List<String> list, String... strings) {
		if (null != list) {
			for (String strList : list) {
				List<String> stringList = Arrays.asList(strList.split(","));
				for (String str : strings) {
					if (stringList.contains(str)) {
						return true;
					}
				}
			}
		}

		return false;
	}

	/*
	 * @Description: 是否需要授权
	 * @Author: walk_code walk_code@163.com
	 * @Param: [p]
	 * @return: boolean
	 * @Date: 2018/10/29 13:55
	 */
	public boolean isRequireAuthorize(String... p) {
		return isExistInList(this.getSystemAllMemuList(), p);
	}

	/*
	 * @Description: node是否可以访问
	 * @Author: walk_code walk_code@163.com
	 * @Param: []
	 * @return: boolean
	 * @Date: 2018/10/29 13:56
	 */
	public boolean isPermission(String... p) {
		return isPermission(this.httpServletRequest.getSession(), p);
	}

	/*
	 * @Description: node是否可以访问
	 * @Author: walk_code walk_code@163.com
	 * @Param: [session, p]
	 * @return: boolean
	 * @Date: 2018/10/29 14:03
	 */
	public static boolean isPermission(HttpSession session, String... p) {
		List permissionList = (List) session.getAttribute(PERMISSION_KEY);

		return isRole(session, "root") || isExistInList(permissionList, p);
	}

	/*
	 * @Description: 角色是否存在
	 * @Author: walk_code walk_code@163.com
	 * @Param: [r]
	 * @return: boolean
	 * @Date: 2018/10/29 14:04
	 */
	public boolean isRole(String... r) {
		return isRole(this.httpServletRequest.getSession(), r);
	}

	/*
	 * @Description: 角色是否存在
	 * @Author: walk_code walk_code@163.com
	 * @Param: [session, r]
	 * @return: boolean
	 * @Date: 2018/10/29 14:06
	 */
	public static boolean isRole(HttpSession session, String... r) {
		List roleList = (List) session.getAttribute(ROLE_KEY);

		return isExistInList(roleList, r);
	}
}
