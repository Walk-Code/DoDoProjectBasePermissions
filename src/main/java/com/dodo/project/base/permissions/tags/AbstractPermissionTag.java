package com.dodo.project.base.permissions.tags;

import com.dodo.project.base.permissions.utils.AuthorizationHelp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;

/*
 * <b>AbstractPermissionTag</b></br>
 *
 * <pre>
 * 自定义jsp权限标签
 * </pre>
 *
 * @Author xqyjjq walk_code@163.com
 * @Date 2019/1/7 15:45
 * @Since JDK 1.8
 */
public abstract class AbstractPermissionTag extends AbstractBaseAuthTag{
	private String name;
	private static final Logger logger = LoggerFactory.getLogger(AbstractPermissionTag.class);

	@Override
	protected int onDoStartTag() throws JspException {
		boolean show = this.showTagBody(this.getName());
		logger.info("是否含有该权限："+show+" 需要校验的路由："+this.getName());
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
	protected boolean isPermission(String p) {
		return AuthorizationHelp.isPermission(this.getCurrentSession(), p);
	}

	protected abstract boolean showTagBody(String var);

}
