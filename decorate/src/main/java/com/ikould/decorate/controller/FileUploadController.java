package com.ikould.decorate.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ikould.decorate.utils.PrintToClient;
import com.ikould.decorate.utils.UploadFileUtil;

@Controller
@RequestMapping(value = "/upload")
public class FileUploadController {

	/**
	 * 域名
	 */
	private static final String DOMAIN_NAME = "http://www.ikould.com";
	/**
	 * 图片文件地址（域名 + 本地址 = 图片所在地址）
	 */
	private static final String IMG_REPERTORY = "Resource" + File.separator
			+ "imgs";

	@RequestMapping(value = "/file")
	public void uploadFile(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		System.out.println(" =====================  upload");
		System.out.println(" =====================  upload");

		String filePath2 = request.getSession().getServletContext()
				.getRealPath("");
		String realFilePath = filePath2.split("decorate")[0] + IMG_REPERTORY;
		String createDirMsg = initDirectory(realFilePath);
		System.out.println(" ========= createDirMsg = " + createDirMsg);
		String fileName = UploadFileUtil.uploadFile(request, response,
				realFilePath);
		JSONObject resultJson = new JSONObject();
		resultJson.put("filePath", DOMAIN_NAME + File.separator + IMG_REPERTORY
				+ File.separator + fileName);
		System.out.println(" ====  resultJson = " + resultJson);
		System.out.println(" =====================  upload");
		System.out.println(" =====================  upload");
		PrintToClient.printMsgToClient(response, resultJson);
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
			if (!file.mkdirs()) {
				result = "文件夹创建失败";
			}
		} else {
			result = "文件夹已存在";
		}
		return result;
	}
}