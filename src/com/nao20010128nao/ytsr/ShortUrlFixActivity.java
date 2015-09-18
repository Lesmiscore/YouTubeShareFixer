package com.nao20010128nao.ytsr;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import java.util.regex.*;
import com.nao20010128nao.ToolBox.*;
public class ShortUrlFixActivity extends Activity {
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
				if(url.indexOf("http://youtu.be/")==-1&&url.indexOf("https://youtu.be/")==-1){
					Toast.makeText(this,getResources().getString(R.string.err_3),100).show();
					finish();return;
				}
				String url2=url;
				String[] ax=url.split("\\:");
                url="http:"+ax[ax.length-1];
				if(url.equals(url2)){
					Toast.makeText(this,getResources().getString(R.string.err_3),100).show();
					finish();return;
				}/*else if(!Tools.CheckMatch(url)){
					Toast.makeText(this,getResources().getString(R.string.err_3),100).show();
					finish();return;
				}*/
				Toast.makeText(this,url,1).show();
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				i.putExtra(Intent.EXTRA_TEXT,url);
				i.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION|i.getFlags());
				startActivity(Intent.createChooser(i, getResources().getString(R.string.chpn)));
			}else{
				Toast.makeText(this,getResources().getString(R.string.err_2),1).show();
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(intent.getStringExtra(Intent.EXTRA_TEXT)));
				i.putExtra(Intent.EXTRA_TEXT,intent.getStringExtra(Intent.EXTRA_TEXT));
				i.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION|i.getFlags());
				startActivity(Intent.createChooser(i, getResources().getString(R.string.chpn)));
			}
    	}else{
			Toast.makeText(this,getResources().getString(R.string.err_1),1).show();
		}
		finish();
    }
}
