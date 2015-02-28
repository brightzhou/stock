package com.zeekie.stock.entity;

public class RedPacketDO {

	private String assignRefereeRedPacket;

	private String assignRegisterRedPacket;

	private Float assignRefereeDrawPercent;

	private String userId;

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the assignRefereeRedPacket
	 */
	public String getAssignRefereeRedPacket() {
		return assignRefereeRedPacket;
	}

	/**
	 * @param assignRefereeRedPacket
	 *            the assignRefereeRedPacket to set
	 */
	public void setAssignRefereeRedPacket(String assignRefereeRedPacket) {
		this.assignRefereeRedPacket = assignRefereeRedPacket;
	}

	/**
	 * @return the assignRegisterRedPacket
	 */
	public String getAssignRegisterRedPacket() {
		return assignRegisterRedPacket;
	}

	/**
	 * @param assignRegisterRedPacket
	 *            the assignRegisterRedPacket to set
	 */
	public void setAssignRegisterRedPacket(String assignRegisterRedPacket) {
		this.assignRegisterRedPacket = assignRegisterRedPacket;
	}

	/**
	 * @return the assignRefereeDrawPercent
	 */
	public Float getAssignRefereeDrawPercent() {
		return assignRefereeDrawPercent;
	}

	/**
	 * @param assignRefereeDrawPercent
	 *            the assignRefereeDrawPercent to set
	 */
	public void setAssignRefereeDrawPercent(Float assignRefereeDrawPercent) {
		this.assignRefereeDrawPercent = assignRefereeDrawPercent;
	}

}
