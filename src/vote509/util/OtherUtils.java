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
 * ������һЩ������
 * @author He Guo
 *
 */
public class OtherUtils {
	
	
	/**
	 * �ж��û��ĵ�¼״̬
	 * 
	 * @param session
	 * @return
	 */
	public static boolean getLoginStatus(String attr, HttpSession session) {
		return session.getAttribute(attr) != null;
	}
	
	/**
	 * frame�еĵ�������
	 * @throws IOException 
	 */
	public static void alertByframe(String msg, String location, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().append("<script>alert('" + msg + "'); top.location.href='" + location + "'</script>");
	}
	
	/**
	 * �������ܣ�����iframe��
	 * @throws IOException 
	 */
	public static void alert(String msg, String location, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().append("<script>alert('" + msg + "'); location.href='" + location + "'</script>");
	}
	
	/**
	 * ��ȡ�ļ���Ŀ
	 * 
	 * @param request
	 * @return
	 */
	public static List<FileItem> getFileItem(HttpServletRequest request) {
		FileItemFactory factory = new DiskFileItemFactory();
		// �ļ��ϴ�������
		ServletFileUpload upload = new ServletFileUpload(factory);
		// ��������
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
	 * �����û�������صı�����
	 * 
	 * @param request
	 */
	public static void dealWithForms(HttpServletRequest request, User u) {
		// ����
		for (FileItem item : getFileItem(request)) {
			if(item.isFormField()) {
				String fieldName = item.getFieldName(); // ��ñ��ֶ�
				// ʹ�� new String() ����ַ����������
                String value = new String(item.getString().getBytes(Charset.forName("ISO-8859-1")), Charset.forName("UTF-8"));
                request.setAttribute(fieldName, value);
			} else {
				String ext = item.getName(); 
				// �ļ��ĳ���Ϊ0��˵��û���ϴ��ļ�
				if(ext.length() == 0) 
					continue;
				ext = ext.split("\\.")[1]; // ����ϴ�ͼƬ�ĺ�׺��
				String header = "/user/" + u.getId() + "/image/header." + ext;
                request.setAttribute("header", header);
                String basePath = request.getSession().getServletContext().getRealPath(".");
                System.out.println(header); // ��ӡ��ǰλ��
                File file = new File(basePath, header);
                try {
                	// �ļ����ڣ�������ļ�
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
	 * ��ӣ����»������ش���
	 * 
	 * @param request
	 */
	public static void dealWithFormsActivity(HttpServletRequest request, User u) {
		// ����
		for (FileItem item : getFileItem(request)) {
			if(item.isFormField()) {
				String fieldName = item.getFieldName(); // ��ñ��ֶ�
				// ʹ�� new String() ����ַ����������
                String value = new String(item.getString().getBytes(Charset.forName("ISO-8859-1")), Charset.forName("UTF-8"));
                request.setAttribute(fieldName, value);
			} else {
				String ext = item.getName(); 
				// �ļ��ĳ���Ϊ0��˵��û���ϴ��ļ�
				if(ext.length() == 0) 
					continue;
				ext = ext.split("\\.")[1]; // ����ϴ�ͼƬ�ĺ�׺��
				String image = "/user/" + u.getId() + "/activity/" + System.currentTimeMillis() + "." + ext;
                request.setAttribute("image", image);
                String basePath = request.getSession().getServletContext().getRealPath(".");
                System.out.println(image); // ��ӡ��ǰλ��
                File file = new File(basePath, image);
                try {
                	// �ļ����ڣ�������ļ�
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
	 * ��ӣ�����ѡ�ֱ�����ش���
	 * 
	 * @param request
	 */
	public static void dealWithFormsContestant(HttpServletRequest request, User u) {
		// ����
		for (FileItem item : getFileItem(request)) {
			if(item.isFormField()) {
				String fieldName = item.getFieldName(); // ��ñ��ֶ�
				// ʹ�� new String() ����ַ����������
                String value = new String(item.getString().getBytes(Charset.forName("ISO-8859-1")), Charset.forName("UTF-8"));
                request.setAttribute(fieldName, value);
			} else {
				String ext = item.getName(); 
				// �ļ��ĳ���Ϊ0��˵��û���ϴ��ļ�
				if(ext.length() == 0) 
					continue;
				ext = ext.split("\\.")[1]; // ����ϴ�ͼƬ�ĺ�׺��
				String image = "/user/" + u.getId() + "/contestant/" + System.currentTimeMillis() + "." + ext;
                request.setAttribute("image", image);
                String basePath = request.getSession().getServletContext().getRealPath(".");
                System.out.println(image); // ��ӡ��ǰλ��
                File file = new File(basePath, image);
                try {
                	// �ļ����ڣ�������ļ�
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
	 * �ж��Ƿ�����ƥ��
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
	 * ����������������
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// System.out.println(getTime());
		System.out.println(regexArea("20-30"));
	}

}
