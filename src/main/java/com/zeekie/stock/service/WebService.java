package com.zeekie.stock.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import sitong.thinker.common.exception.ServiceInvokerException;
import sitong.thinker.common.page.DefaultPage;
import sitong.thinker.common.page.PageQuery;

import com.zeekie.stock.entity.AddCashErrorDO;
import com.zeekie.stock.entity.ClientPercentDO;
import com.zeekie.stock.entity.CurrentOperationWebDO;
import com.zeekie.stock.entity.DictionariesDO;
import com.zeekie.stock.entity.FinanceProductDO;
import com.zeekie.stock.entity.FinanceProductDetailDO;
import com.zeekie.stock.entity.FlbDO;
import com.zeekie.stock.entity.GuessProductDO;
import com.zeekie.stock.entity.MovecashToRefereeDO;
import com.zeekie.stock.entity.OperationInfoDO;
import com.zeekie.stock.entity.OtherFundFlowDO;
import com.zeekie.stock.entity.OwingFeeDO;
import com.zeekie.stock.entity.PayDO;
import com.zeekie.stock.entity.PercentDO;
import com.zeekie.stock.entity.StockCodeDO;
import com.zeekie.stock.entity.TotalFundDO;
import com.zeekie.stock.entity.TransactionDO;
import com.zeekie.stock.entity.UserBankDO;
import com.zeekie.stock.entity.UserInfoDO;
import com.zeekie.stock.entity.WithdrawlDO;
import com.zeekie.stock.entity.StatisticsDO;
import com.zeekie.stock.web.ClientPage;
import com.zeekie.stock.web.DictionariesPage;
import com.zeekie.stock.web.EveningUpPage;
import com.zeekie.stock.web.FinanceDetailPage;
import com.zeekie.stock.web.FinancePage;
import com.zeekie.stock.web.MoveToRefereePage;
import com.zeekie.stock.web.OperationInfoPage;
import com.zeekie.stock.web.PayPage;
import com.zeekie.stock.web.PercentDOPage;
import com.zeekie.stock.web.StatisticsPage;
import com.zeekie.stock.web.StockCodePage;
import com.zeekie.stock.web.TotalFundPage;
import com.zeekie.stock.web.WithdrawlPage;

public interface WebService {

	/**
	 * 获取提现的列表
	 * 
	 * @param withdrawlPage
	 * @return
	 */
	public DefaultPage<WithdrawlDO> getDepositList(WithdrawlPage withdrawlPage)
			throws ServiceInvokerException;

	/**
	 * 更新提现表，代表用戶提現成功
	 * 
	 * @param nickname
	 * @param cash
	 * @param nickname2
	 * @return
	 */
	public boolean withdrawlToUser(String id, String nickname, String cash);

	/**
	 * 获取总资产列表
	 * 
	 * @param totalFundPage
	 * @return
	 * @throws ServiceInvokerException
	 */
	public DefaultPage<TotalFundDO> getTotalFund(TotalFundPage totalFundPage)
			throws ServiceInvokerException;

	/**
	 * 獲取划钱给他的推广人
	 * 
	 * @param moveToRefereePage
	 * @return
	 * @throws ServiceInvokerException
	 */
	public DefaultPage<MovecashToRefereeDO> queryMoveCashToReferee(
			MoveToRefereePage moveToRefereePage) throws ServiceInvokerException;

	/**
	 * 增加总资产
	 * 
	 * @param fund
	 * @param fundAccount
	 * @param storeType
	 *            recharge 平台充值/adjust 金额调整
	 * @param desc
	 * @return
	 */
	public boolean addTotalFund(String fund, String fundAccount, String desc,
			String storeType);

	/**
	 * 获取支付页面
	 * 
	 * @param payPage
	 * @return
	 */
	public DefaultPage<PayDO> getPayList(PayPage payPage)
			throws ServiceInvokerException;

	/**
	 * 
	 * @param nickname
	 * @param fund
	 * @param id
	 * @return
	 */
	public boolean payToUs(String id, String nickname, String fund);

	/**
	 * 
	 * @param payPage
	 * @return
	 */
	public DefaultPage<CurrentOperationWebDO> getEveningUp(EveningUpPage payPage)
			throws ServiceInvokerException;

	/**
	 * 
	 * @param id
	 * @param nickname
	 * @return
	 */
	public boolean eveningUp(String id, String nickname)
			throws RuntimeException;

	/**
	 * 设置收取管理费的日期
	 * 
	 * @param yearMonth
	 * @param days
	 */
	public void setFeeCalendar(String yearMonth, String days)
			throws ServiceInvokerException;

	/**
	 * 根据传递的月份初始化界面设置过的收费日期
	 * 
	 * @param month
	 */
	public String initFeeDays(String month);

	/**
	 * 获取用户列表
	 * 
	 * @param clientPage
	 * @return
	 */
	public DefaultPage<ClientPercentDO> getClient(ClientPage clientPage)
			throws ServiceInvokerException;

	/**
	 * 获取个人信息根据id
	 * 
	 * @param id
	 * @return
	 * @throws ServiceInvokerException
	 */
	public String getClientById(String id) throws ServiceInvokerException;

	/**
	 * 保存修改信息
	 * 
	 * @param data
	 *            JSONObject
	 * @return
	 * @throws ServiceInvokerException
	 */
	public String saveClientInfo(String data) throws ServiceInvokerException;

	/**
	 * 获取资金账户
	 * 
	 * @param status
	 * @return
	 * @throws ServiceInvokerException
	 */
	public String getFundAccount(String status) throws ServiceInvokerException;

	/**
	 * 保存多账户设置
	 * 
	 * @param data
	 * @return
	 */
	public String saveFundAccount(String data) throws ServiceInvokerException;

	/**
	 * 查询手续费欠费情况
	 * 
	 * @param clientPage
	 * @return
	 */
	public DefaultPage<OwingFeeDO> getOwingFee(ClientPage clientPage)
			throws ServiceInvokerException;

	/**
	 * 开启或关闭APP
	 * 
	 * @param flag
	 *            open:打开 /close:关闭
	 */

	public void openOrCloseApp(String flag);

	/**
	 * 查询比例
	 * 
	 * @param page
	 * @return
	 */
	public DefaultPage<PercentDO> caculatePercent(PercentDOPage page)
			throws ServiceInvokerException;

	/**
	 * 获取用户基础信息
	 * 
	 * @param clientPage
	 * @return UserInfoDO
	 */
	public DefaultPage<UserInfoDO> getClientInfo(ClientPage clientPage)
			throws ServiceInvokerException;

	/**
	 * 获取资金流水信息
	 */
	public DefaultPage<OtherFundFlowDO> getFundFlowInfo(ClientPage clientPage)
			throws ServiceInvokerException;

	/**
	 * 获取操盘信息
	 * 
	 * @param infoPage
	 * @return
	 */
	public DefaultPage<OperationInfoDO> getOperationInfo(
			OperationInfoPage infoPage) throws ServiceInvokerException;

	/**
	 * 给用户派发红包
	 * 
	 * @param data
	 * @return
	 */
	public String sendRedPacket(String data) throws ServiceInvokerException;

	/**
	 * 群发短信
	 * 
	 * @param data
	 * @return
	 */
	public String sendMsgToAll(String data) throws ServiceInvokerException;

	/**
	 * 撤销提现
	 * 
	 * @param id
	 * @param nickname
	 * @param cash
	 * @return
	 */
	public boolean undoWithDrawal(String id, String nickname, String cash)
			throws ServiceInvokerException;

	/*
	 * 更新支付信息状态
	 */
	public void updateReceiptStatus(Map<String, String> parasResult)
			throws ServiceInvokerException;

	/**
	 * 设置免费日期
	 * 
	 * @param yearMonth
	 * @param days
	 */
	public void setFreeDays(String yearMonth, String days)
			throws ServiceInvokerException;

	/**
	 * 获取交易流水信息
	 * 
	 * @param clientPage
	 * @return
	 */
	public DefaultPage<TransactionDO> getTransactionInfo(ClientPage clientPage)
			throws ServiceInvokerException;

	/**
	 * 获取银行信息用于维护
	 * 
	 * @param clientPage
	 * @return
	 */
	public DefaultPage<UserBankDO> getUserbank(ClientPage clientPage)
			throws ServiceInvokerException;

	/**
	 * 保存银行卡信息
	 * 
	 * @param id
	 * @param cardNumber
	 * @return
	 */
	public String saveUserbank(JSONArray ja) throws ServiceInvokerException;

	/**
	 * 删除银行卡信息
	 * 
	 * @param id
	 * @return
	 * @throws ServiceInvokerException
	 */
	public String deleteUserbank(String id) throws ServiceInvokerException;

	/**
	 * 每天收益统计
	 * 
	 * @param statisticsPage
	 * @return
	 * @throws ServiceInvokerException
	 */
	public DefaultPage<StatisticsDO> queryStatistics(
			StatisticsPage statisticsPage) throws ServiceInvokerException;

	/**
	 * 添加字典信息
	 * 
	 * @param dictionariesDO
	 * @return
	 * @throws Exception
	 */
	public String insertDictionaries(DictionariesDO dictionariesDO)
			throws Exception;

	/**
	 * 删除字典信息
	 * 
	 * @param id
	 * @return
	 */
	public String deleteDictionaries(String id);

	/**
	 * 修改字典信息
	 * 
	 * @param dictionariesDO
	 * @return
	 */
	public String updateDictionaries(DictionariesDO dictionariesDO);

	/**
	 * 查询字典信息
	 * 
	 * @param dictionariesPage
	 * @return
	 */
	public DefaultPage<DictionariesDO> queryDictionaries(
			DictionariesPage dictionariesPage) throws ServiceInvokerException;

	/**
	 * 查找字典信息
	 * 
	 * @param id
	 * @return
	 */
	public String getDictionaries(String id);

	/**
	 * 获取所有的标
	 * 
	 * @return
	 */
	public DefaultPage<FinanceProductDO> getAllCurrentFinance(
			FinancePage financePage) throws ServiceInvokerException;

	/**
	 * 保存新产信息
	 * 
	 * @param financeProduct
	 *            理财产品
	 * @param financeTotalLimit
	 *            额度
	 * @param annualIncome
	 *            年化收益率
	 * @param expireDay
	 *            期限
	 * @param carryDate
	 *            起息日
	 * @param maxLimit
	 *            最大购买额度
	 * @param minLimit
	 *            最小购买额度
	 * @return
	 * @throws ServiceInvokerException
	 */
	public String saveProduct(String financeProduct, String financeTotalLimit,
			String annualIncome, String expireDay, String carryDate,
			String maxLimit, String minLimit) throws ServiceInvokerException;

	/**
	 * 獲取某個產品的購買明細
	 * 
	 * @param financePage
	 * @return
	 */
	public DefaultPage<FinanceProductDetailDO> getFinanceDetail(
			FinanceDetailPage financePage) throws ServiceInvokerException;

	/**
	 * 为付立波查询
	 * 
	 * @param flbPage
	 * @return
	 */
	public DefaultPage<FlbDO> getFlb(PageQuery flbPage)
			throws ServiceInvokerException;

	/**
	 * 获取错误信息增加保证金
	 * 
	 * @param errorPage
	 * @return
	 */
	public DefaultPage<AddCashErrorDO> getError(ClientPage errorPage)
			throws ServiceInvokerException;

	/*
	 * 查询股票代码
	 * 
	 * @param stockPage
	 * 
	 * @return
	 * 
	 * @throws ServiceInvokerException
	 */
	public DefaultPage<StockCodeDO> queryStockCode(StockCodePage stockPage)
			throws ServiceInvokerException;

	/**
	 * 保存代码
	 * 
	 * @param string
	 * @return
	 */
	public String saveStockCode(String string) throws ServiceInvokerException;

	/**
	 * 修改用户手机
	 * 
	 * @param data
	 * @return
	 * @throws ServiceInvokerException
	 */
	public String editUserphone(String data) throws ServiceInvokerException;

	/**
	 * 设置产品状态
	 * 
	 * @param data
	 * @return
	 * @throws ServiceInvokerException
	 */
	public String setProductStatus(String data) throws ServiceInvokerException;

	/**
	 * 获取理财列表
	 * 
	 * @param product
	 * @return
	 */
	public DefaultPage<GuessProductDO> queryGuessproduct(FinancePage product)
			throws ServiceInvokerException;

	/**
	 * 保存新的竞猜产品
	 * 
	 * @param data
	 * @return
	 */
	public String saveGuessProduct(String data) throws ServiceInvokerException;

}
