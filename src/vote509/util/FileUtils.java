package vote509.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * �ļ���صĹ�����
 * @author He Guo
 *
 */
public class FileUtils {

	/**
     * ��ȡ�����ļ�
     *
     * @param file �ַ����ļ�
     * @return Properties ����
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
	 * �����û��ļ���
	 * @param path
	 * @return
	 */
	public static boolean createDir(String path) {
		File file = new File(path);
		System.out.println(file);
		return file.mkdir();
	}
	
	/**
	 * д���ļ�
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
	 * �û���������Ե�������
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties properties = readProperties("db.properties");
		System.out.println(properties.getProperty("jdbc.url"));
	}

}
