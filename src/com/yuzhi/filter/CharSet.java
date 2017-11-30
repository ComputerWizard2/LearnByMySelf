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
// 在注解里配置编码
@WebFilter(urlPatterns = "/*", initParams = { @WebInitParam(name = "charset", value = "utf-8") })
public class CharSet implements Filter {
	// 方便调用参数，我们将参数作为成员变量了
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
	 * 这个过滤器主要是对编码的设置，所以采用的全过虑
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 先将request和response全部强转为http~的类型
		HttpServletRequest request2 = (HttpServletRequest) request;
		HttpServletResponse response2 = (HttpServletResponse) response;
		// 获取编码
		String charset = fConfig.getInitParameter("charset");
		// 设置编码
		request2.setCharacterEncoding(charset);
		response2.setCharacterEncoding("text/html;charset=" + charset);
		// 放入过滤链中
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
