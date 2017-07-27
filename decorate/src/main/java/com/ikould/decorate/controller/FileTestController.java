package com.ikould.decorate.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ikould.decorate.service.IAdminService;
import com.ikould.decorate.utils.PrintToClient;

@Controller
@RequestMapping(value = "/test")
public class FileTestController {

	@Autowired
	IAdminService adminService;

	@RequestMapping(value = "/file")
	public void main(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		JSONObject json = new JSONObject();

		URL filePathUrl = FileTestController.class.getResource("");
		String filePath = filePathUrl.toString();

		String serverPath = System.getProperty("user.dir");

		String proPath = request.getRealPath("/");

		String realPath2 = request.getSession().getServletContext()
				.getRealPath("");

		json.put("filePath", filePath);
		json.put("serverPath", serverPath);
		json.put("proPath", proPath);
		json.put("realPath2", realPath2);

		String filePath2 = request.getSession().getServletContext()
				.getRealPath("");

		String realFilePath = filePath2.split("decorate")[0] + "imgs";
		String createDirMsg = initDirectory(realFilePath);
		// 创建文件
		File file = new File(realFilePath + File.separator + "test.txt");
		file.setWritable(true, false);
		String error = "";
		String createFile = "文件创建成功";
		try {
			if (!file.exists()) {
				if (!file.createNewFile()) {
					createFile = "文件创建失败";
				}
			}
		} catch (IOException e) {
			error = e.toString();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		json.put("createDirMsg", createDirMsg);
		json.put("createFile", createFile);
		json.put("error", error);
		json.put("realFilePath", realFilePath);
		PrintToClient.printMsgToClient(response, json);
	}

	private void createTestFile(HttpServletRequest request) {
		String filePath = request.getSession().getServletContext()
				.getRealPath("");

		String realFilePath = filePath.split("decorate")[0] + "imgs";
		initDirectory(realFilePath);
		// 创建文件
		File file = new File(realFilePath + File.separator + "test.txt");
		try {
			if (!file.exists())
				file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 创建一个文件夹
	 *
	 * @param path
	 * @return
	 * @author YOLANDA
	 */
	public static String initDirectory(String path) {
		String result = "文件夹创建成功";
		File file = new File(path);
		if (!file.exists()) {
			if (!file.mkdir()) {
				result = "文件夹创建失败";
			}
		} else {
			result = "文件夹已存在";
		}
		return result;
	}

}
