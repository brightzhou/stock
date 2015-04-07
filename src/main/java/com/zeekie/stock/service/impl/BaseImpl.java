package com.zeekie.stock.service.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.zeekie.stock.enums.VerifyCodeEnum;
import com.zeekie.stock.respository.StockMapper;

public class BaseImpl {

	static Logger log = LoggerFactory.getLogger(BaseImpl.class);

	@Autowired
	private StockMapper trade;

	@Autowired
	@Value("${interval}")
	private String interval;

	public String getDesc(String source) {
		VerifyCodeEnum[] code = VerifyCodeEnum.values();
		for (VerifyCodeEnum item : code) {
			if (StringUtils.equals(StringUtils.trim(source), item.getSource())) {
				return item.getDesc();
			}
		}
		return "";
	}

	public boolean validVerifyCode(String telephone, String verifyCode,
			String source) {
		boolean flag = false;
		try {
			if (StringUtils.isNotEmpty(trade.checkVerifyCode(telephone,
					verifyCode, getSecond(), source))) {
				flag = true;
			} 
//			else {
//				// 验证码过期
//				trade.setVerifyCodeExpired(telephone);
//			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return flag;
	}

	private int getSecond() {
		return StringUtils.isNotBlank(interval) ? Integer.parseInt(interval) * 60
				: 60;
	}

}
