package com.zeekie.stock.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import sitong.thinker.common.api.ApiResponse;

import com.zeekie.stock.Constants;
import com.zeekie.stock.service.syncTask.SyncHandler;
import com.zeekie.stock.util.ApiUtils;
import com.zeekie.stock.util.StringUtil;

/**
 * 
 * @author zeekie
 * 
 *         2014年11月27日
 */
@Controller
@RequestMapping("api/stock/")
public class FileController {

	static final Logger log = LoggerFactory.getLogger(FileController.class);

	@Autowired
	@Value("${apk_down_path}")
	private String apkPath;

	@Autowired
	@Value("${apk_name}")
	private String apk;

	@Autowired
	@Value("${apk_version}")
	private String version;

	@Autowired
	@Value("${pic_version}")
	private String picVersion;

	@Autowired
	@Value("${stock_root_url}")
	private String root;

	@Autowired
	@Value("${pic_main_url}")
	private String picMain;

	@Autowired
	@Value("${pic_start_url}")
	private String picStart;

	@Autowired
	private SyncHandler handler;

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "file/download")
	public void fileDownload(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(value = "flag", required = false) String flag)
			throws IOException {

		try {
			String downPath = apkPath + File.separator;
			String name = "";
			if (StringUtils.equals(apk, flag)) {
				downPath = downPath + apk;
				name = apk;
			} else if (StringUtils.equals(version, flag)) {
				downPath = downPath + version;
				name = version;
			} else if (StringUtils.equals(picVersion, flag)) {
				downPath = downPath + picVersion;
				name = picVersion;
			} else {
				log.error("unkown flag!!!");
				writeToClient(response, ApiUtils.toJSON(Constants.CODE_FAILURE,
						"param[flag] is not exists", ""));
				return;
			}

			if (StringUtils.isEmpty(downPath)) {
				log.error("file download error==>flag is empty!!");
				writeToClient(response, ApiUtils.toJSON(Constants.CODE_FAILURE,
						"param[filepath] is empty", ""));
				return;
			}
			OutputStream toClient = null;
			InputStream fis = null;
			try {
				response.reset();
				File file = new File(request.getRealPath(downPath));
				long fileLength = file.length();
				String length = String.valueOf(fileLength);
				fis = new BufferedInputStream(new FileInputStream(file));
				byte[] buffer = new byte[fis.available()];
				fis.read(buffer);

				response.setHeader("content_Length", length);
				response.setContentLength(Integer.parseInt(length));
				response.setStatus(200);
				response.addHeader("Content-Disposition",
						"attachment;filename=" + name);
				response.setContentType("application/octet-stream");

				toClient = new BufferedOutputStream(response.getOutputStream());
				toClient.write(buffer);
				toClient.flush();
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				writeToClient(response, ApiUtils.toJSON(Constants.CODE_FAILURE,
						"internal server error", ""));
			} finally {
				if (fis != null) {
					fis.close();
				}
			}
		} catch (Exception e) {
			log.error("download file error==>" + e.getMessage());
			writeToClient(response, ApiUtils.toJSON(Constants.CODE_FAILURE,
					"internal server error", ""));
			return;
		}
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "file/upload")
	public void upload(HttpServletResponse response, HttpServletRequest request)
			throws UnsupportedEncodingException, IOException {

		MultipartHttpServletRequest mult = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = mult.getFile("Fdata");
		String path = request.getRealPath("//files//");
		String fileName = multipartFile.getOriginalFilename();
		File targetFile = new File(path, StringUtil.getFileName(mult
				.getParameter("type")));
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		try {
			multipartFile.transferTo(targetFile);
			if (log.isDebugEnabled()) {
				log.debug("文件" + fileName + "上传成功");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new IOException(e.getMessage(), e);
		}
		response.getWriter().write(
				new String(fileName.getBytes("utf-8"), "GBK")); // 可以返回一个JSON字符串,

	}

	@RequestMapping(value = "picVersion/get")
	public JSONObject readPicVersionAndIP() {
		return parseString(readVersionInfo());
	}

	private JSONObject parseString(String readVersionInfo) {
		String[] result = null;
		JSONObject jo = new JSONObject();
		if (StringUtils.isNotBlank(readVersionInfo)) {
			result = readVersionInfo.split(",");
			if (null != result && result.length > 0) {
				for (String item : result) {
					String[] version = item.split("=");
					if (version.length > 0) {
						jo.put(version[0].trim()+"-version", version[1].trim());
					}
				}
			}
		} else {
			log.error("下载PIC版本文件，获取内容为空，请检查！！！");
		}
		jo.put("mainUrl", root + picMain);
		jo.put("startUrl", root + picStart);
		return jo;
	}

	private String readVersionInfo() {
		String result = "";
		DataInputStream input = null;
		HttpURLConnection conn = null;
		try {
			URL url = new URL(root + "files/" + picVersion);
			conn = (HttpURLConnection) url.openConnection();
			input = new DataInputStream(conn.getInputStream());
			byte[] buffer = new byte[1024 * 8];
			int count = 0;
			while ((count = input.read(buffer)) > 0) {
			}
			result = new String(buffer);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(input);
			conn.disconnect();
		}
		return result;
	}

	void writeToClient(HttpServletResponse response, String result) {
		try {
			response.setContentType("text/html; charset=UTF-8");
			response.getOutputStream().write(result.getBytes());
		} catch (IOException ex) {
			log.error("wrong when trying to write data", ex);
		}
	}

}
