package com.zeekie.stock.service;

import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hundsun.t2sdk.impl.client.T2Services;
import com.hundsun.t2sdk.interfaces.T2SDKException;
import com.zeekie.stock.Constants;
import com.zeekie.stock.service.dao.Mapper;
import com.zeekie.stock.service.sensitive.SensitiveWordInit;
import com.zeekie.stock.util.FloatJsonValueProcessor;

@Service
public class InitServer implements InitializingBean {

	Logger log = LoggerFactory.getLogger(InitServer.class);

	private String homes_server_name = "";

	private final String selectClause = "queryMsgTemplate";

	@Autowired
	private SensitiveWordInit sensitive;

	@Autowired
	@Value("${homes_server_name}")
	private String servername;

	@Autowired
	@Value("${main_operator_no}")
	private String operatorNo;

	@Autowired
	@Value("${main_operator_pwd}")
	private String password;

	@Autowired
	@Value("${func_am_user_login}")
	private String fn_login;

	@Autowired
	@Value("${msg_user}")
	private String msg_user;

	@Autowired
	@Value("${msg_pwd}")
	private String msg_pwd;

	@Autowired
	@Value("${msg_url}")
	private String msg_url;

	@Autowired
	@Value("${xinge_accessId}")
	private String accessId;

	@Autowired
	@Value("${xinge_secretkey}")
	private String secretkey;

	@Autowired
	@Value("${environment}")
	private String environment;

	@Autowired
	@Value("${startHomes}")
	private String startHomes;

	@Autowired
	private Mapper<String, String> dao;

	@Override
	public void afterPropertiesSet() throws Exception {
		initContants();
		registerJsonConfig();
		Constants.sensitiveWordMap = sensitive.initKeyWord();
		if (log.isDebugEnabled()) {
			log.debug("初始化敏感词汇成功！！！");
		}
		initTemplate();
		if (StringUtils.equals(Constants.CODE_SUCCESS, startHomes))
			startHomes();
	}

	private void initTemplate() {
		Constants.MSG_MODEL = dao.getMap(selectClause, null, "FN", "MODEL");
		if (log.isDebugEnabled()) {
			log.debug("初始化短信模板成功！！！");
		}
	}

	private void initContants() {
		homes_server_name = StringUtils.trim(servername);
		Constants.HOME_MANAGER_NO = StringUtils.trim(operatorNo);
		Constants.HOME_MANAGER_PWD = StringUtils.trim(password);
		Constants.HOME_MANAGER_FNC_LOGIN = StringUtils.trim(fn_login);

		// 发短信账户和密码
		Constants.MSG_USER = StringUtils.trim(msg_user);
		Constants.MSG_PWD = StringUtils.trim(msg_pwd);
		Constants.MSG_URL = StringUtils.trim(msg_url);

		// 注册信鸽
		Constants.accessId = StringUtils.trim(accessId);
		Constants.secretkey = StringUtils.trim(secretkey);
		Constants.environment = Integer.parseInt(StringUtils.trim(environment));
	}

	private void registerJsonConfig() {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Float.class,
				new FloatJsonValueProcessor());
		Constants.jsonConfig = jsonConfig;
	}

	private void startHomes() {
		T2Services server = T2Services.getInstance();
		try {
			server.init();
			server.start();
			Constants.client = server.getClient(homes_server_name);
		} catch (T2SDKException e) {
			log.error(e.getMessage(), e);
		}
	}

}
