package com.zeekie.stock.enums;

import org.apache.commons.lang.StringUtils;

import com.zeekie.stock.Constants;

public enum Fund {

	PLAT_RED_PACKET("80", Constants.REDPACKET_TO_CLIENT), REFEREE_PACKET("90",
			Constants.REDPACKET_TO_CLIENT_REFEREE), REFEREE_PRAISE("100",
			Constants.REDPACKET_TO_CLIENT_REFEREE_TO_USER), CLIENT_PRIASE(
			"110", Constants.REDPACKET_TO_CLIENT_USER_FROM_REFEREE), AMORTIZATION(
			"120", Constants.AMORTIZATION);

	private String type;

	private String desc;

	Fund(String type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the desc
	 */
	public static String getDesc(String nickname, String type) {
		String desc = "";
		for (Fund f : Fund.values()) {
			if (StringUtils.equals(f.getType(), type)) {
				desc = f.desc.replace("nickname", nickname);
			}
		}
		return desc;
	}

}
