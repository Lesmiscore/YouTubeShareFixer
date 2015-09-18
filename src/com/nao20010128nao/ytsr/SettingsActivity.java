package com.nao20010128nao.ytsr;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import android.content.pm.*;
import android.os.Bundle;
import android.view.*;
import android.content.*;
import android.content.res.*;
import javax.crypto.*;
import com.nao20010128nao.ToolBox.*;
public class SettingsActivity extends Activity{
	PackageManager pm;
	CheckBox[] mCB;
	ComponentName[] cn;
	int[] val;
	int l=Toast.LENGTH_LONG,max;
	Button mOK,mCancel,mFillAll,mUnFill,mDef,mApply,mRest,mDisCB;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
		Log.d("TEST","MAYBEOK");
		pm=getPackageManager();
		getChbx();getBtn();
		val=new int[]{R.string.fix_full,
		           R.string.fix_short,
				   R.string.fix_pla,
				   R.string.fix_vali,
				   R.string.fix_full_cb,
				   R.string.fix_short_cb,
				   R.string.fix_pla_cb,
				   R.string.fix_aj,
				   R.string.yt_direct};
		max=val.length-1;
		for(int i=0;i<=max;i++)mCB[i].setText(x(R.string.actv).replace("%c",x(val[i])));
		getCn();
		//if(Tools.AlwaysTrue()){return;}
		mOK.setOnClickListener(new View.OnClickListener() {
				@Override
			public void onClick(View v) {
				for(int i=0;i<=max;i++){
					if(mCB[i].isChecked()){
						pm.setComponentEnabledSetting(cn[i],
													  PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
													  PackageManager.DONT_KILL_APP);
					}else {
						pm.setComponentEnabledSetting(cn[i], 
													  PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
													  PackageManager.DONT_KILL_APP);
					}
				}
				t(R.string.saved);
				finish();
			}
		});
		mCancel.setOnClickListener(new View.OnClickListener() {
				@Override
			public void onClick(View v) {
					finish();
				}
			});
		mDef.setOnClickListener(new View.OnClickListener() {
				@Override
			public void onClick(View v) {
				for(int i=0;i<=max;i++){
					TypedArray data= getResources().obtainTypedArray(R.array.defaults);
				    mCB[i].setChecked(data.getBoolean(i,false));}
				}
			});
		mFillAll.setOnClickListener(new View.OnClickListener() {
				@Override
			public void onClick(View v) {
				for(int i=0;i<=max;i++)mCB[i].setChecked(true);
			}
		});
		mUnFill.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					for(int i=0;i<=max;i++)mCB[i].setChecked(false);
				}
			});
		mRest.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					for(int i=0;i<=max;i++)mCB[i].setChecked(pm.getComponentEnabledSetting(cn[i])!=1);
				}
			});
		mApply.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					for(int i=0;i<=max;i++){
						if(mCB[i].isChecked()){
							pm.setComponentEnabledSetting(cn[i],
														  PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
														  PackageManager.DONT_KILL_APP);
						}else {
							pm.setComponentEnabledSetting(cn[i], 
														  PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
														  PackageManager.DONT_KILL_APP);
						}
					}
					t(R.string.applied);
				}
			});
		mDisCB.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				for(int i=4;i<=6;i++)mCB[i].setChecked(true);
			}
		});
		for(int i=0;i<=max;i++)mCB[i].setChecked(pm.getComponentEnabledSetting(cn[i])!=1);
		
	}
	String x(int id){
		return getResources().getString(id);
	}
	void getChbx(){
		mCB=new CheckBox[9];
		mCB[0]=(CheckBox)findViewById(R.id.settingsCheckBox1);
		mCB[1]=(CheckBox)findViewById(R.id.settingsCheckBox2);
		mCB[2]=(CheckBox)findViewById(R.id.settingsCheckBox3);
		mCB[3]=(CheckBox)findViewById(R.id.settingsCheckBox4);
		mCB[4]=(CheckBox)findViewById(R.id.settingsCheckBox5);
		mCB[5]=(CheckBox)findViewById(R.id.settingsCheckBox6);
		mCB[6]=(CheckBox)findViewById(R.id.settingsCheckBox7);
		mCB[7]=(CheckBox)findViewById(R.id.settingsCheckBox8);
		mCB[8]=(CheckBox)findViewById(R.id.settingsCheckBox9);
	}
	void getCn(){
		cn=new ComponentName[9];
		cn[0]=new ComponentName(this,FullUrlFixActivity.class);
		cn[1]=new ComponentName(this,ShortUrlFixActivity.class);
		cn[2]=new ComponentName(this,PlayListFixActivity.class);
		cn[3]=new ComponentName(this,ValidateTextActivity.class);
	    cn[4]=new ComponentName(this,FullUrlFixCBActivity.class);
		cn[5]=new ComponentName(this,ShortUrlFixCBActivity.class);
		cn[6]=new ComponentName(this,PlayListFixCBActivity.class);
		cn[7]=new ComponentName(this,AutoJudgeFixActivity.class);
		cn[8]=new ComponentName(this,YouTubeDirectActivity.class);
	}
	void getBtn(){
		mOK=(Button)findViewById(R.id.sok);
		mCancel=(Button)findViewById(R.id.cncl);
		mDef=(Button)findViewById(R.id.def);
		mFillAll=(Button)findViewById(R.id.ca);
		mUnFill=(Button)findViewById(R.id.uca);
		mApply=(Button)findViewById(R.id.applybtn);
		mRest=(Button)findViewById(R.id.rest);
		mDisCB=(Button)findViewById(R.id.discb);
	}
	void t(int id){
		Toast.makeText(this,id,l).show();
	}
	void t(String id){
		Toast.makeText(this,id,l).show();
	}
}
