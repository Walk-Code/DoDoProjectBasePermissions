package com.dodo.project.base.permissions.tags;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/*
 * <b>AbstractBaseAuthTag</b></br>
 *
 * <pre>
 * 自定义jsp角色标签
 * </pre>
 *
 * @Author xqyjjq walk_code@163.com
 * @Date 2019/1/7 14:33
 * @Since JDK 1.8
 */
public abstract class AbstractBaseAuthTag extends TagSupport {
	private static final Logger logger = LoggerFactory.getLogger(AbstractBaseAuthTag.class);

	/*
	 * @Description: 获取当前httpSession
	 * @Author: walk_code walk_code@163.com
	 * @Param: []
	 * @return: javax.servlet.http.HttpSession
	 * @Date: 2019/1/7 15:13
	 */
	protected HttpSession getCurrentSession() {
		HttpSession session = this.pageContext.getSession();

		return session;
	}

	/*
	 * @Description: 验证属性
	 * @Author: walk_code walk_code@163.com
	 * @Param: []
	 * @return: void
	 * @Date: 2019/1/7 15:14
	 */
	protected void verifyAttributes() throws JspException {
	}

	@Override
	public int doStartTag() throws JspException {
		logger.info("开始校验权限。。。。");
		verifyAttributes();
		return onDoStartTag();
	}

	protected abstract int onDoStartTag() throws JspException;
}
