package com.nao20010128nao.ytsr;
import java.util.regex.*;
import android.util.Log;
//import org.apache.commons.logging.*;
public class Tools
{
	public static Boolean CheckMatch(String tex){
		return Pattern
		.compile("^http://(www\\.youtube\\.com/watch\\?v=|youtu\\.be/)[0-9a-zA-Z\\-\\_]*")
		.matcher(tex)
		.matches();
	}
	public static Boolean CheckSafeText(String str,String[] regexps){
		for(int i=0;regexps.length<i;i++){
			if(str.split(regexps[i]).length>=2){
				return false;
			}
		}
		return true;
	}
	public static boolean AlwaysTrue(){
	Log.d("TrueSender","Called");
		return true;
	}
}
