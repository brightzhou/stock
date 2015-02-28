package stock.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

public class TestDownloadRemoteFIle {
	public static void downloadNet() throws MalformedURLException {
		// 下载网络文件
		int bytesum = 0;
		int byteread = 0;
		URL url = new URL(
				"http://gdown.baidu.com/data/wisegame/92f91f8564b20694/baidushoujizhushou_16785162.apk");
		InputStream inStream = null;
		FileOutputStream out = null;
		try {
			URLConnection conn = url.openConnection();
			inStream = conn.getInputStream();
			out = new FileOutputStream("D:/zhushou.apk");

			byte[] buffer = new byte[1204];
			while ((byteread = inStream.read(buffer)) != -1) {
				bytesum += byteread;
				out.write(buffer, 0, byteread);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(inStream);
			IOUtils.closeQuietly(out);
		}
	}

	public static void main(String[] args) {
		try {
			downloadNet();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
