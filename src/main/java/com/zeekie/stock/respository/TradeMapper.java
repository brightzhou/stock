package com.zeekie.stock.respository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zeekie.stock.entity.AddCashErrorDO;
import com.zeekie.stock.entity.AddGuaranteePageDO;
import com.zeekie.stock.entity.AutoAddGuaranteeCashDO;
import com.zeekie.stock.entity.CurrentAccountDO;
import com.zeekie.stock.entity.DayDO;
import com.zeekie.stock.entity.DeductDO;
import com.zeekie.stock.entity.DictionariesDO;
import com.zeekie.stock.entity.EveningEndDO;
import com.zeekie.stock.entity.FundFlowDO;
import com.zeekie.stock.entity.HasOpertAndDebtDO;
import com.zeekie.stock.entity.HistoryOperationDO;
import com.zeekie.stock.entity.RuleDO;
import com.zeekie.stock.entity.StopDealStockDO;
import com.zeekie.stock.entity.TradeDO;
import com.zeekie.stock.entity.WarnLineDO;
import com.zeekie.stock.entity.StatisticsDO;
import com.zeekie.stock.entity.form.AddCuaranteeForm;
import com.zeekie.stock.mybatis.MyBatisRepository;
import com.zeekie.stock.web.ClientPage;
import com.zeekie.stock.web.DictionariesPage;
import com.zeekie.stock.web.StatisticsPage;

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

	List<DeductDO> queryCurrentStocker(
			@Param("chargeFeeDate") String chargeFeeDate) throws Exception;

	void updateManageFeeBatch(@Param("chargeFeeDate") String chargeFeeDate)
			throws Exception;

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
	HasOpertAndDebtDO queryHasOperation(@Param("nickname") String nickname)
			throws Exception;

	/**
	 * 设置交易信息
	 * 
	 * @param userId
	 *            用户id
	 * @param nickname
	 *            用户昵称
	 * @param merchantId
	 *            商户订单号
	 * @param cash
	 *            交易金额
	 * @param status
	 *            交易状态 1：成功/0：失败
	 * @param respMsg
	 *            交易信息
	 * @param merchantDate
	 * @param refNo
	 */
	void setPayInfo(@Param("userId") String userId,
			@Param("nickname") String nickname,
			@Param("merchantId") String merchantId, @Param("cash") String cash,
			@Param("status") String status, @Param("respMsg") String respMsg,
			@Param("merchantDate") String merchantDate,
			@Param("refNo") String refNo) throws Exception;

	/**
	 * 设置交易信息
	 * 
	 * @param userId
	 *            用户id
	 * @param nickname
	 *            用户昵称
	 * @param merchantId
	 *            商户订单号
	 * @param cash
	 *            交易金额
	 * @param status
	 *            交易状态 1：成功/0：失败
	 * @param respMsg
	 *            交易信息
	 * @param bankName
	 * @param refNo
	 */
	void setPayInfoByJob(@Param("userId") String userId,
			@Param("nickname") String nickname,
			@Param("merchantId") String merchantId, @Param("cash") String cash,
			@Param("acceptStatus") String acceptStatus,
			@Param("respMsg") String respMsg,
			@Param("bankName") String bankName, @Param("refNo") String refNo)
			throws Exception;

	void updateReceiptStatus(Map<String, String> param);

	/**
	 * 查询需要禁止买入的用户
	 * 
	 * @return
	 */
	List<StopDealStockDO> queryStopDealResult();

	/**
	 * 更新买卖标志
	 * 
	 * @param operateId
	 * @param flag
	 */
	void updateStopBuyFlag(@Param("operateId") String operateId,
			@Param("flag") String flag);

	CurrentAccountDO getUserOperateAcount(@Param("nickname") String nickname)
			throws Exception;

	/**
	 * 统计每天收益
	 * 
	 * @return
	 * @throws Exception
	 */
	List<StatisticsDO> queryStatistics(StatisticsPage statisticsPage)
			throws Exception;

	/**
	 * 统计每天收益总条数
	 * 
	 * @return
	 * @throws Exception
	 */
	Long queryStatisticsCount(StatisticsPage statisticsPage) throws Exception;

	/**
	 * 添加字典信息
	 * 
	 * @param dictionariesDO
	 * @return
	 * @throws Exception
	 */
	Long insertDictionaries(DictionariesDO dictionariesDO) throws Exception;

	/**
	 * 删除字典信息
	 * 
	 * @param id
	 * @return
	 */
	void deleteDictionaries(@Param("id") String id);

	/**
	 * 修改字典信息
	 * 
	 * @param dictionariesDO
	 * @return
	 */
	Long updateDictionaries(DictionariesDO dictionariesDO);

	/**
	 * 查询字典信息
	 * 
	 * @param dictionariesPage
	 * @return
	 */
	List<DictionariesDO> queryDictionaries(DictionariesPage dictionariesPage);

	/**
	 * 查询字典信息数量
	 * 
	 * @param dictionariesPage
	 * @return
	 */
	Long queryDictionariesCount(DictionariesPage dictionariesPage);

	/**
	 * 查找字典信息
	 * 
	 * @param id
	 * @return
	 */
	public DictionariesDO getDictionaries(@Param("id") String id);

	/**
	 * 通过字典KEY查找字典信息
	 * 
	 * @param id
	 * @return
	 */
	public DictionariesDO getDictionariesByDicWord(
			@Param("dicWord") String dicWord);

	/**
	 * 查找字典信息
	 * 
	 * @param id
	 * @return
	 */
	public List<DictionariesDO> queryDictionarieList(
			DictionariesDO dictionariesDO);

	/*
	 * 其他統計值
	 */
	StatisticsDO queryOtherStaticValue();

	void insertError(@Param("nickname") String nickname,
			@Param("addedAssginCapital") String addedAssginCapital,
			@Param("message") String message);

	/**
	 * 获取增加保证金错误条数
	 * 
	 * @param nickname
	 * @return
	 */
	long getErrorCount(@Param("nickname") String nickname);

	/**
	 * 获取列表
	 * 
	 * @param errorPage
	 * @return
	 */
	List<AddCashErrorDO> getErrorList(ClientPage errorPage);
}