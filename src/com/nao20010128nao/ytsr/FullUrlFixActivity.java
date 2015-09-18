package com.nao20010128nao.ytsr;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import com.nao20010128nao.ToolBox.*;
public class FullUrlFixActivity extends Activity
{
    /** Called when the activity is first created. */
	Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank);
        Intent ix = getIntent();
		if (ix != null)intent = ix;
		if (savedInstanceState == null && intent != null){
            Log.d("TAG", "intent != null");

            if (intent.getAction().equals(Intent.ACTION_SEND)){
                Log.d("TAG", "intent.getAction().equals(Intent.ACTION_SEND)");
                String url = intent.getStringExtra(Intent.EXTRA_TEXT);
				if (url.indexOf("http://youtu.be/") == -1 && url.indexOf("https://youtu.be/") == -1)
				{
					Toast.makeText(this, getResources().getString(R.string.err_3), 100).show();
					finish();return;
				}
				String[] ax=url.split("\\/");
                url = "http://www.youtube.com/watch?v=" + ax[ax.length - 1];
				/*if(!Tools.CheckMatch(url)){
				 Toast.makeText(this,getResources().getString(R.string.err_3),100).show();
				 finish();
				 }*/
				Toast.makeText(this, url, 1).show();
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				i.putExtra(Intent.EXTRA_TEXT,url);
				i.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION|i.getFlags());
				startActivity(Intent.createChooser(i, getResources().getString(R.string.chpn)));
            }else{
				Toast.makeText(this, getResources().getString(R.string.err_2), 1).show();
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(intent.getStringExtra(Intent.EXTRA_TEXT)));
				i.putExtra(Intent.EXTRA_TEXT,intent.getStringExtra(Intent.EXTRA_TEXT));
				i.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION|i.getFlags());
				startActivity(Intent.createChooser(i, getResources().getString(R.string.chpn)));
			}
    	}else{
			Toast.makeText(this, getResources().getString(R.string.err_1), 100).show();
		}
		finish();
    }
}
