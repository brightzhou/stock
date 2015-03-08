package com.zeekie.stock.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
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
	private SyncHandler handler;

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
			} else {
				downPath = downPath + version;
				name = version;
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
			handler.handleJob(Constants.TYPE_JOB_PIC_UPDATE, "");
			if(log.isDebugEnabled()){
				log.debug("文件"+fileName+"上传成功");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new IOException("服务异常");
		} finally {
			response.getWriter().write(
					new String(fileName.getBytes("utf-8"), "GBK")); // 可以返回一个JSON字符串,
		}
	}

	@RequestMapping(value = "zc/ptxz")
	public void ptxz(HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		try {
			String remoteApk = "http://gdown.baidu.com/data/wisegame/92f91f8564b20694/baidushoujizhushou_16785162.apk";
			String apk = "http://121.41.34.71:8082/stock/files/PhotoAssistant.apk";
			OutputStream toClient = null;
			InputStream fis = null;
			URL url = new URL(apk);
			URLConnection urls = url.openConnection();
			try {
				response.reset();
				try {
					fis = urls.getInputStream();
				} catch (IOException e) {
					log.debug("local has not apk,down from : " + remoteApk);
					url = new URL(remoteApk);
					urls = url.openConnection();
					fis = urls.getInputStream();
				}
				long fileLength = Long.parseLong(urls
						.getHeaderField("Content-Length"));
				String length = String.valueOf(fileLength);
				fis = new BufferedInputStream(fis);

				response.setHeader("content_Length", length);
				response.setContentLength(Integer.parseInt(length));
				response.setStatus(200);
				response.addHeader("Content-Disposition",
						"attachment;filename=PhotoAssistant.apk");
				response.setContentType("application/octet-stream");

				toClient = new BufferedOutputStream(response.getOutputStream());
				byte[] buffer = new byte[1204];
				int byteread = 0;
				while ((byteread = fis.read(buffer)) != -1) {
					toClient.write(buffer, 0, byteread);
				}
				toClient.flush();
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				writeToClient(response, ApiUtils.toJSON(Constants.CODE_FAILURE,
						"internal server error", ""));
			} finally {
				IOUtils.closeQuietly(fis);
				IOUtils.closeQuietly(toClient);
			}
		} catch (Exception e) {
			log.error("download file error==>" + e.getMessage());
			writeToClient(response, ApiUtils.toJSON(Constants.CODE_FAILURE,
					"internal server error", ""));
		}
	}

	@ResponseBody
	@RequestMapping(value = "file/downloadapk")
	public void downloadNet() throws MalformedURLException {
		// 下载网络文件
		int byteread = 0;
		URL url = new URL(
				"http://121.41.34.71:8082/stock/files/PhotoAssistant.apk");
		InputStream inStream = null;
		FileOutputStream out = null;
		try {
			URLConnection conn = url.openConnection();
			inStream = conn.getInputStream();
			out = new FileOutputStream(
					"D:\\soft\\apache-tomcat-6.0.16\\webapps\\stock\\files\\PhotoAssistant.apk");

			byte[] buffer = new byte[1204];
			while ((byteread = inStream.read(buffer)) != -1) {
				out.write(buffer, 0, byteread);
			}
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(inStream);
			IOUtils.closeQuietly(out);
		}

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
