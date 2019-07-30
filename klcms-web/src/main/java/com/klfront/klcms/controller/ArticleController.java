package com.klfront.klcms.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.klfront.klcms.entity.Article;
import com.klfront.klcms.entity.Category;
import com.klfront.klcms.service.ArticleService;
import com.klfront.klcms.service.CategoryService;
import com.klfront.klcms.util.FileUtil;
import com.klfront.klcms.util.HtmlExtract;
import com.klfront.klcms.util.UrlEncodeUtil;

/**
 * <p>
 * Title: ArticleController.java
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2019
 * </p>
 * <p>
 * Company: www.klfront.com
 * </p>
 *
 * @author Lu Jinlong
 * @date 2019-07-06
 * @version 1.0
 */
@Controller
public class ArticleController {
	private static Integer pageSize = 8;

	@Autowired
	private ArticleService service;

	@Autowired
	private CategoryService catService;

	@Value("${upload.root-path}")
	private String rootFolderPath;

	/**
	 * 主页：freeemarker模板引擎
	 *
	 * @author Lu Jinlong
	 *
	 * @param map
	 * @param category
	 * @param page     第几页，从1开始
	 * @param keyword
	 * @return
	 */
	@RequestMapping("/index.html")
	public String index(ModelMap map, @RequestParam(value = "category", required = false) String category,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "page", required = false) Integer page) {
		if (page == null) {
			page = 1;
		}
		Integer pageIndex = page - 1;

		List<Article> list = service.findByPage(category, keyword, pageIndex, pageSize);
		list.forEach(item -> {
			String text = item.getText();
			if (text == null) {
				text = item.getContent();
			}
			if (text.length() > 300) {
				text = text.substring(0, 300) + "...";
				item.setText(text);
			}
		});
		Integer pageCount = service.getPageCount(category, keyword, pageSize);
		List<Category> categories = catService.findAll();

		if (null != category) {
			map.put("categoryId", category);
		} else {
			map.put("categoryId", "02");
		}
		if (keyword != null) {
			map.put("keyword", keyword);
			// 解决"C#"这样包含特殊字符的关键字，在分页链接中的编码需求
			String encodeKeyword = UrlEncodeUtil.encodeURIComponent(keyword);
			map.put("encodeKeyword", encodeKeyword);
		}

		map.put("articles", list);
		map.put("page", page);
		map.put("pageCount", pageCount);
		map.put("categories", categories);
		map.put("websites", null);
		return "index";
	}

	@RequestMapping("/admin.html")
	public String admin(ModelMap map, @RequestParam(value = "category", required = false) String category,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "page", required = false) Integer page) {
		if (page == null) {
			page = 1;
		}
		Integer pageIndex = page - 1;

		List<Article> list = service.findByPage(category, keyword, pageIndex, pageSize);
		list.forEach(item -> {
			String text = item.getText();
			if (text == null) {
				text = item.getContent();
			}
			if (text.length() > 300) {
				text = text.substring(0, 300) + "...";
				item.setText(text);
			}
		});
		Integer pageCount = service.getPageCount(category, keyword, pageSize);
		List<Category> categories = catService.findAll();

		if (null != category) {
			map.put("categoryId", category);
		}
		if (keyword != null) {
			map.put("keyword", keyword);
			// 解决"C#"这样包含特殊字符的关键字，在分页链接中的编码需求
			String encodeKeyword = UrlEncodeUtil.encodeURIComponent(keyword);
			map.put("encodeKeyword", encodeKeyword);
		}

		map.put("articles", list);
		map.put("page", page);
		map.put("pageCount", pageCount);
		map.put("categories", categories);
		map.put("websites", null);
		return "admin";
	}

	@RequestMapping("/item/{id}.html")
	public String item(ModelMap map, @PathVariable("id") Long id) {
		Article item = service.findById(id);
		if (item == null) {
			map.put("msg", "id为"+id+"的文章不存在");
			return "myerror";
		} else {
			List<Category> categories = catService.findAll();
			map.put("article", item);
			map.put("categories", categories);
			map.put("websites", null);
			return "item";
		}
	}

	@RequestMapping("/article/item/{id}.html")
	public String articleItem(ModelMap map, @PathVariable("id") Long id) {
		Article item = service.findById(id);
		List<Category> categories = catService.findAll();
		map.put("article", item);
		map.put("categories", categories);
		map.put("websites", null);
		return "item";
	}

	@GetMapping("/add/{categoryId}")
	public String add(ModelMap map, @PathVariable("categoryId") String categoryId) {
		List<Category> categories = catService.findAll();
		Article item = new Article();
		item.setId(0L);
		item.setCategoryId(categoryId);
		map.put("article", item);
		map.put("categories", categories);
		map.put("edit", true);
		return "edit";
	}

	@GetMapping("/edit/{id}.html")
	public String edit(ModelMap map, @PathVariable("id") Long id) {
		Article item = service.findById(id);
		List<Category> categories = catService.findAll();
		map.put("article", item);
		map.put("categories", categories);
		map.put("edit", true);
		return "edit";
	}

	@ResponseBody
	@GetMapping("/getContent/{id}")
	public String editTest(ModelMap map, @PathVariable("id") Long id, HttpServletResponse rsp) {
		rsp.setHeader("Content-Type", "application/json;charset=UTF-8");
		Article item = service.findById(id);
		return item.getContent();
	}

	/**
	 * CKEditor 文件上传的处理程序
	 *
	 * @author Lu Jinlong
	 *
	 * @param request
	 * @param response
	 * @param CKEditorFuncNum
	 * @throws IOException
	 */
	@RequestMapping(value = "/ckEditorUpload", method = RequestMethod.POST)
	public void uploadImage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("CKEditorFuncNum") String CKEditorFuncNum) throws IOException {
		try {
			MultipartFile file = null;
			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
			Map<String, MultipartFile> fileMap = mRequest.getFileMap();
			for (Map.Entry<String, MultipartFile> en : fileMap.entrySet()) {
				file = en.getValue();
				break;
			}
			if (file == null) {
				response.getWriter().write("error:no file uploaded");
				return;
			}

			String ext = FileUtil.getFileExt(file);
			if (!ext.equals(".jpg") && !ext.equals(".png")) {
				response.getWriter().write("error:filetypeerror");
				return;
			}

			Calendar cal = Calendar.getInstance();
			String folderPath = rootFolderPath + "/upload/images" + File.separator + cal.get(Calendar.YEAR)
					+ File.separator + cal.get(Calendar.MONTH + 1) + File.separator + cal.get(Calendar.DATE);

			String saveFilePath = FileUtil.saveFile(file, folderPath);
			String relativePath = saveFilePath.replace(rootFolderPath, "");
			response.getWriter().write("<script>window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ", \""
					+ relativePath + "\",\"\");</script>");
		} catch (Exception e) {
			response.getWriter().write("error:" + e.getMessage());
		}
	}

	@PostMapping(value = "/save")
	public void save(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("form") Article article) {
		HtmlExtract extract = new HtmlExtract(article.getContent());
		article.setText(extract.extractText());
		Object user = request.getSession().getAttribute("loginUser");
		if (user != null) {
			article.setAuthor(user.toString());
		}
		if (article.getId() <= 0) {
			service.add(article);
		} else {
			service.update(article);
		}
		String url = String.format("/edit/%s.html", article.getId());
		try {
			response.sendRedirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/delete/{id}")
	public void delete(HttpServletResponse response, @PathVariable("id") Long id) {
		Boolean res = service.deleteById(id);
		try {
			response.sendRedirect("/index.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 测试返回Json结果
	 *
	 * @author Lu Jinlong
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/articles")
	public List<Article> articles() {
		List<Article> list = service.findByPage(null, null, 0, 12);
		return list;
	}

}
