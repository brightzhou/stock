package com.zeekie.stock.entity;

public class HhbFlowDO {
   
	private Long id;
	
	private String type;

	private String transactionTime;

   private String nickname;
	
	private String userId;

	private Float  cash;
    
	private Integer hhb;
	 
	public HhbFlowDO() {
		// TODO Auto-generated constructor stub
	}

	public HhbFlowDO(String nickname, String type, Integer hhb,
			Float cash) {
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

	public Float getCash() {
		return cash;
	}

	public void setCash(Float cash) {
		this.cash = cash;
	}

	public Integer getHhb() {
		return hhb;
	}

	public void setHhb(Integer hhb) {
		this.hhb = hhb;
	}

	

}
