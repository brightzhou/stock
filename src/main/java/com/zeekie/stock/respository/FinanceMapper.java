package com.zeekie.stock.respository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import sitong.thinker.common.exception.ServiceInvokerException;

import com.zeekie.stock.entity.FinanceProductDO;
import com.zeekie.stock.entity.form.FinanceProducetForm;
import com.zeekie.stock.mybatis.MyBatisRepository;

@MyBatisRepository
public interface FinanceMapper {

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
	public List<FinanceProducetForm> getHistoryFinance(
			@Param("userId") String userId, @Param("offset") String offset,
			@Param("financeProtcol") String financeProtcol);

	/**
	 * 检查余额是否购买理财产品
	 * 
	 * @param userId
	 *            用户id
	 * @param financeLimit
	 *            购买额度
	 * @return 1:充足 ；空：不充足
	 */
	public String checkBalance(@Param("userId") String userId,
			@Param("financeLimit") String financeLimit);

}