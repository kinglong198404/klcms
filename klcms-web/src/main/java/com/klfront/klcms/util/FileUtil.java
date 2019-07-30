package com.klfront.klcms.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

/** 
 * <p>Title: FileUtil.java</p>
 * <p>Description:文件上传、下载等工具类 </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: www.klfront.com</p>
 *
 * @author Lu Jinlong
 * @date 2019-07-13  
 * @version	1.0  
 */

public class FileUtil {
	public static String getFileExt(MultipartFile file) {
		String fileExt = "";
		String fileAllName = file.getOriginalFilename();
		int fileTypeIndex = file.getOriginalFilename().lastIndexOf(".");
		if (fileTypeIndex != -1) {
			fileExt = fileAllName.substring(fileTypeIndex, fileAllName.length());
		}
		return fileExt;
	}
	
	/**
	 * 保存上传的文件
	 *
	 * @author Lu Jinlong
	 *
	 * @param file	上传的文件对象
	 * @param folderPath 文件要保存的目录
	 * @return 返回保存的路径
	 * @throws Exception
	 */
	public static String saveFile(MultipartFile file, String folderPath)
			throws Exception {
		OutputStream os = null;
		InputStream inputStream = null;
		String filePath = null;
		try {
			inputStream = file.getInputStream();
			// 1K的字节池
			byte[] bs = new byte[1024];
			int len;
			File folder = new File(folderPath);
			if (!folder.exists())
				folder.mkdirs();

			String fileType = getFileExt(file);
			if(fileType.isEmpty()) {
				fileType = ".tmp";
			}
			filePath = folder.getPath() + File.separator +UUID.randomUUID().toString().replaceAll("-", "")  + fileType;

			os = new FileOutputStream(filePath);
			// 读取数据写入文件
			while ((len = inputStream.read(bs)) != -1) {
				os.write(bs, 0, len);
			}

		} finally {
			if (os != null)
				os.close();
			if (inputStream != null)
				inputStream.close();
		}
		return filePath;
	}

}
