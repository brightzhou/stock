package com.zeekie.stock.entity;

import com.zeekie.stock.util.StringUtil;

public class HhbFlowDO {
   
	private Long id;
	
	private String type;

	private String transactionTime;

   private String nickname;
	
	private String userId;

	private String  cash;
    
	private String hhb;
	 
	public HhbFlowDO() {
		// TODO Auto-generated constructor stub
	}

	public HhbFlowDO(String nickname, String type, String hhb,
			String cash) {
		super();
		this.nickname = nickname;
		this.type = type;
		this.hhb = hhb;
		this.cash = cash;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCash() {
		return cash;
	}

	public void setCash(Float cash) {
		this.cash = String.valueOf(StringUtil.keepTwoDecimalFloat(cash));
	}
	
	public void setStrCash(String cash) {
		this.cash = cash;
	}

	public String getHhb() {
		return hhb;
	}

	public void setHhb(Float hhb) {
		this.hhb = String.valueOf(StringUtil.keepTwoDecimalFloat(hhb));
		 
	}
	
	public void setStrHhb(String hhb) {
		this.hhb = hhb;
		 
	}

	

}
