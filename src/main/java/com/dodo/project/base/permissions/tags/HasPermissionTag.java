package com.dodo.project.base.permissions.tags;

import javax.servlet.jsp.JspException;

/*
 * <b>HasPermissionTag</b></br>
 *
 * <pre>
 * 校验权限是否存在标签
 * </pre>
 *
 * @Author xqyjjq walk_code@163.com
 * @Date 2019/1/7 15:50
 * @Since JDK 1.8
 */
public class HasPermissionTag extends AbstractPermissionTag {
	@Override
	protected boolean showTagBody(String var) {
		return isPermission(var);
	}

	@Override
	public int doEndTag() throws JspException {
		return 0;
	}
}
