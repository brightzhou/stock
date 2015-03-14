package com.zeekie.stock.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sitong.thinker.common.exception.ServiceInvokerException;
import sitong.thinker.common.page.DefaultPage;

import com.zeekie.stock.Constants;
import com.zeekie.stock.entity.ClientPercentDO;
import com.zeekie.stock.entity.CurrentOperationWebDO;
import com.zeekie.stock.entity.MovecashToRefereeDO;
import com.zeekie.stock.entity.OwingFeeDO;
import com.zeekie.stock.entity.PayDO;
import com.zeekie.stock.entity.PercentDO;
import com.zeekie.stock.entity.TotalFundDO;
import com.zeekie.stock.entity.WithdrawlDO;
import com.zeekie.stock.service.WebService;
import com.zeekie.stock.service.syncTask.SyncHandler;
import com.zeekie.stock.util.StringUtil;
import com.zeekie.stock.util.XmlUtil;
import com.zeekie.stock.web.ClientPage;
import com.zeekie.stock.web.EveningUpPage;
import com.zeekie.stock.web.MoveToRefereePage;
import com.zeekie.stock.web.PayPage;
import com.zeekie.stock.web.PercentDOPage;
import com.zeekie.stock.web.TotalFundPage;
import com.zeekie.stock.web.WithdrawlPage;

/**
 * @Author zeekie
 * @date 2014年7月23日
 */
@Controller
@RequestMapping("api/stock/web")
public class StockWebController {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private WebService webService;

	@Autowired
	@Value("${manager}")
	private String manager;

	@Autowired
	@Value("${manager_pwd}")
	private String manager_pwd;

	@Autowired
	private SyncHandler handler;

	@ResponseBody
	@RequestMapping("userWithdrawl/get")
	public DefaultPage<WithdrawlDO> getDepositList(
			HttpServletRequest request,
			@RequestParam(value = "pageIndex", required = false) String pageIndex,
			@RequestParam(value = "pageSize", required = false) String pageSize,
			@RequestParam(value = "sortField", required = false) String sortField,
			@RequestParam(value = "sortOrder", required = false) String sortOrder,
			@RequestParam(value = "nickname", required = false) String nickname,
			@RequestParam(value = "depositType", required = false) String depositType) {
		try {
			pageIndex = StringUtils.defaultIfBlank(pageIndex, "0");
			pageSize = StringUtils.defaultIfBlank(pageSize, "10");

			WithdrawlPage withdrawlPage = new WithdrawlPage(
					Long.valueOf(pageIndex), Long.valueOf(pageSize), sortField,
					sortOrder, nickname, depositType);

			return webService.getDepositList(withdrawlPage);
		} catch (ServiceInvokerException e) {
			log.error("query getDepositList error happened:", e.getMessage());
			return new DefaultPage<WithdrawlDO>();
		}
	}

	@ResponseBody
	@RequestMapping("manager/totalFund/get")
	public DefaultPage<TotalFundDO> getTotalFund(
			HttpServletRequest request,
			@RequestParam(value = "pageIndex", required = false) String pageIndex,
			@RequestParam(value = "pageSize", required = false) String pageSize,
			@RequestParam(value = "sortField", required = false) String sortField,
			@RequestParam(value = "sortOrder", required = false) String sortOrder,
			@RequestParam(value = "type", required = false) String fundAccount) {
		try {
			pageIndex = StringUtils.defaultIfBlank(pageIndex, "0");
			pageSize = StringUtils.defaultIfBlank(pageSize, "10");

			TotalFundPage totalFundPage = new TotalFundPage(
					Long.valueOf(pageIndex), Long.valueOf(pageSize), sortField,
					sortOrder, fundAccount);

			return webService.getTotalFund(totalFundPage);
		} catch (ServiceInvokerException e) {
			log.error("query getTotalFund error happened:", e.getMessage());
			return new DefaultPage<TotalFundDO>();
		}
	}

	@ResponseBody
	@RequestMapping("manager/getFundAccount")
	public String getFundAccount() {
		try {
			return webService.getFundAccount("1");
		} catch (ServiceInvokerException e) {
			log.error("query getFundAccount error happened:", e.getMessage());
		}
		return "";
	}

	@ResponseBody
	@RequestMapping("manager/totalFund/add")
	public String addTotalFund(
			@RequestParam(value = "fund", required = true) String fund,
			@RequestParam(value = "type", required = true) String fundAccount,
			@RequestParam(value = "desc", required = true) String desc,
			@RequestParam(value = "storeType", required = true) String storeType) {
		return webService.addTotalFund(fund, fundAccount, desc, storeType) ? Constants.CODE_SUCCESS
				: Constants.CODE_FAILURE;
	}

	@ResponseBody
	@RequestMapping("caculatePercent")
	public DefaultPage<PercentDO> caculatePercent(
			HttpServletRequest request,
			@RequestParam(value = "pageIndex", required = false) String pageIndex,
			@RequestParam(value = "pageSize", required = false) String pageSize,
			@RequestParam(value = "sortField", required = false) String sortField,
			@RequestParam(value = "sortOrder", required = false) String sortOrder,
			@RequestParam(value = "assetName", required = false) String assetName) {
		try {
			pageIndex = StringUtils.defaultIfBlank(pageIndex, "0");
			pageSize = StringUtils.defaultIfBlank(pageSize, "10");

			PercentDOPage page = new PercentDOPage(Long.valueOf(pageIndex),
					Long.valueOf(pageSize), sortField, sortOrder, assetName);

			return webService.caculatePercent(page);
		} catch (ServiceInvokerException e) {
			log.error("query queryMoveCashToReferee error happened:",
					e.getMessage());
			return new DefaultPage<PercentDO>();
		}
	}

	@ResponseBody
	@RequestMapping("withdrawlToUser")
	public String withdrawlToUser(
			@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "nickname", required = true) String nickname,
			@RequestParam(value = "cash", required = true) String cash) {
		return webService.withdrawlToUser(id, nickname, cash) ? Constants.CODE_SUCCESS
				: Constants.CODE_FAILURE;
	}

	@ResponseBody
	@RequestMapping("manager/moveCashToReferee/get")
	public DefaultPage<MovecashToRefereeDO> queryMoveCashToReferee(
			HttpServletRequest request,
			@RequestParam(value = "pageIndex", required = false) String pageIndex,
			@RequestParam(value = "pageSize", required = false) String pageSize,
			@RequestParam(value = "sortField", required = false) String sortField,
			@RequestParam(value = "sortOrder", required = false) String sortOrder,
			@RequestParam(value = "nickname", required = false) String nickname,
			@RequestParam(value = "referee", required = false) String referee) {
		try {
			pageIndex = StringUtils.defaultIfBlank(pageIndex, "0");
			pageSize = StringUtils.defaultIfBlank(pageSize, "10");

			MoveToRefereePage moveToRefereePage = new MoveToRefereePage(
					Long.valueOf(pageIndex), Long.valueOf(pageSize), sortField,
					sortOrder, nickname, referee);

			return webService.queryMoveCashToReferee(moveToRefereePage);
		} catch (ServiceInvokerException e) {
			log.error("query queryMoveCashToReferee error happened:",
					e.getMessage());
			return new DefaultPage<MovecashToRefereeDO>();
		}
	}

	@ResponseBody
	@RequestMapping("pay/get")
	public DefaultPage<PayDO> getPayList(
			HttpServletRequest request,
			@RequestParam(value = "pageIndex", required = false) String pageIndex,
			@RequestParam(value = "pageSize", required = false) String pageSize,
			@RequestParam(value = "sortField", required = false) String sortField,
			@RequestParam(value = "sortOrder", required = false) String sortOrder) {
		try {
			pageIndex = StringUtils.defaultIfBlank(pageIndex, "0");
			pageSize = StringUtils.defaultIfBlank(pageSize, "10");

			PayPage payPage = new PayPage(Long.valueOf(pageIndex),
					Long.valueOf(pageSize), sortField, sortOrder);

			return webService.getPayList(payPage);
		} catch (ServiceInvokerException e) {
			log.error("query getPayList error happened:", e.getMessage());
			return new DefaultPage<PayDO>();
		}
	}

	@ResponseBody
	@RequestMapping("payToUs")
	public String payToUs(
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "nickname", required = true) String nickname,
			@RequestParam(value = "fund", required = true) String fund) {
		return webService.payToUs(id, nickname, fund) ? Constants.CODE_SUCCESS
				: Constants.CODE_FAILURE;
	}

	@ResponseBody
	@RequestMapping("eveningUp/get")
	public DefaultPage<CurrentOperationWebDO> getEveningUp(
			HttpServletRequest request,
			@RequestParam(value = "pageIndex", required = false) String pageIndex,
			@RequestParam(value = "pageSize", required = false) String pageSize,
			@RequestParam(value = "sortField", required = false) String sortField,
			@RequestParam(value = "sortOrder", required = false) String sortOrder,
			@RequestParam(value = "nickname", required = false) String nickname) {
		try {
			pageIndex = StringUtils.defaultIfBlank(pageIndex, "0");
			pageSize = StringUtils.defaultIfBlank(pageSize, "10");

			EveningUpPage eveningUpPage = new EveningUpPage(
					Long.valueOf(pageIndex), Long.valueOf(pageSize), sortField,
					sortOrder, nickname);

			return webService.getEveningUp(eveningUpPage);
		} catch (ServiceInvokerException e) {
			log.error("query getEveningUp error happened:", e.getMessage());
			return new DefaultPage<CurrentOperationWebDO>();
		}
	}

	@ResponseBody
	@RequestMapping("eveningUp")
	public String eveningUp(
			@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "nickname", required = true) String nickname,
			@RequestParam(value = "userId", required = true) String userId) {
		String flag = webService.eveningUp(id, nickname) ? Constants.CODE_SUCCESS
				: Constants.CODE_FAILURE;
		if (StringUtils.equals(Constants.CODE_SUCCESS, flag)) {
			Map<String, String> param = new HashMap<String, String>();
			param.put("operationId", id);
			param.put("nickname", nickname);
			param.put("userId", userId);
			handler.handleOtherJob(Constants.TYPE_JOB_EVENING_UP_REMIND, param);
		}
		return flag;
	}

	@ResponseBody
	@RequestMapping("skip/mainPage")
	public String mainPage(
			HttpServletRequest request,
			@RequestParam(value = "submitData", required = true) String submitData) {

		JSONObject jo = JSONObject.fromObject(submitData);
		String user = jo.getString("user");
		String pwd = jo.getString("pwd");
		// String skipPage = "error";
		if (StringUtils.equals(manager, user)
				&& StringUtils.equals(manager_pwd, pwd)) {
			// skipPage = "main";
			request.getSession().setAttribute("user", user);
			return "1";
		}
		// ModelAndView view = new ModelAndView(skipPage);
		return "0";
	}

	@ResponseBody
	@RequestMapping("logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute("user", "");
		request.getSession().invalidate();
		try {
			response.sendRedirect("/stock/index.jsp");
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	@ResponseBody
	@RequestMapping("setFeeCalendar")
	public String setFeeCalendar(
			@RequestParam(value = "yearMonth", required = true) String yearMonth,
			@RequestParam(value = "days", required = true) String days) {
		try {
			webService.setFeeCalendar(yearMonth, days);
		} catch (ServiceInvokerException e) {
			return "0";
		}
		return "1";

	}

	@ResponseBody
	@RequestMapping("setDays")
	public String setDays(
			@RequestParam(value = "month", required = true) String month) {
		JSONObject jo = new JSONObject();
		JSONArray result = StringUtil.getCalendar(month);
		jo.put("calendar", result.toString());
		jo.put("feeDay", webService.initFeeDays(month));
		return jo.toString();
	}

	@ResponseBody
	@RequestMapping("acceptCall")
	public void acceptCall(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String xml = getStreamString(request.getInputStream());
			if (StringUtils.isNotBlank(xml)) {
				Map<String, String> parasResult = XmlUtil.readStringXmlOut(xml);
				if (log.isDebugEnabled()) {
					log.debug("获取支付回调结果：" + parasResult);
				}

				String rechargeResult = StringUtils.equals(
						parasResult.get("respMsg"), "交易成功") ? Constants.CODE_SUCCESS
						: Constants.CODE_FAILURE;
				parasResult.put("rechargeResult", rechargeResult);

				handler.handleOtherJob(Constants.TYPE_JOB_PAY_NOTICE,
						parasResult);

				if (StringUtils.equals(rechargeResult, Constants.CODE_SUCCESS)) {
					if (log.isDebugEnabled()) {
						log.debug("支付失败，不推送消息");
					}
				} else {
					if (log.isDebugEnabled()) {
						log.debug("支付失败，不推送消息");
					}
				}
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} catch (DocumentException e) {
			log.error(e.getMessage(), e);
		}
	}

	@ResponseBody
	@RequestMapping("getPayMsg")
	public String getPayMsg(
			@RequestParam(value = "nickname", required = true) String nickname) {
		JSONObject jo = new JSONObject();

		return jo.toString();
	}

	@ResponseBody
	@RequestMapping("getClient")
	public DefaultPage<ClientPercentDO> getClient(
			HttpServletRequest request,
			@RequestParam(value = "pageIndex", required = false) String pageIndex,
			@RequestParam(value = "pageSize", required = false) String pageSize,
			@RequestParam(value = "sortField", required = false) String sortField,
			@RequestParam(value = "sortOrder", required = false) String sortOrder,
			@RequestParam(value = "nickname", required = false) String nickname) {
		try {
			pageIndex = StringUtils.defaultIfBlank(pageIndex, "0");
			pageSize = StringUtils.defaultIfBlank(pageSize, "10");

			ClientPage clientPage = new ClientPage(Long.valueOf(pageIndex),
					Long.valueOf(pageSize), sortField, sortOrder, nickname);

			return webService.getClient(clientPage);
		} catch (ServiceInvokerException e) {
			log.error("query ClientPage error happened:", e.getMessage());
			return new DefaultPage<ClientPercentDO>();
		}
	}

	@ResponseBody
	@RequestMapping("getClientById")
	public String getClientById(
			@RequestParam(value = "id", required = false) String id) {
		try {
			return webService.getClientById(id);
		} catch (ServiceInvokerException e) {
			log.error("query ClientPage error happened:", e.getMessage());
			return "";
		}
	}

	@ResponseBody
	@RequestMapping("saveClientInfo")
	public String saveClientInfo(
			@RequestParam(value = "data", required = false) String data) {
		try {
			return webService.saveClientInfo(data);
		} catch (ServiceInvokerException e) {
			log.error("saveClientInfo error happened:", e.getMessage());
			return "0";
		}
	}

	@ResponseBody
	@RequestMapping("getFundAccount")
	public String getFundAccount(
			@RequestParam(value = "status", required = false) String status) {
		try {
			return webService.getFundAccount(status);
		} catch (ServiceInvokerException e) {
			log.error("getFundAccount error happened:", e.getMessage());
			return "";
		}
	}

	@ResponseBody
	@RequestMapping("saveFundAccount")
	public String saveFundAccount(
			@RequestParam(value = "data", required = false) String data) {
		try {
			return webService.saveFundAccount(data);
		} catch (ServiceInvokerException e) {
			log.error("saveFundAccount error happened:", e.getMessage());
			return "0";
		}
	}

	@ResponseBody
	@RequestMapping("getOwingFee")
	public DefaultPage<OwingFeeDO> getOwingFee(
			HttpServletRequest request,
			@RequestParam(value = "pageIndex", required = false) String pageIndex,
			@RequestParam(value = "pageSize", required = false) String pageSize,
			@RequestParam(value = "sortField", required = false) String sortField,
			@RequestParam(value = "sortOrder", required = false) String sortOrder,
			@RequestParam(value = "nickname", required = false) String nickname) {
		try {
			pageIndex = StringUtils.defaultIfBlank(pageIndex, "0");
			pageSize = StringUtils.defaultIfBlank(pageSize, "10");

			ClientPage clientPage = new ClientPage(Long.valueOf(pageIndex),
					Long.valueOf(pageSize), sortField, sortOrder, nickname);

			return webService.getOwingFee(clientPage);
		} catch (ServiceInvokerException e) {
			log.error("query ClientPage error happened:", e.getMessage());
			return new DefaultPage<OwingFeeDO>();
		}
	}

	@ResponseBody
	@RequestMapping("manager/openOrCloseApp")
	public void openApp(@RequestParam("flag") String flag) {
		webService.openOrCloseApp(flag);
		Constants.HOMES_STATUS = flag;
	}

	@ResponseBody
	@RequestMapping("manager/getAppStatus")
	public String getAppStatus() {
		return Constants.HOMES_STATUS;
	}

	/**
	 * 将一个输入流转化为字符串
	 */
	private String getStreamString(InputStream in) {
		if (in != null) {
			try {
				BufferedReader tBufferedReader = new BufferedReader(
						new InputStreamReader(in));
				StringBuffer xml = new StringBuffer();
				String sTempOneLine = new String("");
				while ((sTempOneLine = tBufferedReader.readLine()) != null) {
					xml.append(sTempOneLine);
				}

				String originResult = StringUtil.getResult(xml.toString());
				if (log.isDebugEnabled()) {
					log.debug("原始结果：" + originResult);
				}
				String convertResult = URLDecoder.decode(new String(
						originResult.getBytes()), "UTF-8");
				if (log.isDebugEnabled()) {
					log.debug("回调函数转换返回结果为：" + convertResult);
				}
				return convertResult;
			} catch (Exception ex) {
				log.error(ex.getMessage(), ex);
			} finally {
				IOUtils.closeQuietly(in);
			}
		} else {
			if (log.isDebugEnabled()) {
				log.debug("获取支付回调结果为空");
			}
		}
		return "";
	}

}
