package com.zeekie.stock.service;

import java.util.Map;

import com.zeekie.stock.entity.form.RegisterForm;
import com.zeekie.stock.msg.ReturnMsg;

public interface CommonService {

	public Map<String, String> login(String nickname, String password);

	/**
	 * 注册
	 * 
	 * @param register
	 * @return
	 */
	public ReturnMsg register(RegisterForm register);

	/**
	 * 获取验证码
	 * 
	 * @param telephone
	 * @param source
	 * @return
	 */
	public String genVerifyCode(String telephone, String source);

	/**
	 * 校验验证码
	 * 
	 * @param telephone
	 * @param verifyCode
	 * @param source
	 * @return
	 */
	public boolean check(String telephone, String verifyCode, String source);

	/**
	 * 忘记密码或修改密码
	 * 
	 * @param nickname
	 * @param telephone
	 * @param oldPassword
	 *            为空则为忘记密码
	 * @param newPassward
	 * @return
	 */
	public boolean updatePwd(String nickname, String telephone,
			String oldPassword, String newPassward);

	/**
	 * 修改手势密码
	 * 
	 * @param nickname
	 * @param figurePwd
	 * @return
	 */
	public boolean updateFigurePwd(String nickname, String figurePwd);

	/**
	 * 修改手机号码
	 * 
	 * @param nickname
	 * @param telephone
	 * @param verifyCode
	 * @param newTelephone
	 * @return
	 */
	public String updateTelephone(String nickname, String telephone,
			String newTelephone, String verifyCode);

	/**
	 * 绑定手机号
	 * 
	 * @param nickname
	 * @param telephone
	 * @param verifyCode
	 * @return
	 */
	public String bindTelephone(String nickname, String telephone,
			String verifyCode);

	/**
	 * 根据手势密码获取用户信息
	 * 
	 * @param figurePwd
	 * @param nickname
	 * @return
	 */
	public Object figureLogin(String nickname, String figurePwd);

	public void loginOff(String nickname);
	
	/**
	 * 获取验证码
	 * 
	 * @param telephone
	 * @param source
	 * @return
	 */
	public String genVerifyCodeByNickname(String nickname, String source);

}
