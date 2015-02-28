package com.zeekie.stock.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginFilter implements Filter {

	Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * 需要排除的页面
	 */
	private String excludedPages;

	private String[] excludedPageArray;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		excludedPages = filterConfig.getInitParameter("excludedPages");
		if (StringUtils.isNotEmpty(excludedPages)) {
			excludedPageArray = excludedPages.split(",");
		}
		return;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String requestUri = httpRequest.getRequestURI();
		boolean isExcludedPage = false;
		if (log.isDebugEnabled()) {
			log.debug("doFilter for " + requestUri);
		}
		for (String page : excludedPageArray) {// 判断是否在过滤url之外
			if (((HttpServletRequest) request).getServletPath().equals(page)) {
				isExcludedPage = true;
				break;
			}
		}

		if (isExcludedPage) {
			chain.doFilter(request, response);
		} else {
			HttpSession session = httpRequest.getSession(true);
			if (session.getAttribute("user") == null
					|| session.getAttribute("user") == "") {
				if (log.isDebugEnabled()) {
					log.debug("user should login first. ");
				}
				httpResponse.sendRedirect(httpRequest.getContextPath()
						+ "/index.jsp");
			} else {
				chain.doFilter(request, response);
				return;
			}
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
