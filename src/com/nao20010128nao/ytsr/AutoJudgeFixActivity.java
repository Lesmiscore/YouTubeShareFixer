package com.nao20010128nao.ytsr;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import android.view.*;
import com.nao20010128nao.ToolBox.*;
public class AutoJudgeFixActivity extends Activity implements View.OnClickListener{
	Activity Me=this;String url;
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.select);
        Intent intent = getIntent();
		
		if (savedInstanceState == null && intent != null) {
            Log.d("TAG", "intent != null");

            if (intent.getAction().equals(Intent.ACTION_SEND)) {
                Log.d("TAG","intent.getAction().equals(Intent.ACTION_SEND)");
                url = intent.getStringExtra(Intent.EXTRA_TEXT);
				if(url.indexOf("http://youtu.be/")!=-1||url.indexOf("https://youtu.be/")!=-1){
					((Button)findViewById(R.id.fullurl)).setOnClickListener(new View.OnClickListener(){
							@Override
							public void onClick(View p1){
								String[] ax=url.split("\\/");
								url="http://www.youtube.com/watch?v="+ax[ax.length-1];
								Toast.makeText(Me,url,100).show();
								send(url);
							}
						});
					((Button)findViewById(R.id.shorturl)).setOnClickListener(new View.OnClickListener(){
							@Override
							public void onClick(View p1){
								String[] ax=url.split("\\:");
								url="http:"+ax[ax.length-1];
								Toast.makeText(Me,url,100).show();
								send(url);
							}
						});
					return;
				}else if(url.indexOf("http://www.youtube.com/playlist?list=")!=-1||url.indexOf("https://www.youtube.com/playlist?list=")!=-1){
					String[] ax=url.split("\\=");
					url="http://www.youtube.com/playlist?list="+ax[ax.length-1];
					Toast.makeText(this,url,100).show();
					send(url);
				}else{
					Toast.makeText(this,getResources().getString(R.string.err_3),100).show();
					finish();return;
				}
			}else{
				Toast.makeText(this,getResources().getString(R.string.err_2),100).show();
			}
    	}else{
			Toast.makeText(this,getResources().getString(R.string.err_1),100).show();
		}
		finish();
    }
	@Override
	public void onClick(View p1){
		Toast.makeText(this,((Button)p1).getText(),100).show();
	}
	void send(String url){
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(Intent.createChooser(i, getResources().getString(R.string.chpn)));
		finish();
	}
}
