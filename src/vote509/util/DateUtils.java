package vote509.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	/**
	 * ��ȡ��ǰϵͳʱ�䣬�Ѿ����ø�ʽ�������
	 * @return
	 */
	public static String getTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		return sdf.format(date.getTime());
	}
	
	/**
	 * ��ȡ��ǰϵͳʱ�䣬�Ѿ����ø�ʽ�������
	 * @return
	 */
	public static String getTime(String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = new Date();
		return sdf.format(date.getTime());
	}
	
	/**
	 * ��ȡ��ǰϵͳʱ�䣬�Ѿ����ø�ʽ�������
	 * @return
	 */
	public static String getTime(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(date.getTime());
	}
	
	/**
	 * ��ȡ��ǰϵͳʱ�䣬�Ѿ����ø�ʽ�������
	 * @return
	 * @throws ParseException 
	 */
	public static Date setTimeToDate(String date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.parse(date);
	}
	
	public static void main(String[] args) {
		String time = getTime("HH");
		System.out.println(time.equals("00"));
	}
}
