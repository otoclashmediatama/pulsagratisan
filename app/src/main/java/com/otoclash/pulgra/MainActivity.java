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


public class MainActivity extends Activity {

	private LinearLayout linear1;
	private LinearLayout linear8;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear7;
	private LinearLayout linear11;
	private LinearLayout halamanakun;
	private ImageView imageintro;
	private TextView intro;
	private LinearLayout linear15;
	private LinearLayout linear16;
	private LinearLayout linear17;
	private Button tombol_masuk;
	private EditText nama;
	private EditText nope;
	private CheckBox checkbox1;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private LinearLayout linear12;
	private LinearLayout linear13;
	private ImageView imageview1;
	private ImageView imageview2;
	private ImageView imageview3;
	private ImageView imageview4;
	private ImageView imageview5;
	private LinearLayout linear9;
	private LinearLayout linear10;
	private TextView next;

	private double n = 0;
	private HashMap<String, Object> firebasedata = new HashMap<>();


	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private Vibrator gtr;
	private DatabaseReference databaseAkun = _firebase.getReference("databaseAkun");
	private SharedPreferences databaseDanSetting;
	private Intent i_cek123 = new Intent();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initialize();
		initializeLogic();
	}

	private void  initialize() {
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear8 = (LinearLayout) findViewById(R.id.linear8);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		linear11 = (LinearLayout) findViewById(R.id.linear11);
		halamanakun = (LinearLayout) findViewById(R.id.halamanakun);
		imageintro = (ImageView) findViewById(R.id.imageintro);
		intro = (TextView) findViewById(R.id.intro);
		linear15 = (LinearLayout) findViewById(R.id.linear15);
		linear16 = (LinearLayout) findViewById(R.id.linear16);
		linear17 = (LinearLayout) findViewById(R.id.linear17);
		tombol_masuk = (Button) findViewById(R.id.tombol_masuk);
		nama = (EditText) findViewById(R.id.nama);
		nope = (EditText) findViewById(R.id.nope);
		checkbox1 = (CheckBox) findViewById(R.id.checkbox1);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		linear12 = (LinearLayout) findViewById(R.id.linear12);
		linear13 = (LinearLayout) findViewById(R.id.linear13);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		imageview3 = (ImageView) findViewById(R.id.imageview3);
		imageview4 = (ImageView) findViewById(R.id.imageview4);
		imageview5 = (ImageView) findViewById(R.id.imageview5);
		linear9 = (LinearLayout) findViewById(R.id.linear9);
		linear10 = (LinearLayout) findViewById(R.id.linear10);
		next = (TextView) findViewById(R.id.next);

		gtr = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

		databaseDanSetting = getSharedPreferences("databaseDanSetting", Activity.MODE_PRIVATE);


		next.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) { 
				n++;
				if (n == 1) {
					imageintro.setImageResource(R.drawable.masukimage);
					intro.setText("Setiap harinya jangan lupa absen masuk, biar kamu dapat bonus coin");
					imageview1.setImageResource(R.drawable.bundarputih);
					imageview2.setImageResource(R.drawable.bundarhitam);
				}
				else {
					if (n == 2) {
						imageintro.setImageResource(R.drawable.bagikan);
						intro.setText("Jangan lupa bagikan ke temen, biar kamu tambah banyak coin");
						imageview1.setImageResource(R.drawable.bundarputih);
						imageview2.setImageResource(R.drawable.bundarputih);
						imageview3.setImageResource(R.drawable.bundarhitam);
					}
					else {
						if (n == 3) {
							imageintro.setImageResource(R.drawable.rateapp);
							intro.setText("Kalau kamu suka, kasih bintang aku biar kamu aku kasih tambah coin");
							imageview1.setImageResource(R.drawable.bundarputih);
							imageview2.setImageResource(R.drawable.bundarputih);
							imageview3.setImageResource(R.drawable.bundarputih);
							imageview4.setImageResource(R.drawable.bundarhitam);
						}
						else {
							if (n == 4) {
								imageintro.setImageResource(R.drawable.waktuituuang);
								intro.setText("Semakin betah kamu pantengin aku, coin semakin cepat bertambah");
								imageview1.setImageResource(R.drawable.bundarputih);
								imageview2.setImageResource(R.drawable.bundarputih);
								imageview3.setImageResource(R.drawable.bundarputih);
								imageview4.setImageResource(R.drawable.bundarputih);
								imageview5.setImageResource(R.drawable.bundarhitam);
							}
							else {
								if (n == 5) {
									halamanakun.setVisibility(View.VISIBLE);
									linear11.setVisibility(View.GONE);
									next.setVisibility(View.INVISIBLE);
								}
							}
						}
					}
				}
			}
		});
		tombol_masuk.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) { 
				if (nama.getText().toString().equals("") || (nope.getText().toString().equals("") || databaseDanSetting.getString("persetujuan", "").equals(""))) {
					showMessage("Semua bagian wajib di isi");
				}
				else {
					databaseDanSetting.edit().putString("sudahmasuk", "1").commit();
					databaseDanSetting.edit().putString("nama", nama.getText().toString()).commit();
					databaseDanSetting.edit().putString("nope", nope.getText().toString()).commit();
					databaseDanSetting.edit().putString("poin", "15").commit();
					firebasedata = new HashMap<>();
					firebasedata.put("nama", nama.getText().toString());
					firebasedata.put("nope", nope.getText().toString());
					firebasedata.put("poin", "15");
					databaseAkun.push().updateChildren(firebasedata);
					i_cek123.setClass(getApplicationContext(), Cekcek123Activity.class);
					i_cek123.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
					startActivity(i_cek123);
					finish();
				}
			}
		});
		checkbox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _buttonView, final boolean _isChecked)  { 
				if (_isChecked) {
					databaseDanSetting.edit().putString("persetujuan", "1").commit();
				}
				else {
					databaseDanSetting.edit().putString("persetujuan", "0").commit();
				}
			}
		});

	}

	private void  initializeLogic() {
		if (databaseDanSetting.getString("sudahmasuk", "").equals("1")) {
			i_cek123.setClass(getApplicationContext(), Cekcek123Activity.class);
			i_cek123.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
			startActivity(i_cek123);
			finish();
		}
		else {
			n = 0;
			imageintro.setImageResource(R.drawable.coinkepulsa);
			intro.setText("Kumpulin coin, nanti kalau sudah mencukupi tukar sama pulsa");
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { linear2.setElevation(25f);}
			android.graphics.drawable.GradientDrawable linearLy = new android.graphics.drawable.GradientDrawable();  linearLy.setColor(Color.WHITE);  linearLy.setCornerRadius(25);  linear2.setBackground(linearLy);
		}
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
