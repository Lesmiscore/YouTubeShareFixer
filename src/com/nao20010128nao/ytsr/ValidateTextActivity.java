package com.nao20010128nao.ytsr;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import com.nao20010128nao.ToolBox.*;
public class ValidateTextActivity extends Activity {
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank);

        Intent intent = getIntent();
		if (savedInstanceState == null && intent != null) {
            if (intent.getAction().equals(Intent.ACTION_SEND)) {
                try{
				String url = intent.getStringExtra(Intent.EXTRA_TEXT);
					if(url.split("http://youtu\\.be/").length<2&&
					   url.split("https://youtu\\.be/").length<2&&
					   url.split("http://www\\.youtube\\.com/playlist\\?list=").length<2&&
					   url.split("https://www\\.youtube\\.com/playlist\\?list=").length<2){
					finala(x(R.string.mes_1)+":\n"+url);
				}else{
					finala(x(R.string.mes_2)+":\n"+url);
				}
				}catch(Throwable e){
					finala(R.string.mes_3);
				}
			}else{
				finala(R.string.err_2);
			}
    	}else{
			finala(R.string.err_1);
		}
		finish();
    }
	void finala(int id){
		Toast.makeText(this,getResources().getString(id),100).show();
	    finish();
	}
	void finala(String id){
		Toast.makeText(this,id,100).show();
		finish();
	}
	String x(int id){
		return getResources().getString(id);
	}
}
