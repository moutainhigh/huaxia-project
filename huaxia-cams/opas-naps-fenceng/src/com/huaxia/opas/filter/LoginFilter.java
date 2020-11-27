package com.huaxia.opas.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.huaxia.opas.cache.service.ITokenService;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("开始进入过滤器");		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String url = req.getRequestURL().toString();
		String basepath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/opas-topui/";
		// 包含这些的请求将不通过过滤器
		if (url.contains("SmartVoiceServlet") ||url.contains("PadPatchboltHttpServer") || url.contains("PadSetStausHttpServer") || url.contains("PadHttpServer") || url.contains("login") || url.contains("logout")
				|| url.contains("userRoleOrg") || url.contains("userRelationInfo") || url.contains("setPassword")) {
			chain.doFilter(request, response);
		} else {
			HttpSession session = req.getSession();
			String userId = "";
			if( session.getAttribute("userId") == null ){
				PrintWriter writer = null;
				try {
					res.setContentType("text/html;charset=utf-8");
					writer = res.getWriter();
					//writer.write("location.href='/opas-topui/index.html'");
					writer.write("location.href='/opas-topui/verifyLogin.html'");
					writer.flush();
				} catch (IOException e1) {
				} finally {
					if (writer != null) {
						writer.close();
					}
				}
				return ;
			}else{
				userId = session.getAttribute("userId").toString();				
			}
			ServletContext sc = req.getSession().getServletContext();
			XmlWebApplicationContext cxt = (XmlWebApplicationContext) WebApplicationContextUtils
					.getWebApplicationContext(sc);
			String type = req.getHeader("X-Requested-With");
			if (null != cxt && null != cxt.getBean("tokenService")) {
				ITokenService tokenService = (ITokenService) cxt.getBean("tokenService");
				try {
					String uuid = tokenService.getToken(userId);
					String guid = session.getAttribute("guid").toString();
					String guid2 = tokenService.getGuid(userId);
					if (null == uuid || "".equals(uuid) || null == guid || "".equals(guid) || !guid.equals(guid2)) {
						if ("XMLHttpRequest".equalsIgnoreCase(type)) {
							res.setHeader("sessionstatus", "303");
							res.setHeader("CONTENTPATH", basepath);
							res.getWriter().print("您已被踢下线");
						}
						return;
					} else {
						chain.doFilter(request, response);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("结束过滤器调用");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}
