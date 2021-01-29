package vote509.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import vote509.entity.User;

/**
 * 其它的一些工具类
 * @author He Guo
 *
 */
public class OtherUtils {
	
	
	/**
	 * 判断用户的登录状态
	 * 
	 * @param session
	 * @return
	 */
	public static boolean getLoginStatus(String attr, HttpSession session) {
		return session.getAttribute(attr) != null;
	}
	
	/**
	 * frame中的弹窗功能
	 * @throws IOException 
	 */
	public static void alertByframe(String msg, String location, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().append("<script>alert('" + msg + "'); top.location.href='" + location + "'</script>");
	}
	
	/**
	 * 弹窗功能，不在iframe中
	 * @throws IOException 
	 */
	public static void alert(String msg, String location, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().append("<script>alert('" + msg + "'); location.href='" + location + "'</script>");
	}
	
	/**
	 * 获取文件项目
	 * 
	 * @param request
	 * @return
	 */
	public static List<FileItem> getFileItem(HttpServletRequest request) {
		FileItemFactory factory = new DiskFileItemFactory();
		// 文件上传处理器
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 解析请求
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return items;
	}
	
	/**
	 * 更新用户数据相关的表单处理
	 * 
	 * @param request
	 */
	public static void dealWithForms(HttpServletRequest request, User u) {
		// 遍历
		for (FileItem item : getFileItem(request)) {
			if(item.isFormField()) {
				String fieldName = item.getFieldName(); // 获得表单字段
				// 使用 new String() 解决字符乱码的问题
                String value = new String(item.getString().getBytes(Charset.forName("ISO-8859-1")), Charset.forName("UTF-8"));
                request.setAttribute(fieldName, value);
			} else {
				String ext = item.getName(); 
				// 文件的长度为0，说明没有上传文件
				if(ext.length() == 0) 
					continue;
				ext = ext.split("\\.")[1]; // 获得上传图片的后缀名
				String header = "/user/" + u.getId() + "/image/header." + ext;
                request.setAttribute("header", header);
                String basePath = request.getSession().getServletContext().getRealPath(".");
                System.out.println(header); // 打印当前位置
                File file = new File(basePath, header);
                try {
                	// 文件存在，清除该文件
                	if(file.exists()) file.delete();
                	item.write(file);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
			}
		}
	}
	
	/**
	 * 添加，更新活动表单的相关处理
	 * 
	 * @param request
	 */
	public static void dealWithFormsActivity(HttpServletRequest request, User u) {
		// 遍历
		for (FileItem item : getFileItem(request)) {
			if(item.isFormField()) {
				String fieldName = item.getFieldName(); // 获得表单字段
				// 使用 new String() 解决字符乱码的问题
                String value = new String(item.getString().getBytes(Charset.forName("ISO-8859-1")), Charset.forName("UTF-8"));
                request.setAttribute(fieldName, value);
			} else {
				String ext = item.getName(); 
				// 文件的长度为0，说明没有上传文件
				if(ext.length() == 0) 
					continue;
				ext = ext.split("\\.")[1]; // 获得上传图片的后缀名
				String image = "/user/" + u.getId() + "/activity/" + System.currentTimeMillis() + "." + ext;
                request.setAttribute("image", image);
                String basePath = request.getSession().getServletContext().getRealPath(".");
                System.out.println(image); // 打印当前位置
                File file = new File(basePath, image);
                try {
                	// 文件存在，清除该文件
                	if(file.exists()) file.delete();
                	item.write(file);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
			}
		}
	}
	
	/**
	 * 添加，更新选手表单的相关处理
	 * 
	 * @param request
	 */
	public static void dealWithFormsContestant(HttpServletRequest request, User u) {
		// 遍历
		for (FileItem item : getFileItem(request)) {
			if(item.isFormField()) {
				String fieldName = item.getFieldName(); // 获得表单字段
				// 使用 new String() 解决字符乱码的问题
                String value = new String(item.getString().getBytes(Charset.forName("ISO-8859-1")), Charset.forName("UTF-8"));
                request.setAttribute(fieldName, value);
			} else {
				String ext = item.getName(); 
				// 文件的长度为0，说明没有上传文件
				if(ext.length() == 0) 
					continue;
				ext = ext.split("\\.")[1]; // 获得上传图片的后缀名
				String image = "/user/" + u.getId() + "/contestant/" + System.currentTimeMillis() + "." + ext;
                request.setAttribute("image", image);
                String basePath = request.getSession().getServletContext().getRealPath(".");
                System.out.println(image); // 打印当前位置
                File file = new File(basePath, image);
                try {
                	// 文件存在，清除该文件
                	if(file.exists()) file.delete();
                	item.write(file);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
			}
		}
	}
	
	/**
	 * 判断是否正常匹配
	 * 
	 * @param area
	 * @return
	 */
	public static boolean regexArea(String area) {
		
		String pattern = "^[0-9]+-[0-9]+$";

		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(area);
		return m.matches();
			
	}

	/**
	 * 工具类主方法测试
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// System.out.println(getTime());
		System.out.println(regexArea("20-30"));
	}

}
