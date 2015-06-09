package com.zeekie.stock.entity;

public class DictionariesDO {
    //主键
	private String id;
	//类型
	private String dicType;
	//字典词
	private String dicWord;
	//字典值
	private String dicValue;
	//字典名称
	private String dicName;
	//排序号
	private String orderNo;
	//描述
	private String des;
	//是否缓存
	private String isCache;
	//状态 1可用   0不可用 
	private String status;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDicType() {
		return dicType;
	}
	public void setDicType(String dicType) {
		this.dicType = dicType;
	}
	public String getDicWord() {
		return dicWord;
	}
	public void setDicWord(String dicWord) {
		this.dicWord = dicWord;
	}
	public String getDicValue() {
		return dicValue;
	}
	public void setDicValue(String dicValue) {
		this.dicValue = dicValue;
	}
	public String getDicName() {
		return dicName;
	}
	public void setDicName(String dicName) {
		this.dicName = dicName;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getIsCache() {
		return isCache;
	}
	public void setIsCache(String isCache) {
		this.isCache = isCache;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
