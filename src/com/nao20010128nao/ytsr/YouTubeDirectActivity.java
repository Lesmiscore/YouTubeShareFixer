package com.nao20010128nao.ytsr;
import com.nao20010128nao.ToolBox.*;
import android.os.*;
import android.content.*;
import android.util.*;
import android.widget.*;
import android.net.*;
import java.util.*;
import android.content.pm.*;
import com.nao20010128nao.SpoofBrowser.classes.*;
import android.app.*;

public class YouTubeDirectActivity extends Activity
{
	Intent intent;
	int l=Toast.LENGTH_LONG;
	int s=Toast.LENGTH_SHORT;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.blank);
        Intent ix = getIntent();
		if (ix != null)intent = ix;
		if (savedInstanceState == null && intent != null)
		{
            Log.d("TAG", "intent != null");

            if (intent.getAction().equals(Intent.ACTION_SEND)){
                Log.d("TAG", "intent.getAction().equals(Intent.ACTION_SEND)");
                String url = intent.getStringExtra(Intent.EXTRA_TEXT);
				try{
					if (url.indexOf("http://youtu.be/") == -1 && url.indexOf("https://youtu.be/") == -1 &&
						url.indexOf("http://www.youtube.com/playlist?list=") == -1 && url.indexOf("https://www.youtube.com/playlist?list=") == -1){
						Toast.makeText(this, getResources().getString(R.string.err_3), 100).show();
						finish();return;
					}else if(url.indexOf("http://youtu.be/") != -1 || url.indexOf("https://youtu.be/") != -1){
						String[] ax=url.split("\\/");
						url = "http://www.youtube.com/watch?v=" + ax[ax.length - 1];
					}else if(url.indexOf("http://www.youtube.com/playlist?list=") != -1 || url.indexOf("https://www.youtube.com/playlist?list=") != -1){
						String[] ax=url.split("\\=");
						url = "http://www.youtube.com/playlist?list=" + ax[ax.length - 1];
					}
				}catch(Throwable ex){
					ex.printStackTrace();
					Toast.makeText(this, R.string.fail_send, l).show();
					return;
				}
				try{
					//if(Tools.AlwaysTrue())throw new Throwable();
					for(String i:getResources().getStringArray(R.array.classNames)){
						try{
							Log.d("CLASS_NAME_DETECTION",i);
							startActivity(new Intent(Intent.ACTION_VIEW)
									.setData(Uri.parse(url))
									.setClassName(getResources().getString(R.string.packageName),i));
							finish();
							return;
						}catch(Throwable ex){}
					}
				}catch (Throwable ex){
					ex.printStackTrace();
					for(ResolveInfo i:getSendableActivityList(getPackageManager(),null)){
						Log.d("CLASS_NAME_DETECTION",i.activityInfo.name);
						if(i.activityInfo.packageName==getResources().getString(R.string.packageName)&i.loadLabel(getPackageManager())=="YouTube"){
							try{
								startActivity(new Intent(Intent.ACTION_SEND)
											  .setData(Uri.parse(url))
											  .setClassName(getResources().getString(R.string.packageName),i.activityInfo.name));
								finish();
								return;
							}catch(Throwable ex_){
								ex_.printStackTrace();
							}
						}
					}
					/*最後の一手(ユーザーに選んでもらう)*/
					Toast.makeText(this, R.string.fail_send, l).show();
					startActivity(Intent.createChooser(new Intent(Intent.ACTION_VIEW)
							   			.setData(Uri.parse(url)),getResources().getString(R.string.fail_send_share)));
				}
            }else{
				Toast.makeText(this, getResources().getString(R.string.err_2), s).show();
			}
    	}else{
			Toast.makeText(this, getResources().getString(R.string.err_1), s).show();
		}
		finish();
	}
	public List<ResolveInfo> getSendableActivityList(PackageManager packageManager,Intent intent) {
		if(intent==null){
			intent = new Intent().setAction(Intent.ACTION_SEND);
		}
		List<ResolveInfo> resolveInfoList = packageManager.queryIntentActivities(intent, 0);
		Log.d("DEBUG","resolveInfoList:"+resolveInfoList.size());
		return resolveInfoList;
	}
}
