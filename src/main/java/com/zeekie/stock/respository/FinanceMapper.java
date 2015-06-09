package com.zeekie.stock.respository;

import java.util.List;

import sitong.thinker.common.exception.ServiceInvokerException;

import com.zeekie.stock.entity.FinanceProductDO;
import com.zeekie.stock.entity.form.FinanceProducetForm;
import com.zeekie.stock.mybatis.MyBatisRepository;

@MyBatisRepository
public interface FinanceMapper {

	public void getCurrentFinance() throws Exception;

	/**
	 * 获取当日理财信息
	 * 
	 * @return FinanceProductDO
	 * @throws ServiceInvokerException
	 */
	public FinanceProductDO getFinanceProduct() throws ServiceInvokerException;

	/**
	 * 保存理财信息
	 * 
	 * @param form
	 * @throws ServiceInvokerException
	 */
	public void saveCurrentFinance(FinanceProducetForm form)
			throws ServiceInvokerException;

	/**
	 * 获取历史理财记录
	 * 
	 * @param userId
	 * @param offset
	 * @param financeProtcol 
	 * @return List<FinanceProducetForm>
	 */
	public List<FinanceProducetForm> getHistoryFinance(String userId,
			String offset, String financeProtcol);

}