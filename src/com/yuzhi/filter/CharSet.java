package com.yuzhi.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class CharSet
 */
// ��ע�������ñ���
@WebFilter(urlPatterns = "/*", initParams = { @WebInitParam(name = "charset", value = "utf-8") })
public class CharSet implements Filter {
	// ������ò��������ǽ�������Ϊ��Ա������
	private FilterConfig fConfig;

	/**
	 * Default constructor.
	 */
	public CharSet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	/**
	 * �����������Ҫ�ǶԱ�������ã����Բ��õ�ȫ����
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// �Ƚ�request��responseȫ��ǿתΪhttp~������
		HttpServletRequest request2 = (HttpServletRequest) request;
		HttpServletResponse response2 = (HttpServletResponse) response;
		// ��ȡ����
		String charset = fConfig.getInitParameter("charset");
		// ���ñ���
		request2.setCharacterEncoding(charset);
		response2.setCharacterEncoding("text/html;charset=" + charset);
		// �����������
		chain.doFilter(request2, response2);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.fConfig = fConfig;
	}

}
