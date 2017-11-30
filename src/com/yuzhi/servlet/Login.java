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
		// ���ڿͻ��˷��͹������������ǿ��Խ��л�ȡ����
		String uname = request.getParameter("uname");
		String upwd = request.getParameter("upwd");
		// �����Ҿ�ֱ�ӵ���dao��ķ����������ݿ���бȽ�
		UserDAO userDAO = new UserDaoImpl();
		boolean i = userDAO.findUserByPwdAndName(uname, upwd);
		if (i) {
			// ��½�ɹ�----���뵽�û�ҳ����ʾ��ǰ�ĵ�¼����
			HttpSession session = request.getSession();
			session.setAttribute("user", uname);
			// �����ж�һ���Ƿ��ظ���¼
			boolean login = RepeatLogin.isLogin(uname);
			if (!login) {
				// ���뵽��ҳ��
				response.sendRedirect("Welcome.jsp");

			} else {
				// �ض��򵽵�¼ҳ�棬����ʾ�ظ���¼

				session.setAttribute("mess", "�ظ���¼������");
				response.sendRedirect("login.jsp");
			}

		} else {
			// ��¼ʧ�ܣ����ص�¼ҳ��
			// ��������ʾ��Ϣ
			HttpSession session = request.getSession();
			session.setAttribute("mess", "�����������������");
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
