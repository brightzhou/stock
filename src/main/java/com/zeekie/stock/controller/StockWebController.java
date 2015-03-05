package com.zeekie.stock.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
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
			@RequestParam(value = "type", required = true) String fundAccount) {
		return webService.addTotalFund(fund, fundAccount) ? Constants.CODE_SUCCESS
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
	public void acceptCall(HttpServletRequest request) {
		byte[] bt = null;
		InputStream stream = null;
		ByteArrayOutputStream out = null;
		try {
			stream = request.getInputStream();
			out = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int len = 0;
			while ((len = stream.read(b)) != -1) {
				out.write(b, 0, len);
			}
			stream.close();
			out.close();
			bt = out.toByteArray();

			log.debug("收到支付宝的调用返回：" + bt.toString());
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} finally {
			try {
				if (stream != null)
					stream.close();
			} catch (IOException e1) {
				log.error(e1.getMessage(), e1);
			}
			try {
				if (out != null)
					out.close();
			} catch (IOException e1) {
				log.error(e1.getMessage(), e1);
			}
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
	}

}
