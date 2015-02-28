package com.zeekie.stock;

import sitong.thinker.common.message.ResultMessage;

/**
 * @author zeekie
 * @version 1.0, 2014/05/27
 */
public class StockResultMessage extends ResultMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 成功 */
	public static final StockResultMessage SUCCESS = newMessage();

	/** 内部服务异常 */
	public static final StockResultMessage INTERNAL_SERVER_ERROR = newMessage();

	/** 参数出错 */
	public static final StockResultMessage BAD_REQUEST = newMessage();

}
