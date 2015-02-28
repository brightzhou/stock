package com.zeekie.stock.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zeekie.stock.Constants;

@Controller
@RequestMapping("api/stock/web")
public class TestControllerForZc {

	static final Logger log = LoggerFactory
			.getLogger(TestControllerForZc.class);

	@ResponseBody
	@RequestMapping(value = "zc/getUrl")
	public void saveFileAdress(
			@RequestParam(value = "address", required = true) String address) {
		Constants.fileAdress = address;
	}

	@ResponseBody
	@RequestMapping(value = "zc/returnAdress")
	public String getAdress() {
		return Constants.fileAdress;
	}

	@ResponseBody
	@RequestMapping(value = "zc/clearAdress")
	public void clearAdress() {
		Constants.fileAdress = "";
	}
}
