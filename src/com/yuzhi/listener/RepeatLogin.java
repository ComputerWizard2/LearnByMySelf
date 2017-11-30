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
 * 这个监听器主要对于用户的重复的登录的进行监听，对于重复登录的用户给出反馈，无法到达servlet后台的服务器中
 * 
 * @author Administrator
 *
 */
@WebListener
public class RepeatLogin implements HttpSessionAttributeListener, HttpSessionListener {
	// 这里设置一个静态的常量,如果用户登录进来，我们就将用户添加到map集合中，最后每当用户再次登录我们就可以遍历map集合是否存在该user对象
	private static final String USER = "user";
	// 对当前的登录统计的用户进行一个
	private static int count = 0;
	// 我们还需要创建一个map的集合
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
		// 当用户登录的时候session对象一定会增加attribute 的属性

		if (USER.equals(se.getName())) {
			map.put(USER, se.getSession());

		}

	}

	/**
	 * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
	 */
	public void attributeRemoved(HttpSessionBindingEvent se) {
		// 如果客户对退出登录，就要对map中的客户进行移除并且，对session对象进行销毁
		HttpSession session = map.get(se.getName());

		map.remove(se.getName());

	}

	/**
	 * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
	 */
	public void attributeReplaced(HttpSessionBindingEvent se) {
		// 如果重复放置值，则对给客户端一个反馈信息，或者给过滤器一个消息
		if (USER.equals(se.getName())) {
			HttpSession session = se.getSession();
			session.setAttribute("mess", "重复登录。。。。");
		}
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// session被创建了，也就是一个用户已经登录了
		count++;
		HttpSession session = se.getSession();
		session.setAttribute("count ", count);

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// 如果sesssion被销汇，也就是用户退出登录
		count--;
		HttpSession session = se.getSession();
		session.setAttribute("count", count);

	}

	// 以上仅仅是对map的集合的增加或者减少，而不是没有对用户是否重复的登录做出判断
	// 所以我在这里自检一个方法来实现对冲农夫登录的用户的登录
	public static boolean isLogin(String user) {
		if (map.containsKey(user)) {

			return true;

		}

		return false;

	}

}
