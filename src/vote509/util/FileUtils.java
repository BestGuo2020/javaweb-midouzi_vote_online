package vote509.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 文件相关的工具类
 * @author He Guo
 *
 */
public class FileUtils {

	/**
     * 读取配置文件
     *
     * @param file 字符串文件
     * @return Properties 对象
     */
    public static Properties readProperties(String file) {
        Properties properties = new Properties();
        InputStream in = FileUtils.class.getClassLoader().getResourceAsStream(file);
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
    
    /**
	 * 创建用户文件夹
	 * @param path
	 * @return
	 */
	public static boolean createDir(String path) {
		File file = new File(path);
		System.out.println(file);
		return file.mkdir();
	}
	
	/**
	 * 写入文件
	 * @param is
	 * @param path
	 */
	public static void write(InputStream is, String path) {
		// TODO Auto-generated method stub
		try {
			FileOutputStream fos = new FileOutputStream(path);
			byte[] b = new byte[1000];
			int len = 0;
			while((len = is.read(b)) != -1) {
				fos.write(b, 0, len);
			}
			fos.close();
			is.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 用户工具类测试的主方法
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties properties = readProperties("db.properties");
		System.out.println(properties.getProperty("jdbc.url"));
	}

}
