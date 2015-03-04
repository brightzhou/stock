package com.zeekie.stock.service;

import sitong.thinker.common.exception.ServiceInvokerException;
import sitong.thinker.common.page.DefaultPage;

import com.zeekie.stock.entity.ClientPercentDO;
import com.zeekie.stock.entity.CurrentOperationWebDO;
import com.zeekie.stock.entity.EveningUpDO;
import com.zeekie.stock.entity.MovecashToRefereeDO;
import com.zeekie.stock.entity.OwingFeeDO;
import com.zeekie.stock.entity.PayDO;
import com.zeekie.stock.entity.TotalFundDO;
import com.zeekie.stock.entity.WithdrawlDO;
import com.zeekie.stock.web.ClientPage;
import com.zeekie.stock.web.EveningUpPage;
import com.zeekie.stock.web.MoveToRefereePage;
import com.zeekie.stock.web.PayPage;
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
	 * @return
	 */
	public boolean addTotalFund(String fund, String fundAccount);

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

}
