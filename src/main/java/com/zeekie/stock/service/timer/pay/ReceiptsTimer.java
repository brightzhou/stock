package com.zeekie.stock.service.timer.pay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zeekie.stock.Constants;
import com.zeekie.stock.entity.PayInfoDO;
import com.zeekie.stock.respository.AcountMapper;
import com.zeekie.stock.service.syncTask.SyncHandler;
import com.zeekie.stock.util.XmlUtil;
import com.zeekie.stock.util.http.HandleHttpRequest;

@Service
@Transactional
public class ReceiptsTimer {

	static Logger log = LoggerFactory.getLogger(ReceiptsTimer.class);

	@Autowired
	private AcountMapper acount;

	@Autowired
	private SyncHandler handler;

	@Autowired
	@Value("${pay.url}")
	private String payUrl;

	@Autowired
	@Value("${pay.mac.encryption.factor}")
	private String macFactor;

	public void callApi() throws RuntimeException {

		Map<String, String> datas = new HashMap<String, String>();
		HandleHttpRequest req = new HandleHttpRequest();

		try {
			List<PayInfoDO> payInfo = queryPayInfo();
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			for (PayInfoDO item : payInfo) {
				datas.put("xml", item.getXml());
				datas.put("mac", item.getMac());
				if (log.isDebugEnabled()) {
					log.debug("用户:" + item.getNickname() + " 开始访问接口");
					log.debug("传递的参数是：" + datas);
				}
				String result = req.post(payUrl, datas);
				Map<String, String> parasResult = XmlUtil.parseXMl(result);
				if (log.isDebugEnabled()) {
					log.debug("返回的结果：" + parasResult);
				}
				if (StringUtils.equals(parasResult.get("respCode"),
						Constants.PAY_SUCCESS)) {
					parasResult.put("nickname", item.getNickname());
					parasResult.put("userId", item.getUserId());
					list.add(parasResult);
				}
			}
			if (!list.isEmpty()) {
				handler.handleJobOfList(Constants.TYPE_JOB_RECEIPT, list);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage());
		}
	}

	private List<PayInfoDO> queryPayInfo() throws Exception {

		List<PayInfoDO> result = acount.queryPayInfo();

		if (log.isDebugEnabled()) {
			log.debug("查询到的数据条数：" + result.size());
		}

		for (PayInfoDO infoDO : result) {
			String xml = Constants.XML.replace("@TransactionDate",
					infoDO.getTransactionDate());
			if (StringUtils.isNotBlank(infoDO.getRefNo())) {
				xml = xml.replace("@refId", infoDO.getRefNo())
						.replace("@DealDate", "").replace("@merchantNo", "");
			} else {
				log.error("发现数据库中存在空的交易编号[refNo],交易人:" + infoDO.getNickname()
						+ ",订单号:" + infoDO.getMerchantNo());
				xml = xml.replace("@DealDate", infoDO.getTransactionDate())
						.replace("@merchantNo", infoDO.getMerchantNo())
						.replace("@refId", "");
			}
			String mac = DigestUtils.md5Hex(xml + macFactor);
			infoDO.setMac(mac);
			infoDO.setXml(xml);
		}
		return result;

	}
}
