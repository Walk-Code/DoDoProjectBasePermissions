package com.dodo.project.base.permissions.tags;

import com.dodo.project.base.permissions.utils.AuthorizationHelp;

import javax.servlet.jsp.JspException;

/*
 * <b>AbstractRoleTag</b></br>
 *
 * <pre>
 * 角色验证标签
 * </pre>
 *
 * @Author xqyjjq walk_code@163.com
 * @Date 2019/1/7 15:17
 * @Since JDK 1.8
 */
public abstract class AbstractRoleTag extends AbstractBaseAuthTag {
	private String name;

	@Override
	protected int onDoStartTag() throws JspException {
		boolean show = this.showTagBody(this.getName());
		if (show) {
			return 1;
		}

		return 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	 * @Description: 校验角色是否在权限角色列表中
	 * @Author: walk_code walk_code@163.com
	 * @Param: [p]
	 * @return: boolean
	 * @Date: 2019/1/7 15:23
	 */
	protected boolean isRole(String p) {
		return AuthorizationHelp.isRole(this.getCurrentSession(), p);
	}

	protected abstract boolean showTagBody(String var);
}
