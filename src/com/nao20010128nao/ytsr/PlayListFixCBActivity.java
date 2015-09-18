package com.nao20010128nao.ytsr;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import android.content.*;
import com.nao20010128nao.ToolBox.*;
public class PlayListFixCBActivity extends Activity{
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank);

        Intent intent = getIntent();
		if (savedInstanceState == null && intent != null) {
            Log.d("TAG", "intent != null");

            if (intent.getAction().equals(Intent.ACTION_SEND)) {
                Log.d("TAG","intent.getAction().equals(Intent.ACTION_SEND)");
                String url = intent.getStringExtra(Intent.EXTRA_TEXT);
				if(url.indexOf("http://www.youtube.com/playlist?list=")==-1&&url.indexOf("https://www.youtube.com/playlist?list=")==-1){
					Toast.makeText(this,getResources().getString(R.string.err_3),100).show();
					finish();return;
				}
				String[] ax=url.split("\\=");
                url="http://www.youtube.com/playlist?list="+ax[ax.length-1];
				/*if(!Tools.CheckMatch(url)){
				 Toast.makeText(this,getResources().getString(R.string.err_3),100).show();
				 finish();
				 }*/
				Toast.makeText(this,url,100).show();
				ClipboardManager cm = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
				cm.setText(url);
            }else{
				Toast.makeText(this,getResources().getString(R.string.err_2),100).show();
			}
    	}else{
			Toast.makeText(this,getResources().getString(R.string.err_1),100).show();
		}
		finish();
	}
}
