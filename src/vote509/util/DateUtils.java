package vote509.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	/**
	 * 获取当前系统时间，已经做好格式化处理的
	 * @return
	 */
	public static String getTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		return sdf.format(date.getTime());
	}
	
	/**
	 * 获取当前系统时间，已经做好格式化处理的
	 * @return
	 */
	public static String getTime(String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = new Date();
		return sdf.format(date.getTime());
	}
	
	/**
	 * 获取当前系统时间，已经做好格式化处理的
	 * @return
	 */
	public static String getTime(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(date.getTime());
	}
	
	/**
	 * 获取当前系统时间，已经做好格式化处理的
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
