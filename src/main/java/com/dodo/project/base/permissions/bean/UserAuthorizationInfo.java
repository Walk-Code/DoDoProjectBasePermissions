package com.dodo.project.base.permissions.bean;

import java.util.List;
import java.util.Map;

/**
 * <b>UserAuthorizationInfo</b></br>
 *
 * <pre>
 * 用户授权信息
 * </pre>
 *
 * @Author xqyjjq walk_code@163.com
 * @Date 2018/10/29 10:52
 * @Since JDK 1.8
 */
public class UserAuthorizationInfo {
	// 用户权限列表
	private List<String> permissionList;

	// 用户角色列表
	private List<String> roleList;

	// 菜单模块列表
	private List<Map<String, Object>> menuModuleList;

	public List<String> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<String> permissionList) {
		this.permissionList = permissionList;
	}

	public List<String> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<String> roleList) {
		this.roleList = roleList;
	}

	public List<Map<String, Object>> getMenuModuleList() {
		return menuModuleList;
	}

	public void setMenuModuleList(List<Map<String, Object>> menuModuleList) {
		this.menuModuleList = menuModuleList;
	}
}
