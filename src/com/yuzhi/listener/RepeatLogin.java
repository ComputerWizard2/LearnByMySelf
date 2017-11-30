package com.yuzhi.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class RepeatLogin
 *
 */
/**
 * �����������Ҫ�����û����ظ��ĵ�¼�Ľ��м����������ظ���¼���û������������޷�����servlet��̨�ķ�������
 * 
 * @author Administrator
 *
 */
@WebListener
public class RepeatLogin implements HttpSessionAttributeListener, HttpSessionListener {
	// ��������һ����̬�ĳ���,����û���¼���������Ǿͽ��û���ӵ�map�����У����ÿ���û��ٴε�¼���ǾͿ��Ա���map�����Ƿ���ڸ�user����
	private static final String USER = "user";
	// �Ե�ǰ�ĵ�¼ͳ�Ƶ��û�����һ��
	private static int count = 0;
	// ���ǻ���Ҫ����һ��map�ļ���
	private static Map<String, HttpSession> map = null;

	/**
	 * Default constructor.
	 */

	public RepeatLogin() {
		map = new HashMap<>();
	}

	/**
	 * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
	 */
	public void attributeAdded(HttpSessionBindingEvent se) {
		// ���û���¼��ʱ��session����һ��������attribute ������

		if (USER.equals(se.getName())) {
			map.put(USER, se.getSession());

		}

	}

	/**
	 * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
	 */
	public void attributeRemoved(HttpSessionBindingEvent se) {
		// ����ͻ����˳���¼����Ҫ��map�еĿͻ������Ƴ����ң���session�����������
		HttpSession session = map.get(se.getName());

		map.remove(se.getName());

	}

	/**
	 * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
	 */
	public void attributeReplaced(HttpSessionBindingEvent se) {
		// ����ظ�����ֵ����Ը��ͻ���һ��������Ϣ�����߸�������һ����Ϣ
		if (USER.equals(se.getName())) {
			HttpSession session = se.getSession();
			session.setAttribute("mess", "�ظ���¼��������");
		}
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// session�������ˣ�Ҳ����һ���û��Ѿ���¼��
		count++;
		HttpSession session = se.getSession();
		session.setAttribute("count ", count);

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// ���sesssion�����㣬Ҳ�����û��˳���¼
		count--;
		HttpSession session = se.getSession();
		session.setAttribute("count", count);

	}

	// ���Ͻ����Ƕ�map�ļ��ϵ����ӻ��߼��٣�������û�ж��û��Ƿ��ظ��ĵ�¼�����ж�
	// �������������Լ�һ��������ʵ�ֶԳ�ũ���¼���û��ĵ�¼
	public static boolean isLogin(String user) {
		if (map.containsKey(user)) {

			return true;

		}

		return false;

	}

}
