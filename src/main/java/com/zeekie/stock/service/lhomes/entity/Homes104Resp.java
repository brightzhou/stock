package com.zeekie.stock.service.lhomes.entity;

import java.util.List;

import com.zeekie.stock.service.homes.entity.EntrustQueryEntity;

public class Homes104Resp extends HomesResponse {

	private List<EntrustQueryEntity> list;

	/**
	 * @return the list
	 */
	public List<EntrustQueryEntity> getList() {
		return list;
	}

	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(List<EntrustQueryEntity> list) {
		this.list = list;
	}

}
