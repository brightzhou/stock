package com.zeekie.stock.respository;

import org.apache.ibatis.annotations.Param;

import com.zeekie.stock.entity.CurrentOperationDO;
import com.zeekie.stock.entity.LoginDO;
import com.zeekie.stock.entity.form.RegisterForm;
import com.zeekie.stock.mybatis.MyBatisRepository;

@MyBatisRepository
public interface StockMapper {

	public LoginDO login(@Param("nickname") String nickname,
			@Param("password") String password) throws Exception;

	public void register(RegisterForm form) throws Exception;

	public String isExists(@Param("nickname") String nickname,
			@Param("telephone") String telephone) throws Exception;

	public void genVerifyCode(@Param("telephone") String telephone,
			@Param("verifyCode") String verifyCode,
			@Param("source") String source) throws Exception;

	public String checkVerifyCode(@Param("telephone") String telephone,
			@Param("verifyCode") String verifyCode,
			@Param("interval") int interval, @Param("source") String source)
			throws Exception;

	public void setVerifyCodeExpired(String telephone) throws Exception;

	public void updatePwd(@Param("nickname") String nickname,
			@Param("telephone") String telephone,
			@Param("newPassward") String newPassward) throws Exception;

	public String isvalidPassword(@Param("nickname") String nickname,
			@Param("telephone") String telephone,
			@Param("oldPassward") String oldPassward) throws Exception;

	public void updateOperateMainInfo(@Param("currentCash") String currentCash,
			@Param("marketValue") String marketValue,
			@Param("nickname") String nickname) throws Exception;

	public void updateProfitAndLoss(@Param("currentCash") String currentCash,
			@Param("marketValue") String marketValue,
			@Param("nickname") String nickname) throws Exception;

	public CurrentOperationDO getCurrentOperation(
			@Param("nickname") String nickname) throws Exception;

	public void setBlankWallet(@Param("nickname") String nickname)
			throws Exception;

	public void updatePwdUserIsNull(@Param("nickname") String nickname,
			@Param("telephone") String telephone,
			@Param("newPassward") String newPassward) throws Exception;

	public LoginDO figureLogin(@Param("nickname") String nickname,
			@Param("figurePwd") String figurePwd) throws Exception;

	public String queryLoginStatus(@Param("nickname") String nickname)
			throws Exception;

	public void updateLoginStatus(@Param("userId") String userId)
			throws Exception;

	public long querySeq(@Param("seq") String seq) throws Exception;

}