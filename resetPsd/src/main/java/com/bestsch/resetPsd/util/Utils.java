package com.bestsch.resetPsd.util;

import com.bestsch.utils.DException;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils
{
	public static boolean isNumeric(String str)
	{
		if (str == null) return false;
		
		Pattern pattern = Pattern.compile("^-?[0-9]+");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) { return false; }
		return true;
	} 
	
	public static String upperCaseFirstChar(String str)
	{
		char[] ch = str.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z')
			ch[0] = (char) (ch[0] - 32);
		return new String(ch);
	}
	
	public static String checkFileSeparator(String path)
	{
		if (path.lastIndexOf(File.separator) == path.length() - 1)
			return path;
		else return path + File.separator;
	}
	
	public static String checkUrlSeparator(String path)
	{
		if (path.lastIndexOf('/') == path.length() - 1)
			return path;
		else return path + '/';
	}
	
	public static List<Integer> idStrToList(String idStr)
	{
		try
		{
			List<Integer> ret = new ArrayList<>();
			String[] idStrs = idStr.split(",");
			for (String id: idStrs)
				ret.add(Integer.parseInt(id));
			return ret;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	public static List<String> strToList(String idStr)
	{
		try
		{
			List<String> ret = new ArrayList<>();
			String[] idStrs = idStr.split(",");
			for (String id: idStrs)
				ret.add(id);
			return ret;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	public static String listToIdStr(List<Integer> list)
	{
		String idStr = "";
		for (Integer id: list)
			idStr += id + ",";
		idStr = idStr.substring(0, idStr.length() - 1);
		return idStr;
	}
	
	public static long dateStrToLong(String str)
	{
		if (StringUtils.isEmpty(str))
			return 0;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try
		{
			long ret = sdf.parse(str).getTime() / 1000;
			return ret;
		}
		catch (ParseException e)
		{
//			throw new DException("错误的日期时间格式(yyyy-MM-dd)");
		}
		
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		try
		{
			long ret = sdf.parse(str).getTime() / 1000;
			return ret;
		}
		catch (ParseException e)
		{
//			throw new DException("错误的日期时间格式(yyyy-MM-dd)");
		}
		
		throw new DException("错误的日期时间格式(yyyy-MM-dd或yyyy-MM-dd HH:mm)");
	}
	
	public static Date str2Date(String dateStr)
	{
		if (StringUtils.isEmpty(dateStr))
			return null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try
		{
			return sdf.parse(dateStr);
		}
		catch (ParseException e)
		{
//			throw new DException("错误的日期时间格式(yyyy-MM-dd)");
		}
		
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		try
		{
			return sdf.parse(dateStr);
		}
		catch (ParseException e)
		{
//			throw new DException("错误的日期时间格式(yyyy-MM-dd)");
		}
		
		throw new DException("错误的日期时间格式(yyyy-MM-dd或yyyy-MM-dd HH:mm)");
	}
	
	public static Date tryStr2Date(String dateStr)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try
		{
			return sdf.parse(dateStr);
		}
		catch (ParseException e)
		{
		}
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try
		{
			return sdf.parse(dateStr);
		}
		catch (ParseException e)
		{
		}
		sdf = new SimpleDateFormat("yyyy/MM/dd");
		try
		{
			return sdf.parse(dateStr);
		}
		catch (ParseException e)
		{
		}
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		try
		{
			return sdf.parse(dateStr);
		}
		catch (ParseException e)
		{
		}
		
		return null;
	}
	
	public static boolean strIsNumeric(String str)
	{
		if (StringUtils.isEmpty(str)) return false;
		return StringUtils.isNumeric(str);
	}
	
	public static boolean strIsNotNumeric(String str)
	{
		if (StringUtils.isEmpty(str)) return true;
		return !StringUtils.isNumeric(str);
	}
	
	public static boolean isMobileNO(String mobiles)
	{
		if (mobiles == null) return false;

//		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
		Pattern p = Pattern.compile("^(1[0-9][0-9])\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}
	
	public static boolean isPhoneNumber(String phone)
	{
		if (phone == null) return false;
		
		Pattern p = Pattern.compile("((\\+[0-9]{2,4})?\\(?[0-9]+\\)?-?)?[0-9]{7,8}");
		Matcher m = p.matcher(phone);
		return m.matches();
	}
	
	public static boolean isIdCardNum(String idCardNum)
	{
		if (idCardNum == null) return false;
		
		Pattern idNumPattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");
		Matcher idNumMatcher = idNumPattern.matcher(idCardNum);
		return idNumMatcher.matches();
	}


	public static String createIdentidyCode()
	{
		Random ran = new Random();
//		return ran.nextInt(1000000)+"";
		return (int)((Math.random()*9+1)*100000)+"";
	}
	
	public static void main(String[] args) throws Exception
	{

		System.out.println();
	}
}
