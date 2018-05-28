package com.otoclash.pulgra;

import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.ClipboardManager;
import android.graphics.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.text.*;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Cekcek123Activity extends Activity {

	private LinearLayout linear1;
	private LinearLayout linear5;
	private ImageView imginter;
	private LinearLayout linear6;
	private LinearLayout linear4;
	private TextView textinter;
	private LinearLayout l_load;
	private Button update_ok;
	private Button update_ng;

	private HashMap<String, Object> map_cek = new HashMap<>();


	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private Timer _timer = new Timer();
	private Intent i_konten = new Intent();
	private TimerTask cek;
	private SharedPreferences databaseDanSetting;
	private DatabaseReference versiAPK = _firebase.getReference("versiAPK");


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cekcek123);
		initialize();
		initializeLogic();
	}

	private void  initialize() {
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		imginter = (ImageView) findViewById(R.id.imginter);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		textinter = (TextView) findViewById(R.id.textinter);
		l_load = (LinearLayout) findViewById(R.id.l_load);
		update_ok = (Button) findViewById(R.id.update_ok);
		update_ng = (Button) findViewById(R.id.update_ng);



		databaseDanSetting = getSharedPreferences("databaseDanSetting", Activity.MODE_PRIVATE);


		update_ok.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) { 
				i_konten.setClass(getApplicationContext(), KontenActivity.class);
				i_konten.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				startActivity(i_konten);
				finish();
			}
		});
		update_ng.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) { 
				final String appPackageName = getPackageName();
try {
startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
}
catch (android.content.ActivityNotFoundException anfe) {
startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
}
			}
		});
		versiAPK.addChildEventListener(new ChildEventListener() {
		@Override
		public void onChildAdded(DataSnapshot _dataSnapshot, String _previousValue) {
			GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
			HashMap<String, Object> _childValue = _dataSnapshot.getValue(_ind);
				map_cek = new Gson().fromJson(new Gson().toJson(_childValue), new TypeToken<HashMap<String, Object>>(){}.getType());
				databaseDanSetting.edit().putString("onlineCekVersi", map_cek.get("v").toString()).commit();
			}
			@Override
			public void onChildChanged(DataSnapshot _dataSnapshot, String _value) {
			GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
			HashMap<String, Object> _childValue = _dataSnapshot.getValue(_ind);
				map_cek = new Gson().fromJson(new Gson().toJson(_childValue), new TypeToken<HashMap<String, Object>>(){}.getType());
				databaseDanSetting.edit().putString("onlineCekVersi", map_cek.get("v").toString()).commit();
			}
			@Override
			public void onChildMoved(DataSnapshot _dataSnapshot, String _value) {
			}
			@Override
			public void onChildRemoved(DataSnapshot _dataSnapshot) {
			GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
			HashMap<String, Object> _childValue = _dataSnapshot.getValue(_ind);

			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {

			}
		});


	}

	private void  initializeLogic() {
		ProgressBar cpb = new ProgressBar(Cekcek123Activity.this);  l_load.addView(cpb);
		update_ok.setVisibility(View.GONE);
		update_ng.setVisibility(View.GONE);
		databaseDanSetting.edit().putString("versiSaat_ini", "1.0").commit();
		textinter.setText("Sedang memeriksa jaringan internet dan versi aplikasi....");
		cek = new TimerTask() {
					@Override
						public void run() {
							runOnUiThread(new Runnable() {
							@Override
								public void run() {
										android.net.ConnectivityManager cm = (android.net.ConnectivityManager) Cekcek123Activity.this.getSystemService(android.content.Context.CONNECTIVITY_SERVICE);

android.net.NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

if (activeNetwork != null) {
if (activeNetwork.getType() == android.net.ConnectivityManager.TYPE_WIFI) {
imginter.setImageResource(R.drawable.internet_wifi);
textinter.setText("OK");
} else if (activeNetwork.getType() == android.net.ConnectivityManager.TYPE_MOBILE) {
imginter.setImageResource(R.drawable.internet_ok);
textinter.setText("OK");
}
} else {
imginter.setImageResource(R.drawable.internet_ng);
textinter.setText("NG");
}
								if (databaseDanSetting.getString("onlineCekVersi", "").equals(databaseDanSetting.getString("versiSaat_ini", ""))) {
									update_ok.setVisibility(View.VISIBLE);
									cek = new TimerTask() {
												@Override
													public void run() {
														runOnUiThread(new Runnable() {
														@Override
															public void run() {
																								update_ok.setText("LANJUT");
															}
														});
													}
												};
												_timer.schedule(cek, (int)(1000));
								}
								else {
									update_ng.setVisibility(View.VISIBLE);
								}
								l_load.setVisibility(View.GONE);
								}
							});
						}
					};
					_timer.schedule(cek, (int)(3000));
	}

	@Override
	public void onBackPressed() {

	}





	// created automatically
	private void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}

	private int getLocationX(View _v) {
		 int _location[] = new int[2];
		 _v.getLocationInWindow(_location);
		 return _location[0];
	}

	private int getLocationY(View _v) {
		 int _location[] = new int[2];
		 _v.getLocationInWindow(_location);
		 return _location[1];
	}

	private int getRandom(int _minValue ,int _maxValue){
		Random random = new Random();
		return random.nextInt(_maxValue - _minValue + 1) + _minValue;
	}

	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
				_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}

	private float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}

	private int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}

	private int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}


}
