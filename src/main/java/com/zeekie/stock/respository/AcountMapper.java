package com.zeekie.stock.respository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import sitong.thinker.common.page.PageQuery;

import com.zeekie.stock.entity.AccountDO;
import com.zeekie.stock.entity.BankInfoDO;
import com.zeekie.stock.entity.BindBankDO;
import com.zeekie.stock.entity.CashDO;
import com.zeekie.stock.entity.ClientPercentDO;
import com.zeekie.stock.entity.CurrentAccountDO;
import com.zeekie.stock.entity.CurrentOperationWebDO;
import com.zeekie.stock.entity.DebtDO;
import com.zeekie.stock.entity.EndStockCashDO;
import com.zeekie.stock.entity.FundAccountDO;
import com.zeekie.stock.entity.FundFlowDO;
import com.zeekie.stock.entity.IdentifyDO;
import com.zeekie.stock.entity.InsufficientBalanceRemindDO;
import com.zeekie.stock.entity.ManagerDO;
import com.zeekie.stock.entity.MovecashToRefereeDO;
import com.zeekie.stock.entity.OtherFundFlowDO;
import com.zeekie.stock.entity.OwingFeeDO;
import com.zeekie.stock.entity.PayDO;
import com.zeekie.stock.entity.PercentDO;
import com.zeekie.stock.entity.PhoneAndTIcketDO;
import com.zeekie.stock.entity.RedPacketDO;
import com.zeekie.stock.entity.RedpacketAndBalanceDO;
import com.zeekie.stock.entity.StockRadioDO;
import com.zeekie.stock.entity.TotalFundDO;
import com.zeekie.stock.entity.TradeDO;
import com.zeekie.stock.entity.UserDO;
import com.zeekie.stock.entity.UserInfoDO;
import com.zeekie.stock.entity.WithdrawPageDO;
import com.zeekie.stock.entity.WithdrawlDO;
import com.zeekie.stock.entity.form.TradeForm;
import com.zeekie.stock.mybatis.MyBatisRepository;
import com.zeekie.stock.web.ClientPage;
import com.zeekie.stock.web.EveningUpPage;
import com.zeekie.stock.web.MoveToRefereePage;
import com.zeekie.stock.web.PayPage;
import com.zeekie.stock.web.TotalFundPage;
import com.zeekie.stock.web.WithdrawlPage;

@MyBatisRepository
public interface AcountMapper {

	public void insertIdentify(@Param("nickname") String nickname,
			@Param("truename") String truename, @Param("idCard") String idCard)
			throws Exception;

	public void bindCreditCard(@Param("userId") String userId,
			@Param("phone") String phone, @Param("bank") String bank,
			@Param("number") String number, @Param("bankCode") String bankCode)
			throws Exception;

	public void setDepositPwd(@Param("nickname") String nickname,
			@Param("telephone") String telephone,
			@Param("depositPwd") String depositPwd) throws Exception;

	public TradeDO getInitUserInfo();

	public String cashIsEnough(@Param("tradeFund") String tradeFund,
			@Param("fundAccount") String fundAccount) throws Exception;

	public StockRadioDO getAssignRadioForCurrUser(
			@Param("nickname") String nickname) throws Exception;

	public void storeOperatorCash(TradeForm tradeForm) throws Exception;

	public TradeDO getOperateAcount(@Param("nickname") String nickname)
			throws Exception;

	public ManagerDO getStockManager() throws Exception;

	public List<TradeDO> getSonAccountInfo() throws Exception;

	public String queryClientCombineId(@Param("nickname") String nickname)
			throws Exception;

	public List<InsufficientBalanceRemindDO> queryUserBalanceIsNotEnough()
			throws Exception;

	public List<FundFlowDO> getFundFlow(@Param("nickname") String nickname,
			@Param("offset") String offset) throws Exception;

	public void updateFigurePwd(@Param("nickname") String nickname,
			@Param("figurePwd") String figurePwd) throws Exception;

	public void updateTelephone(@Param("nickname") String nickname,
			@Param("telephone") String telephone) throws Exception;

	public CurrentAccountDO getCurrentAccount(@Param("nickname") String nickname)
			throws Exception;

	void withdraw(@Param("nickname") String nickname, @Param("fund") String fund)
			throws Exception;

	public String isIdentify(@Param("nickname") String nickname)
			throws Exception;

	public List<WithdrawlDO> getDepositList(WithdrawlPage withdrawlPage)
			throws Exception;

	public Long queryDepositCount(WithdrawlPage withdrawlPage) throws Exception;

	public void updateDepositStatus(@Param("id") String id) throws Exception;

	public long queryTotalFundCount(@Param("fundAccount") String fundAccount)
			throws Exception;

	public List<TotalFundDO> getTotalFundList(TotalFundPage totalFundPage)
			throws Exception;

	public String operationIsEnded(@Param("nickname") String nickname)
			throws Exception;

	public EndStockCashDO queryUserLastCash(@Param("nickname") String nickname)
			throws Exception;

	public void moveProfitToUserWallet(@Param("nickname") String nickname,
			@Param("userCash") String userCash) throws Exception;

	public void moveAssignCashToTotalFund(
			@Param("fundAccount") String fundAccount,
			@Param("assginCash") String assginCash) throws Exception;

	public void recordIntegral(@Param("nickname") String nickname)
			throws Exception;

	public void moveRedPacketToReferee(@Param("nickname") String nickname,
			@Param("redPacket") String redPacket) throws Exception;

	public void moveRedPacketToRegister(@Param("nickname") String nickname,
			@Param("redPacket") String redPacket) throws Exception;

	public void movePlatformRedPacketToRegister(
			@Param("nickname") String nickname,
			@Param("redPacket") long redPacket) throws Exception;

	public RedPacketDO getRedPacket(@Param("nickname") String nickname)
			throws Exception;

	public void insertRefereeRedPacket(@Param("id") Long id,
			@Param("refereeId") String refereeId,
			@Param("redPacket") String redPacket) throws Exception;

	public long queryMoveCashToRefereeCount() throws Exception;

	public List<MovecashToRefereeDO> queryMoveCashToReferee(
			MoveToRefereePage moveToRefereePage) throws Exception;

	public void addTotalFund(@Param("type") String type,
			@Param("fund") String fund,
			@Param("fundAccount") String fundAccount,
			@Param("desc") String desc, @Param("storeType") String storeType)
			throws Exception;

	public void bindPercent(@Param("nickname") String nickname)
			throws Exception;

	public String queryReferee(@Param("nickname") String nickname)
			throws Exception;

	public String getPlatRedPacketToReferee(@Param("referee") String referee)
			throws Exception;

	public RedpacketAndBalanceDO getRefereeRedPacket(
			@Param("referee") String referee) throws Exception;

	public String getPlatRedPacketToRegister(@Param("nickname") String nickname)
			throws Exception;

	public String existWallet(@Param("nickname") String referee)
			throws Exception;

	public void insertMoneyToWallet(@Param("nickname") String referee,
			@Param("redPacket") long redPacket) throws Exception;

	public String checkDepositPwd(@Param("nickname") String nickname,
			@Param("depositPwd") String depositPwd) throws Exception;

	public WithdrawPageDO withdrawPageEnter(@Param("nickname") String nickname)
			throws Exception;

	public String checkBalance(@Param("nickname") String nickname,
			@Param("fund") String fund) throws Exception;

	public void updateStatusToN(@Param("fundAccount") String fundAccount)
			throws Exception;

	public void updateStatusToY(@Param("fundAccount") String fundAccount)
			throws Exception;

	public void updateAccountStatus(
			@Param("operatorAcount") String operatorAcount,
			@Param("newOperatePwd") String newOperatePwd) throws Exception;

	public String checkOldTelephone(@Param("nickname") String nickname,
			@Param("telephone") String telephone) throws Exception;

	public String userWalletIsFull(@Param("nickname") String nickname,
			@Param("guaranteeCash") String guaranteeCash) throws Exception;

	public String checkDepoist(@Param("userId") String userId,
			@Param("depositPwd") String depositPwd) throws Exception;

	public long getPayListCount() throws Exception;

	public List<PayDO> getPayList(PayPage payPage) throws Exception;

	public void updatePayStatus(@Param("id") String id) throws Exception;

	public void saveFreezeCash(@Param("nickname") String nickname,
			@Param("fund") String fund) throws Exception;

	public void deductWithdrawCahs(@Param("nickname") String nickname,
			@Param("cash") String cash) throws Exception;

	public String getOperateAccount() throws Exception;

	public void updateDebt(@Param("debt") String debt,
			@Param("nickname") String nickname) throws Exception;

	public void deductDebt(@Param("cash") Float cash,
			@Param("nickname") String nickname) throws Exception;

	public CashDO selectCash(@Param("nickname") String nickname)
			throws Exception;

	public DebtDO getWallet(@Param("nickname") String nickname)
			throws Exception;

	public long queryCurrentOperationCount(@Param("nickname") String nickname)
			throws Exception;

	public List<CurrentOperationWebDO> queryCurrentOperation(
			EveningUpPage eveningUpPage) throws Exception;

	public void update(@Param("id") String id) throws Exception;

	public AccountDO getAccount(@Param("nickname") String nickname)
			throws Exception;

	public List<TradeDO> getAllUserInfo() throws Exception;

	public List<String> querySensitiveWords() throws Exception;

	public String queryRefereeNickname(@Param("nickname") String nickname)
			throws Exception;

	public long queryClientCount(@Param("nickname") String nickname)
			throws Exception;

	public List<ClientPercentDO> queryClient(ClientPage clientPage)
			throws Exception;

	public ClientPercentDO getClientById(@Param("id") String id)
			throws Exception;

	public void saveClientInfo(ClientPercentDO clientPercentDO)
			throws Exception;

	public List<FundAccountDO> getFundAccount(@Param("status") String status)
			throws Exception;

	public void updateAllStatusToOne() throws Exception;

	public void updateStatusToZero(@Param("account") String account)
			throws Exception;

	public void updateStatusToOne(@Param("account") String account)
			throws Exception;

	public void loginOff(@Param("nickname") String nickname) throws Exception;

	public PhoneAndTIcketDO getUserPhone(@Param("nickname") String nickname)
			throws Exception;

	public long queryOwingFeeCount(@Param("nickname") String nickname)
			throws Exception;

	public List<OwingFeeDO> queryOwingFee(ClientPage clientPage)
			throws Exception;

	/**
	 * 获取用户真实姓名
	 * 
	 * @param nickname
	 * @return truename
	 * @throws Exception
	 */
	public String queryTrueName(@Param("nickname") String nickname)
			throws Exception;

	/**
	 * 获取总共有多少个资金账号
	 * 
	 * @return
	 * @throws Exception
	 */
	public Integer queryTotalFundAccount() throws Exception;

	/**
	 * 查询资产比例
	 * 
	 * @param assetName
	 * @return
	 * @throws Exception
	 */
	public long queryTotal(@Param("assetName") String assetName)
			throws Exception;

	/**
	 * 获取剩余资产占有比例
	 * 
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<PercentDO> queryList(PageQuery page) throws Exception;

	/**
	 * 获取资金账号，根据用户名称
	 * 
	 * @param nickname
	 * @return
	 */
	public String queryFundAccount(@Param("nickname") String nickname)
			throws Exception;

	public void updateFundAccountStatus(@Param("type") String type,
			@Param("managerAccountId") String managerAccountId)
			throws Exception;

	/**
	 * 通过用户id获取真实姓名和身份证号
	 * 
	 * @param userId
	 * @return UserDO
	 * @throws Exception
	 */
	public UserDO getUserInfo(@Param("userId") String userId) throws Exception;

	/**
	 * 获取银行信息
	 * 
	 * @param userId
	 * @return BankInfoDO
	 * @throws Exception
	 */
	public BankInfoDO getBankInfo(@Param("userId") String userId)
			throws Exception;

	/**
	 * 根据用户id获取用户名
	 * 
	 * @param string
	 * @return
	 */
	public String queryNickname(@Param("userId") String userId)
			throws Exception;

	/**
	 * 设置推荐人为空
	 * 
	 * @param nickname
	 * @throws Exception
	 */
	public void updateRefereeIsNull(@Param("nickname") String nickname)
			throws Exception;

	/**
	 * 修改提现密码
	 * 
	 * @param userId
	 * @param depositPwd
	 * @throws Exception
	 */
	public void updateDepositePwd(@Param("userId") String userId,
			@Param("depositPwd") String depositPwd) throws Exception;

	/**
	 * 更新上线比例为空
	 * 
	 * @param nickname
	 * @throws Exception
	 */
	public void updateRefereeRadioIsNull(@Param("nickname") String nickname)
			throws Exception;

	/**
	 * 验证该用户是否已经认证过了
	 * 
	 * @param nickname
	 * @return
	 * @throws Exception
	 */
	public String queryIdentifyFlag(@Param("nickname") String nickname)
			throws Exception;

	public void updateOpenBank(@Param("nickname") String nickname,
			@Param("openBank") String openBank) throws Exception;

	/**
	 * 判断是否设置提现密码
	 * 
	 * @param nickname
	 * @return
	 */
	public String queryDepositPwd(@Param("nickname") String nickname)
			throws Exception;

	/**
	 * 获取身份认证信息
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public IdentifyDO queryIdentifyInfo(@Param("userId") String userId)
			throws Exception;

	/**
	 * 获取绑定的银行卡信息
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public BindBankDO getBindCreditCard(@Param("userId") String userId)
			throws Exception;

	/**
	 * 获取基本信息记录数据
	 * 
	 * @param nickname
	 * @return
	 */
	public long queryClientInfoCount(@Param("nickname") String nickname)
			throws Exception;

	/**
	 * 获取基础信息列表
	 * 
	 * @param clientPage
	 * @return List<UserInfoDO>
	 */
	public List<UserInfoDO> queryClientInfo(ClientPage clientPage)
			throws Exception;

	/**
	 * 获取其他流水数目
	 * 
	 * @param nickname
	 * @return
	 */
	public long queryFundFlowInfoCount(@Param("nickname") String nickname);

	/**
	 * 获取其他流水列表
	 * 
	 * @param nickname
	 * @return List<OtherFundFlowDO>
	 */
	public List<OtherFundFlowDO> queryFundFlowInfo(ClientPage clientPage);

}