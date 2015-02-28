package com.zeekie.stock.entity.form;

public class RegisterForm {

	private Long id;

	private String nickname;

	private String telephone;

	private String password;

	private String verifyCode;

	private String figurePwd;

	/**
	 * 推荐人ID
	 */
	private String refereeId;

	/**
	 * @return the refereeId
	 */
	public String getRefereeId() {
		return refereeId;
	}

	/**
	 * @param refereeId
	 *            the refereeId to set
	 */
	public void setRefereeId(String refereeId) {
		this.refereeId = refereeId;
	}

	/**
	 * @return the figurePwd
	 */
	public String getFigurePwd() {
		return figurePwd;
	}

	/**
	 * @param figurePwd
	 *            the figurePwd to set
	 */
	public void setFigurePwd(String figurePwd) {
		this.figurePwd = figurePwd;
	}

	/**
	 * @return the verifyCode
	 */
	public String getVerifyCode() {
		return verifyCode;
	}

	/**
	 * @param verifyCode
	 *            the verifyCode to set
	 */
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
