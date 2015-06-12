package com.zeekie.stock.respository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import sitong.thinker.common.exception.ServiceInvokerException;

import com.zeekie.stock.entity.FinanceIncomeDO;
import com.zeekie.stock.entity.FinanceProductDO;
import com.zeekie.stock.entity.HistoryFinanceDO;
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
	public List<HistoryFinanceDO> getHistoryFinance(
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
			@Param("financeLimit") String financeLimit)
			throws ServiceInvokerException;

	/**
	 * 查询所有人的理财收益
	 */
	public List<FinanceIncomeDO> queryFinanceIncome()
			throws ServiceInvokerException;

	/**
	 * 计算理财每天的收益
	 * 
	 * @param currentYearDays
	 */
	public void updateCurrentIncome(
			@Param("currentYearDays") int currentYearDays)
			throws ServiceInvokerException;

	/**
	 * 切换状态
	 * 
	 * @param userId
	 * @param isStock
	 */
	public void updateStatus(@Param("userId") String userId,
			@Param("isStock") String isStock) throws ServiceInvokerException;

	/**
	 * 更新理财额度
	 * 
	 * @param productCode
	 *            产品代码
	 * @param financeLimit
	 *            购买额度
	 * @throws ServiceInvokerException
	 */
	public void updateTotalLimit(@Param("productCode") String productCode,
			@Param("financeLimit") String financeLimit)
			throws ServiceInvokerException;

	/**
	 * 更新钱包余额
	 * 
	 * @param financeLimit
	 * @param userId
	 */
	public void updateWallet(@Param("fund") String fund,
			@Param("userId") String userId);

}