package com.dodo.project.base.permissions.tags;

import javax.servlet.jsp.JspException;

/*
 * <b>HasRoleTag</b></br>
 *
 * <pre>
 * 校验角色是否存在标签
 * </pre>
 *
 * @Author xqyjjq walk_code@163.com
 * @Date 2019/1/7 15:26
 * @Since JDK 1.8
 */
public class HasRoleTag extends AbstractRoleTag{
	@Override
	protected boolean showTagBody(String var) {
		return this.isRole(var);
	}

	@Override
	public int doEndTag() throws JspException {
		return 0;
	}
}
