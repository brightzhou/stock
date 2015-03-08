package com.zeekie.stock.handler;

import java.io.File;

import org.apache.commons.lang.StringUtils;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File file) {
		String body = "";
		String ext = "";
		String filename = file.getName();
		int pot = file.getName().lastIndexOf(".");
		if (pot != -1) {
			ext = filename.substring(pot);
			if (!StringUtils.equals("jpg", ext)) {
				ext = "jpg";
			}
			body = filename.substring(0, pot);
		}
		String newName = body + ext;
		file = new File(file.getParent(), newName);
		return file;
	}

}
