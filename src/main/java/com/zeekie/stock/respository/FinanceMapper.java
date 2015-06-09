package com.zeekie.stock.respository;

import java.util.List;

import com.zeekie.stock.mybatis.MyBatisRepository;

@MyBatisRepository
public interface FinanceMapper {

	public void getCurrentFinance() throws Exception;

}