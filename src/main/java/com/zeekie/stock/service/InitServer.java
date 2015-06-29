package com.zeekie.stock.service;

import java.io.File;
import java.io.IOException;

import net.sf.json.JsonConfig;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.hczq.hz.client.AmHttpServicesStub;
import com.hundsun.t2sdk.impl.client.T2Services;
import com.hundsun.t2sdk.interfaces.T2SDKException;
import com.zeekie.stock.Constants;
import com.zeekie.stock.service.dao.Mapper;
import com.zeekie.stock.service.lhomes.entity.AHomesEntity;
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
	@Value("${stock.interface.visit.time.range}")
	private String range;

	@Autowired
	@Value("${stock.encryption.factor}")
	private String factor;

	@Autowired
	@Value("${loaduser}")
	private String loaduser;

	@Autowired
	@Value("${stock.status.changeIsOpen}")
	private String open;

	@Autowired
	private Mapper<String, String> dao;

	@Override
	public void afterPropertiesSet() throws Exception {

		initContants();

		Constants.sensitiveWordMap = sensitive.initKeyWord();
		if (log.isDebugEnabled()) {
			log.debug("初始化敏感词汇成功！！！");
		}
		// 初始化短信模板
		initTemplate();

		// 启动HOMES
		if (StringUtils.equals(Constants.CODE_SUCCESS, startHomes)) {
			startHomes();
		}

		// 加载所有的用户用于token校验
		if (StringUtils.equals(Constants.CODE_SUCCESS, loaduser)) {
			loadAllUser();
		}
		
		initLittleHoms();
	}

	private void loadAllUser() {
		sensitive.loadAllUser();
		if (log.isDebugEnabled()) {
			log.debug("加载所有用户成功");
		}
	}

	private void initTemplate() {
		Constants.MSG_MODEL = dao.getMap(selectClause, null, "FN", "MODEL");
		if (log.isDebugEnabled()) {
			log.debug("初始化短信模板成功！！！");
		}
	}

	private void initContants() throws IOException {
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

		// 接口访问时间范围默认120秒
		Constants.range = Integer.parseInt(StringUtils.trim(range));

		Constants.factor = factor;

		registerJsonConfig();
		// 获取交易信息
		initTransactionXml();

		if (log.isDebugEnabled()) {
			log.debug("初始化全局变量成功！！！");
		}
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

			if (log.isDebugEnabled()) {
				log.debug("启动HOMES成功！！！");
			}
		} catch (T2SDKException e) {
			log.error(e.getMessage(), e);
		}
	}

	private void initTransactionXml() throws IOException {
		File file = ResourceUtils.getFile("classpath:transactionInfo.xml");
		String xml = FileUtils.readFileToString(file);
		// 内容
		Constants.XML = new String(xml.getBytes("utf-8"));
	}

	private void initLittleHoms() {
		if (StringUtils.equals("open", open)) {
			try {
				Constants.services = AmHttpServicesStub.createAmServices();
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
			if (log.isDebugEnabled()) {
				log.debug("初始化小homs成功");
			}
		}
	}

}
