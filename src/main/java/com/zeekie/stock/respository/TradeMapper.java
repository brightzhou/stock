package com.zeekie.stock.respository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zeekie.stock.entity.AddGuaranteePageDO;
import com.zeekie.stock.entity.AutoAddGuaranteeCashDO;
import com.zeekie.stock.entity.DayDO;
import com.zeekie.stock.entity.DeductDO;
import com.zeekie.stock.entity.EveningEndDO;
import com.zeekie.stock.entity.FundFlowDO;
import com.zeekie.stock.entity.HistoryOperationDO;
import com.zeekie.stock.entity.RuleDO;
import com.zeekie.stock.entity.TradeDO;
import com.zeekie.stock.entity.WarnLineDO;
import com.zeekie.stock.entity.form.AddCuaranteeForm;
import com.zeekie.stock.mybatis.MyBatisRepository;

@MyBatisRepository
public interface TradeMapper {

	RuleDO getRule() throws Exception;

	void storeOperatorAcountInfo(TradeDO acountDO) throws Exception;

	void deductGuaranteeCash(@Param("guaranteeCash") String guaranteeCash,
			@Param("nickname") String nickname) throws Exception;

	void deductTradeFund(@Param("tradeFund") Float tradeFund) throws Exception;

	void recordFundflow(@Param("nickname") String nickname,
			@Param("transactionType") String transactionType,
			@Param("fund") String fund, @Param("desc") String desc)
			throws Exception;

	void deductManageFeeBatch(List<DeductDO> deduct) throws Exception;

	void addFlowFundBatch(List<FundFlowDO> fee) throws Exception;

	void addDrawFeeBatch(List<DeductDO> deduct) throws Exception;

	void deductManageFee(DeductDO deduct) throws Exception;

	DeductDO queryNewStocker(@Param("nickname") String nickname)
			throws Exception;

	List<DeductDO> queryCurrentStocker() throws Exception;

	void updateManageFeeBatch() throws Exception;

	void updateManageFeeByUser(@Param("nickname") String nickname)
			throws Exception;

	AddGuaranteePageDO enterAddGuaranteePage(
			@Param("nickname") String nickname,
			@Param("addGuranteeCash") float addGuranteeCash) throws Exception;

	void modifyFund(AddCuaranteeForm addedGuaranteeCash) throws Exception;

	String queryOrginTradeFund(@Param("nickname") String nickname)
			throws Exception;

	void recordFundflowbatch(
			@Param("transFromClientToZhifubao") String transFromClientToZhifubao,
			List<DeductDO> result) throws Exception;

	List<AutoAddGuaranteeCashDO> getNeedAutoAddGuaranteeCashClient()
			throws Exception;

	void setAutoAddCuaranteeCash(@Param("nickname") String nickname)
			throws Exception;

	String isEnoughCashForClient(@Param("nickname") String nickname,
			@Param("addGuaranteeCash") String addGuaranteeCash)
			throws Exception;

	void currentOperationOver(@Param("nickname") String nickname)
			throws Exception;

	void setCurrentOpertionAccountIsHistory(@Param("nickname") String nickname)
			throws Exception;

	void recharge(@Param("nickname") String nickname, @Param("fund") String fund)
			throws Exception;

	void caculateRefereeIncome(@Param("nickname") String nickname)
			throws Exception;

	List<HistoryOperationDO> getHistoryOperation(
			@Param("nickname") String nickname, @Param("offset") String offset)
			throws Exception;

	void insertRecord(@Param("nickname") String nickname,
			@Param("payAccount") String payAccount, @Param("fund") String fund)
			throws Exception;

	void updateCurrentAccountMayUse(@Param("nickname") String nickname)
			throws Exception;

	EveningEndDO getEveningFlag(@Param("nickname") String nickname)
			throws Exception;

	Long getSeqId(@Param("seq") String seq) throws Exception;

	void setFeeCalendarBatch(List<DayDO> days) throws Exception;

	List<String> initFeeDays(@Param("month") String month) throws Exception;

	String selectFeeDay(@Param("feeDate") String feeDate) throws Exception;

	/**
	 * 处理到达警戒金额后发送短信
	 * *********************************************************************
	 */
	/**
	 * 查詢當warnflag=0 但是实际资产低于警戒金额后的用户
	 * 
	 * @return
	 * @throws Exception
	 */
	List<WarnLineDO> queryNeedNoticeUser() throws Exception;

	/**
	 * 更新发送短信的用户warnflag=1
	 * 
	 * @param result
	 *            List<WarnLineDO>
	 * @throws Exception
	 */
	void updateWarnFlagToOne(List<WarnLineDO> result) throws Exception;

	/**
	 * 查詢當warnflag=1 但是实际资产如果大于（（1-警戒线）*20%+警戒线），则设置alm-sms为0
	 * 
	 * @return List<WarnLineDO>
	 * @throws Exception
	 */
	/* List<WarnLineDO> querySwingUser() throws Exception; */

	/**
	 * 更新发送短信的用户warnflag=0
	 * 
	 * @param swingPercent
	 * @throws Exception
	 */
	void updateWarnFlagToZero(String swingPercent) throws Exception;

	/**
	 * *********************************************************************
	 */

	/**
	 * 判断用户是否存在操盘
	 * 
	 * @param nickname
	 * @return
	 * @throws Exception
	 */
	String queryHasOperation(@Param("nickname") String nickname)
			throws Exception;

}