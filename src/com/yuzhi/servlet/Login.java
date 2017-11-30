package com.yuzhi.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yuzhi.dao.UserDAO;
import com.yuzhi.daoimp.UserDaoImpl;
import com.yuzhi.listener.RepeatLogin;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 对于客户端发送过来的请求我们可以进行获取参数
		String uname = request.getParameter("uname");
		String upwd = request.getParameter("upwd");
		// 这里我就直接调用dao层的方法访问数据库进行比较
		UserDAO userDAO = new UserDaoImpl();
		boolean i = userDAO.findUserByPwdAndName(uname, upwd);
		if (i) {
			// 登陆成功----进入到用户页面显示当前的登录人数
			HttpSession session = request.getSession();
			session.setAttribute("user", uname);
			// 这里判断一下是否重复登录
			boolean login = RepeatLogin.isLogin(uname);
			if (!login) {
				// 进入到主页面
				response.sendRedirect("Welcome.jsp");

			} else {
				// 重定向到登录页面，并提示重复登录

				session.setAttribute("mess", "重复登录。。。");
				response.sendRedirect("login.jsp");
			}

		} else {
			// 登录失败，返回登录页面
			// 并给个提示信息
			HttpSession session = request.getSession();
			session.setAttribute("mess", "密码错误，请重新输入");
			response.sendRedirect("login.jsp");

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
