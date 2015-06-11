package com.zeekie.stock.web;

import sitong.thinker.common.page.PageQuery;

public class DictionariesPage extends PageQuery {

	private static final long serialVersionUID = 2767775110683210541L;
	
	private String dicName ;
	
	private String dicWord ;
	
	private String dicType;



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



	public String getDicName() {
		return dicName;
	}



	public void setDicName(String dicName) {
		this.dicName = dicName;
	}



	public DictionariesPage(long pageIndex, long pageSize, String sortField,
			String sortOrder, String dicName,String dicType) {
		super(pageIndex, pageSize, sortField, sortOrder);
		this.dicName = dicName;
		this.dicType = dicType;
	}

	 

}
