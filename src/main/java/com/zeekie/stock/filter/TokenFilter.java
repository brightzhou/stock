package com.zeekie.stock.filter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sitong.thinker.common.error.Message;
import sitong.thinker.common.util.JsonUtils;
import sitong.thinker.common.util.codec.CodecException;
import sitong.thinker.common.util.codec.TokenUtils;

import com.zeekie.stock.Constants;
import com.zeekie.stock.enums.EnumMsg;
import com.zeekie.stock.util.ApiUtils;
import com.zeekie.stock.util.StringUtil;

public class TokenFilter implements Filter {

	private static final Logger Log = LoggerFactory
			.getLogger(TokenFilter.class);

	private static final String AUTH_HEADER_NAME = "user_auth";

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

	@SuppressWarnings("unchecked")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String subUrl = req.getServletPath();
		if (inExcludeList(subUrl)) {
			chain.doFilter(request, response);
			return;
		}

		String userAuth = req.getHeader(AUTH_HEADER_NAME);

		if (StringUtils.isBlank(userAuth)) {
			writeAuthFailed(resp, EnumMsg.TOKEN_ERROR.getCode(),
					EnumMsg.TOKEN_ERROR.getMsg());
			return;
		}

		Map<String, Object> userAuthMap = JsonUtils.toMap(userAuth);

		if (userAuthMap == null) {
			Log.error("wrong user auth head accepted: {}", userAuth);
			writeAuthFailed(resp, EnumMsg.TOKEN_ERROR.getCode(),
					EnumMsg.TOKEN_ERROR.getMsg());
			return;
		}
		if (!checkWithToken(req, resp, chain, userAuthMap)) {
			return;
		}
	}

	private boolean checkWithToken(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain,
			Map<String, Object> userAuthMap) throws IOException,
			ServletException {
		String token = (String) userAuthMap.get("token");
		try {
			Map<String, String> tokenMap = verifyToken(token);

			if (!StringUtils.equals(Constants.CODE_SUCCESS,
					tokenMap.get("flag"))) {
				writeAuthFailed(response, tokenMap.get("code"),
						tokenMap.get("msg"));
				Log.error("校验验证码发生错误：" + tokenMap.get("msg"));
				return false;
			}
			filterChain.doFilter(request, response);
		} catch (Exception e) {
			Log.error(e.getMessage(), e);
			writeAuthFailed(response, EnumMsg.TOKEN_ERROR.getCode(),
					EnumMsg.TOKEN_ERROR.getMsg());
		}
		return true;
	}

	private Map<String, String> verifyToken(String token) throws CodecException {
		Map<String, String> map = new HashMap<String, String>();
		String original;
		try {
			original = TokenUtils.decryptToken(token, Constants.factor);
			String[] sl = original.split(",");
			if (sl.length != 2) {
				throw new CodecException();
			}
			String nickname = sl[0];
			String clientTime = sl[1];// yyyymmddhhmmss
			if (StringUtil.isBlank(nickname) || StringUtil.isBlank(clientTime)) {
				// 参数错误
				map.put("flag", Constants.CODE_FAILURE);
				map.put("code", EnumMsg.PARAM_EMPTY.getCode());
				map.put("msg", EnumMsg.PARAM_EMPTY.getMsg());
				return map;
			} else {
				if (Constants.user.indexOf(nickname) == -1) {
					map.put("flag", Constants.CODE_FAILURE);
					map.put("code", EnumMsg.ERROR_PARAM.getCode());
					map.put("msg", EnumMsg.ERROR_PARAM.getMsg());
					return map;
				}
			}
			if (!checkServerTime(clientTime, Constants.range)) {
				map.put("flag", Constants.CODE_FAILURE);
				map.put("code", EnumMsg.VISIT_EXCEED.getCode());
				map.put("msg", EnumMsg.VISIT_EXCEED.getMsg());
				return map;
			}
			map.put("flag", Constants.CODE_SUCCESS);
		} catch (CodecException e) {
			throw new CodecException("解析token发生错误");
		}
		return map;
	}

	/**
	 * 校验服务器时间
	 * 
	 * @param clientTime
	 *            客户端日志
	 * @param validTime
	 *            有效期
	 * @return
	 */
	private static boolean checkServerTime(String clientTime, int validTime) {
		Date thisDate = DateUtils.addSeconds(new Date(), -validTime);// 当前的服务器时间，减去授权的时间，再与客户端的时间进行比较。
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String serverTime = df.format(thisDate);
		return serverTime.compareTo(clientTime) <= 0;
	}

	private void writeAuthFailed(HttpServletResponse response, String code,
			String msg) throws IOException {
		String jsonResult = JsonUtils.fromObject(ApiUtils.bad(new Message(code,
				msg)));
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		response.getWriter().write(jsonResult);
	}

	private boolean inExcludeList(String subUrl) {
		if (null == excludedPageArray || excludedPageArray.length == 0) {
			return false;
		}
		for (String page : excludedPageArray) {// 判断是否在过滤url之外
			if (subUrl.equals(page)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
