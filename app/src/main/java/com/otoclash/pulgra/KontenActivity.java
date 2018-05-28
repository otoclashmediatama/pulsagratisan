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

public class KontenActivity extends Activity {

	private LinearLayout linear1;
	private LinearLayout actionbar;
	private LinearLayout menu_dan_baca2;
	private LinearLayout halaman_claimpoin;
	private LinearLayout linear_pesan_sistem;
	private LinearLayout footer;
	private ImageView imageview1;
	private TextView poin;
	private LinearLayout linear5;
	private LinearLayout linear14;
	private ListView list_menu;
	private Spinner spinner_menu;
	private TextView nope;
	private TextView tanggal;
	private LinearLayout linear8;
	private LinearLayout linear9;
	private LinearLayout linear10;
	private LinearLayout linear11;
	private LinearLayout linear12;
	private ImageView icon_tombol_absen;
	private TextView text_absenmasuk;
	private ImageView icon_berbagi;
	private TextView text_berbagi;
	private ImageView icon_rating;
	private TextView textkasihnilai;
	private ImageView icon_waktu_on;
	private TextView textview6;
	private TextView timer;
	private LinearLayout claim_poin;
	private LinearLayout linear20;
	private ListView viewoperator;
	private LinearLayout linear21;
	private Spinner operator;
	private Button sepuluhribu;
	private Button limapuluhribu;
	private Button seratusribu;
	private ImageView imagepesansistem;
	private LinearLayout linear4;
	private HorizontalScrollView hscroll1;
	private LinearLayout linear15;
	private TextView isi_pesan_sistem;

	private double jam = 0;
	private double menit = 0;
	private double detik = 0;
	private double cek = 0;
	private double timeElapsed = 0;
	private HashMap<String, Object> firebasedata = new HashMap<>();
	private HashMap<String, Object> informasi = new HashMap<>();
	private HashMap<String, Object> cekRating = new HashMap<>();
	private String namaOperator = "";
	private HashMap<String, Object> AkunClaimPoin = new HashMap<>();
	private double tambahPoinDariAbsen = 0;
	private double tambahPoinDariRating = 0;
	private double nilaiDariBerbagi = 0;
	private double seribu = 0;
	private double limaribu = 0;
	private double spuluhribu = 0;
	private String namaMenu = "";

	private ArrayList<String> firebasedatabase = new ArrayList<String>();
	private ArrayList<HashMap<String, Object>> listOperator = new ArrayList<>();
	private ArrayList<String> NominalPulsa = new ArrayList<String>();
	private ArrayList<HashMap<String, Object>> listMenu = new ArrayList<>();

	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private Timer _timer = new Timer();
	private Calendar calender_on = Calendar.getInstance();
	private TimerTask tmr_on;
	private AlertDialog.Builder kasih_nilai_d;
	private DatabaseReference databaseAkun = _firebase.getReference("databaseAkun");
	private SharedPreferences databaseDanSetting;
	private DatabaseReference pesanSistem = _firebase.getReference("pesanSistem");
	private TimerTask tmr_adshow;
	private DatabaseReference cekKasihRating = _firebase.getReference("cekKasihRating");
	private DatabaseReference DataAkunClaimPoin = _firebase.getReference("DataAkunClaimPoin");
	private TimerTask runTimeCheck;
	private TimerTask cekUpdateNominal;
	private TimerTask DelayTambahPoinAbsen;
	private AlertDialog.Builder keluar_d;
	private Intent waAdmin = new Intent();
	private Intent mailAdmin = new Intent();
	private AlertDialog.Builder tentangKami;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.konten);
		initialize();
		initializeLogic();
	}

	private void  initialize() {
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		actionbar = (LinearLayout) findViewById(R.id.actionbar);
		menu_dan_baca2 = (LinearLayout) findViewById(R.id.menu_dan_baca2);
		halaman_claimpoin = (LinearLayout) findViewById(R.id.halaman_claimpoin);
		linear_pesan_sistem = (LinearLayout) findViewById(R.id.linear_pesan_sistem);
		footer = (LinearLayout) findViewById(R.id.footer);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		poin = (TextView) findViewById(R.id.poin);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		linear14 = (LinearLayout) findViewById(R.id.linear14);
		list_menu = (ListView) findViewById(R.id.list_menu);
		spinner_menu = (Spinner) findViewById(R.id.spinner_menu);
		nope = (TextView) findViewById(R.id.nope);
		tanggal = (TextView) findViewById(R.id.tanggal);
		linear8 = (LinearLayout) findViewById(R.id.linear8);
		linear9 = (LinearLayout) findViewById(R.id.linear9);
		linear10 = (LinearLayout) findViewById(R.id.linear10);
		linear11 = (LinearLayout) findViewById(R.id.linear11);
		linear12 = (LinearLayout) findViewById(R.id.linear12);
		icon_tombol_absen = (ImageView) findViewById(R.id.icon_tombol_absen);
		text_absenmasuk = (TextView) findViewById(R.id.text_absenmasuk);
		icon_berbagi = (ImageView) findViewById(R.id.icon_berbagi);
		text_berbagi = (TextView) findViewById(R.id.text_berbagi);
		icon_rating = (ImageView) findViewById(R.id.icon_rating);
		textkasihnilai = (TextView) findViewById(R.id.textkasihnilai);
		icon_waktu_on = (ImageView) findViewById(R.id.icon_waktu_on);
		textview6 = (TextView) findViewById(R.id.textview6);
		timer = (TextView) findViewById(R.id.timer);
		claim_poin = (LinearLayout) findViewById(R.id.claim_poin);
		linear20 = (LinearLayout) findViewById(R.id.linear20);
		viewoperator = (ListView) findViewById(R.id.viewoperator);
		linear21 = (LinearLayout) findViewById(R.id.linear21);
		operator = (Spinner) findViewById(R.id.operator);
		sepuluhribu = (Button) findViewById(R.id.sepuluhribu);
		limapuluhribu = (Button) findViewById(R.id.limapuluhribu);
		seratusribu = (Button) findViewById(R.id.seratusribu);
		imagepesansistem = (ImageView) findViewById(R.id.imagepesansistem);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		hscroll1 = (HorizontalScrollView) findViewById(R.id.hscroll1);
		linear15 = (LinearLayout) findViewById(R.id.linear15);
		isi_pesan_sistem = (TextView) findViewById(R.id.isi_pesan_sistem);



		kasih_nilai_d = new AlertDialog.Builder(this);

		databaseDanSetting = getSharedPreferences("databaseDanSetting", Activity.MODE_PRIVATE);







		keluar_d = new AlertDialog.Builder(this);


		tentangKami = new AlertDialog.Builder(this);

		textkasihnilai.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) { 
				if (databaseDanSetting.getString("cekPernahRating", "").equals("1")) {
					showMessage("Kamu sudah pernah kasih rating!");
				}
				else {
					kasih_nilai_d.setTitle("Suka aku?");
					kasih_nilai_d.setCancelable(false);
					kasih_nilai_d.setView(R.layout.dialog_rating_c);
					kasih_nilai_d.setPositiveButton("IYA", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
													cekRating = new HashMap<>();
								cekRating.put("nope", nope.getText().toString());
								cekRating.put("kasihNilai", "1");
								cekKasihRating.push().updateChildren(cekRating);
								final String appPackageName = getPackageName();
try {
startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
}
catch (android.content.ActivityNotFoundException anfe) {
startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
}
								linear11.setAlpha((float)(0.5d));
								tambahPoinDariRating = Double.parseDouble(databaseDanSetting.getString("poin", "")) + 100;
								databaseDanSetting.edit().putString("poin", String.valueOf((long)(tambahPoinDariRating))).commit();
						}
					});
					kasih_nilai_d.setNegativeButton("ENGGAK", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
													showMessage("Kamu melewatkan tambahan 100 poin!");
						}
					});
					kasih_nilai_d.create().show();
				}
			}
		});
		icon_rating.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) { 
				if (databaseDanSetting.getString("cekPernahRating", "").equals("1")) {
					showMessage("Kamu sudah pernah kasih rating!");
				}
				else {
					kasih_nilai_d.setTitle("Suka aku?");
					kasih_nilai_d.setCancelable(false);
					kasih_nilai_d.setView(R.layout.dialog_rating_c);
					kasih_nilai_d.setPositiveButton("IYA", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
													cekRating = new HashMap<>();
								cekRating.put("nope", nope.getText().toString());
								cekRating.put("kasihNilai", "1");
								cekKasihRating.push().updateChildren(cekRating);
								final String appPackageName = getPackageName();
try {
startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
}
catch (android.content.ActivityNotFoundException anfe) {
startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
}
								tambahPoinDariRating = Double.parseDouble(databaseDanSetting.getString("poin", "")) + 100;
								databaseDanSetting.edit().putString("poin", String.valueOf((long)(tambahPoinDariRating))).commit();
						}
					});
					kasih_nilai_d.setNegativeButton("ENGGAK", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
													showMessage("Kamu melewatkan tambahan 100 poin!");
						}
					});
					kasih_nilai_d.create().show();
				}
			}
		});
		databaseAkun.addChildEventListener(new ChildEventListener() {
		@Override
		public void onChildAdded(DataSnapshot _dataSnapshot, String _previousValue) {
			GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
			HashMap<String, Object> _childValue = _dataSnapshot.getValue(_ind);
				firebasedata = new Gson().fromJson(new Gson().toJson(_childValue), new TypeToken<HashMap<String, Object>>(){}.getType());
				if (firebasedata.get("nope").toString().equals(databaseDanSetting.getString("nope", ""))) {
					databaseDanSetting.edit().putString("poin", firebasedata.get("poin").toString()).commit();
				}
			}
			@Override
			public void onChildChanged(DataSnapshot _dataSnapshot, String _value) {
			GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
			HashMap<String, Object> _childValue = _dataSnapshot.getValue(_ind);
				firebasedata = new Gson().fromJson(new Gson().toJson(_childValue), new TypeToken<HashMap<String, Object>>(){}.getType());
				if (firebasedata.get("nope").toString().equals(databaseDanSetting.getString("nope", ""))) {
					databaseDanSetting.edit().putString("poin", firebasedata.get("poin").toString()).commit();
				}
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

		icon_tombol_absen.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) { 
				if (databaseDanSetting.getString("terakhirAbsen", "").equals(tanggal.getText().toString())) {
					showMessage("Hari ini kamu sudah absen");
				}
				else {
					linear9.setAlpha((float)(0.5d));
					databaseDanSetting.edit().putString("terakhirAbsen", tanggal.getText().toString()).commit();
					showMessage("Absen berhasil");
					DelayTambahPoinAbsen = new TimerTask() {
								@Override
									public void run() {
										runOnUiThread(new Runnable() {
										@Override
											public void run() {
																tambahPoinDariAbsen = Double.parseDouble(databaseDanSetting.getString("poin", "")) + 15;
											databaseDanSetting.edit().putString("poin", String.valueOf((long)(tambahPoinDariAbsen))).commit();
											}
										});
									}
								};
								_timer.schedule(DelayTambahPoinAbsen, (int)(15000));
				}
			}
		});
		text_absenmasuk.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) { 
				if (databaseDanSetting.getString("terakhirAbsen", "").equals(tanggal.getText().toString())) {
					showMessage("Hari ini kamu sudah absen");
				}
				else {
					linear9.setAlpha((float)(0.5d));
					databaseDanSetting.edit().putString("terakhirAbsen", tanggal.getText().toString()).commit();
					showMessage("Absen berhasil, tunggu 15 Detik poin akan ditambahkan!");
					DelayTambahPoinAbsen = new TimerTask() {
								@Override
									public void run() {
										runOnUiThread(new Runnable() {
										@Override
											public void run() {
																tambahPoinDariAbsen = Double.parseDouble(databaseDanSetting.getString("poin", "")) + 15;
											databaseDanSetting.edit().putString("poin", String.valueOf((long)(tambahPoinDariAbsen))).commit();
											}
										});
									}
								};
								_timer.schedule(DelayTambahPoinAbsen, (int)(15000));
				}
			}
		});
		pesanSistem.addChildEventListener(new ChildEventListener() {
		@Override
		public void onChildAdded(DataSnapshot _dataSnapshot, String _previousValue) {
			GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
			HashMap<String, Object> _childValue = _dataSnapshot.getValue(_ind);
				informasi = new Gson().fromJson(new Gson().toJson(_childValue), new TypeToken<HashMap<String, Object>>(){}.getType());
				databaseDanSetting.edit().putString("infoAdmin", informasi.get("pesanDariAdmin").toString()).commit();
			}
			@Override
			public void onChildChanged(DataSnapshot _dataSnapshot, String _value) {
			GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
			HashMap<String, Object> _childValue = _dataSnapshot.getValue(_ind);
				informasi = new Gson().fromJson(new Gson().toJson(_childValue), new TypeToken<HashMap<String, Object>>(){}.getType());
				databaseDanSetting.edit().putString("infoAdmin", informasi.get("pesanDariAdmin").toString()).commit();
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

		icon_berbagi.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) { 
				//create dialog builder
final AlertDialog alertDialog = new AlertDialog.Builder(KontenActivity.this).create();

alertDialog.setCancelable(false);

LayoutInflater inflater = getLayoutInflater();
//here type R.layout.YOUR_VIEW
View convertView = (View) inflater.inflate(R.layout.pilahan_bagi_c, null);
alertDialog.setView(convertView);
//define it's widgets
Button btn_wa = (Button) convertView.findViewById(R.id.wa);
Button btn_fb = (Button) convertView.findViewById(R.id.fb);

//listeners and other codes for dialog
btn_wa.setOnClickListener(new View.OnClickListener(){
    public void onClick(View v){
        alertDialog.dismiss();
        try {
            Intent intentwa = new Intent(Intent.ACTION_SEND);
            intentwa.setType("text/plain");
            intentwa.putExtra(Intent.EXTRA_TEXT, "https://otoclash.com/amp/" +
                    " Hey bro! Yuk gabung sama aku di aplikasi android yang bisa kasih pulsa kamu secara GRATISS");
            intentwa.setPackage("com.whatsapp");
            startActivity(intentwa);
        }catch(Exception e){
            Toast.makeText(KontenActivity.this, "Error! Sepertinya kamu belum instal aplikasi WhatsApp",
                    Toast.LENGTH_SHORT).show();
        }
    }
});
btn_fb.setOnClickListener(new View.OnClickListener(){
    public void onClick(View v){
        alertDialog.dismiss();
        try {
            Intent intentfb = new Intent(Intent.ACTION_SEND);
            intentfb.setType("text/plain");
            intentfb.putExtra(Intent.EXTRA_TEXT, "https://otoclash.com/amp/" +
                    " Hey bro! Yuk gabung sama aku di aplikasi android yang bisa kasih pulsa kamu secara GRATISS");
            intentfb.setPackage("com.facebook.katana");
            startActivity(intentfb);
        }catch(Exception e){
            Toast.makeText(KontenActivity.this, "Error! Sepertinya kamu belum instal aplikasi Facebook", Toast.LENGTH_SHORT).show();
        }
    }
});

alertDialog.show();
				nilaiDariBerbagi = Double.parseDouble(databaseDanSetting.getString("poin", "")) + 5;
				databaseDanSetting.edit().putString("poin", String.valueOf((long)(nilaiDariBerbagi))).commit();
			}
		});
		text_berbagi.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) { 
				//create dialog builder
final AlertDialog alertDialog = new AlertDialog.Builder(KontenActivity.this).create();

alertDialog.setCancelable(false);

LayoutInflater inflater = getLayoutInflater();
//here type R.layout.YOUR_VIEW
View convertView = (View) inflater.inflate(R.layout.pilahan_bagi_c, null);
alertDialog.setView(convertView);
//define it's widgets
Button btn_wa = (Button) convertView.findViewById(R.id.wa);
Button btn_fb = (Button) convertView.findViewById(R.id.fb);

//listeners and other codes for dialog
btn_wa.setOnClickListener(new View.OnClickListener(){
    public void onClick(View v){
        alertDialog.dismiss();
        try {
            Intent intentwa = new Intent(Intent.ACTION_SEND);
            intentwa.setType("text/plain");
            intentwa.putExtra(Intent.EXTRA_TEXT, "https://otoclash.com/amp/" +
                    " Hey bro! Yuk gabung sama aku di aplikasi android yang bisa kasih pulsa kamu secara GRATISS");
            intentwa.setPackage("com.whatsapp");
            startActivity(intentwa);
        }catch(Exception e){
            Toast.makeText(KontenActivity.this, "Error! Sepertinya kamu belum instal aplikasi WhatsApp",
                    Toast.LENGTH_SHORT).show();
        }
    }
});
btn_fb.setOnClickListener(new View.OnClickListener(){
    public void onClick(View v){
        alertDialog.dismiss();
        try {
            Intent intentfb = new Intent(Intent.ACTION_SEND);
            intentfb.setType("text/plain");
            intentfb.putExtra(Intent.EXTRA_TEXT, "https://otoclash.com/amp/" +
                    " Hey bro! Yuk gabung sama aku di aplikasi android yang bisa kasih pulsa kamu secara GRATISS");
            intentfb.setPackage("com.facebook.katana");
            startActivity(intentfb);
        }catch(Exception e){
            Toast.makeText(KontenActivity.this, "Error! Sepertinya kamu belum instal aplikasi Facebook", Toast.LENGTH_SHORT).show();
        }
    }
});

alertDialog.show();
				nilaiDariBerbagi = Double.parseDouble(databaseDanSetting.getString("poin", "")) + 5;
				databaseDanSetting.edit().putString("poin", String.valueOf((long)(nilaiDariBerbagi))).commit();
			}
		});
		cekKasihRating.addChildEventListener(new ChildEventListener() {
		@Override
		public void onChildAdded(DataSnapshot _dataSnapshot, String _previousValue) {
			GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
			HashMap<String, Object> _childValue = _dataSnapshot.getValue(_ind);
				cekRating = new Gson().fromJson(new Gson().toJson(_childValue), new TypeToken<HashMap<String, Object>>(){}.getType());
				if (cekRating.get("nope").toString().equals(nope.getText().toString())) {
					databaseDanSetting.edit().putString("cekPernahRating", cekRating.get("kasihNilai").toString()).commit();
				}
			}
			@Override
			public void onChildChanged(DataSnapshot _dataSnapshot, String _value) {
			GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
			HashMap<String, Object> _childValue = _dataSnapshot.getValue(_ind);

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

		operator.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView _parent, View _view, final int _position, long _id) { 
				databaseDanSetting.edit().putString("operator", listOperator.get((int)_position).get("operator").toString()).commit();
			}
			@Override
			public void onNothingSelected(AdapterView _parent) { 
			}
		});

		sepuluhribu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) { 
				if (Double.parseDouble(databaseDanSetting.getString("poin", "")) < 1000) {
					showMessage("Poin kamu kurang dari 1000, kumpulin lagi ya!");
				}
				else {
					if (Double.parseDouble(databaseDanSetting.getString("poin", "")) > 1000) {
						if (databaseDanSetting.getString("operator", "").equals("Operator")) {
							showMessage("Tentukan dulu Operatormu!");
						}
						else {
							AkunClaimPoin = new HashMap<>();
							AkunClaimPoin.put("nope", nope.getText().toString());
							AkunClaimPoin.put("operator", databaseDanSetting.getString("operator", ""));
							AkunClaimPoin.put("nominal", "Rp 10.000");
							AkunClaimPoin.put("tanggalClaim", tanggal.getText().toString());
							DataAkunClaimPoin.push().updateChildren(AkunClaimPoin);
							seribu = 1000;
							_kurangi1000_(seribu);
							poin.setText(databaseDanSetting.getString("poin", ""));
						}
					}
				}
			}
		});
		limapuluhribu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) { 
				if (Double.parseDouble(databaseDanSetting.getString("poin", "")) < 5000) {
					showMessage("Poin kamu kurang dari 5000, kumpulin lagi ya!");
				}
				else {
					if (Double.parseDouble(databaseDanSetting.getString("poin", "")) > 5000) {
						if (databaseDanSetting.getString("operator", "").equals("Operator")) {
							showMessage("Tentukan dulu Operatormu!");
						}
						else {
							AkunClaimPoin = new HashMap<>();
							AkunClaimPoin.put("nope", nope.getText().toString());
							AkunClaimPoin.put("operator", databaseDanSetting.getString("operator", ""));
							AkunClaimPoin.put("nominal", "Rp 50.000");
							AkunClaimPoin.put("tanggalClaim", tanggal.getText().toString());
							DataAkunClaimPoin.push().updateChildren(AkunClaimPoin);
							limaribu = 5000;
							_kurangi5000_(limaribu);
							poin.setText(databaseDanSetting.getString("poin", ""));
						}
					}
				}
			}
		});
		seratusribu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) { 
				if (Double.parseDouble(databaseDanSetting.getString("poin", "")) < 10000) {
					showMessage("Poin kamu kurang dari 10000, kumpulin lagi ya!");
				}
				else {
					if (Double.parseDouble(databaseDanSetting.getString("poin", "")) > 10000) {
						if (databaseDanSetting.getString("operator", "").equals("Operator")) {
							showMessage("Tentukan dulu Operatormu!");
						}
						else {
							AkunClaimPoin = new HashMap<>();
							AkunClaimPoin.put("nope", nope.getText().toString());
							AkunClaimPoin.put("operator", databaseDanSetting.getString("operator", ""));
							AkunClaimPoin.put("nominal", "Rp 100.000");
							AkunClaimPoin.put("tanggalClaim", tanggal.getText().toString());
							DataAkunClaimPoin.push().updateChildren(AkunClaimPoin);
							spuluhribu = 10000;
							_kurangi10000_(spuluhribu);
							poin.setText(databaseDanSetting.getString("poin", ""));
						}
					}
				}
			}
		});
		spinner_menu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView _parent, View _view, final int _position, long _id) { 
				if (listMenu.get((int)_position).get("menu").toString().equals("")) {

				}
				else {
					if (listMenu.get((int)_position).get("menu").toString().equals("Hubungi via WA")) {
						waAdmin.setAction(Intent.ACTION_VIEW);
						waAdmin.setData(Uri.parse("https://api.whatsapp.com/send?phone=6282237215927&text=Salam%20Pulsa Gratisan%20Saya%20Mohon%20Bantuan%20Untuk%20:".concat("".concat("%3E%3E"))));
						startActivity(waAdmin);
					}
					else {
						if (listMenu.get((int)_position).get("menu").toString().equals("Hubungi via Email")) {
							mailAdmin.setAction(Intent.ACTION_VIEW);
							mailAdmin.setData(Uri.parse("mailto:cs@otoclash.com"));
							startActivity(mailAdmin);
						}
						else {
							if (listMenu.get((int)_position).get("menu").toString().equals("Tentang Kami")) {
								tentangKami.setTitle("Pulsa Gratisan".concat(" V. ".concat(databaseDanSetting.getString("versiSaat_ini", ""))));
								tentangKami.setMessage("Kami membuat aplikasi ini untuk sama sama saling untung. Jika anda ingin beriklan di aplikasi ini silahkan hubungi kami");
								tentangKami.setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface _dialog, int _which) {
								
									}
								});
								tentangKami.create().show();
							}
							else {

							}
						}
					}
				}
			}
			@Override
			public void onNothingSelected(AdapterView _parent) { 
			}
		});


	}

	private void  initializeLogic() {
		runTimeCheck = new TimerTask() {
					@Override
						public void run() {
							runOnUiThread(new Runnable() {
							@Override
								public void run() {
										_sumberFile();
								_TerimaPesanAdmin();
								if (databaseDanSetting.getString("terakhirAbsen", "").equals(tanggal.getText().toString())) {
									linear9.setAlpha((float)(0.5d));
								}
								else {
									linear9.setAlpha((float)(1.0d));
								}
								if (databaseDanSetting.getString("cekPernahRating", "").equals("1")) {
									linear11.setAlpha((float)(0.5d));
								}
								else {
									linear11.setAlpha((float)(1.0d));
								}
								}
							});
						}
					};
					_timer.scheduleAtFixedRate(runTimeCheck, (int)(100), (int)(100));
		_sumberScript();
		_waktuKeluarIklan();
		_detikWaktuON();
		_TanggalHari_ini();
		_spinnerOperator();
		_spinnerMenu();
	}

	@Override
	public void onStart() {
		super.onStart();

	}
	@Override
	public void onBackPressed() {
				keluar_d.setTitle("Keluar");
				keluar_d.setMessage("Meninggalkan aplikasi ?");
				keluar_d.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
											firebasedata = new HashMap<>();
							firebasedata.put("nama", databaseDanSetting.getString("nama", ""));
							firebasedata.put("nope", databaseDanSetting.getString("nope", ""));
							firebasedata.put("poin", databaseDanSetting.getString("poin", ""));
							databaseAkun.push().updateChildren(firebasedata);
							finish();
					}
				});
				keluar_d.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
				
					}
				});
				keluar_d.create().show();
	}

	private void _timerEllapse (final double _sec) {
		jam = _sec / 3600;
		menit = (_sec % 3600) / 60;
		detik = _sec % 60;
		timer.setText(String.valueOf((long)(jam)).concat(":").concat(String.valueOf((long)(menit)).concat(":").concat(String.valueOf((long)(detik)))));
	}
	private void _sumberScript () {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { actionbar.setElevation(25f);}
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { linear9.setElevation(15f);}
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { linear10.setElevation(15f);}
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { linear11.setElevation(15f);}
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { linear12.setElevation(15f);}
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { linear8.setElevation(25f);}
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		android.graphics.drawable.GradientDrawable linearLy = new android.graphics.drawable.GradientDrawable();  linearLy.setColor(Color.BLACK);  linearLy.setCornerRadius(15);  linear4.setBackground(linearLy);
		android.graphics.drawable.GradientDrawable linearLy9 = new android.graphics.drawable.GradientDrawable();  linearLy9.setColor(Color.WHITE);  linearLy9.setCornerRadius(30);  linear9.setBackground(linearLy9);
		android.graphics.drawable.GradientDrawable linearLy10 = new android.graphics.drawable.GradientDrawable();  linearLy10.setColor(Color.WHITE);  linearLy10.setCornerRadius(30);  linear10.setBackground(linearLy10);
		android.graphics.drawable.GradientDrawable linearLy11 = new android.graphics.drawable.GradientDrawable();  linearLy11.setColor(Color.WHITE);  linearLy11.setCornerRadius(30);  linear11.setBackground(linearLy11);
		android.graphics.drawable.GradientDrawable linearLy12 = new android.graphics.drawable.GradientDrawable();  linearLy12.setColor(Color.WHITE);  linearLy12.setCornerRadius(30);  linear12.setBackground(linearLy12);
		android.graphics.drawable.GradientDrawable linearLy20 = new android.graphics.drawable.GradientDrawable();  linearLy20.setColor(Color.WHITE);  linearLy20.setCornerRadius(75);  linear20.setBackground(linearLy20);
		poin.setShadowLayer(3,1,1, Color.BLACK);
	}
	private void _sumberFile () {
		nope.setText(databaseDanSetting.getString("nope", ""));
		poin.setText(databaseDanSetting.getString("poin", ""));
	}
	private void _waktuKeluarIklan () {
		tmr_adshow = new TimerTask() {
					@Override
						public void run() {
							runOnUiThread(new Runnable() {
							@Override
								public void run() {
										showMessage("Memuat iklan");
								}
							});
						}
					};
					_timer.scheduleAtFixedRate(tmr_adshow, (int)(30000), (int)(30000));
	}
	private void _detikWaktuON () {
		if (cek == 0) {
			tmr_on = new TimerTask() {
						@Override
							public void run() {
								runOnUiThread(new Runnable() {
								@Override
									public void run() {
												timeElapsed++;
									_timerEllapse(timeElapsed);
									}
								});
							}
						};
						_timer.scheduleAtFixedRate(tmr_on, (int)(1000), (int)(1000));
			cek = 1;
		}
	}
	private void _TanggalHari_ini () {
		tanggal.setText(new SimpleDateFormat("dd/MM/yyyy").format(calender_on.getTime()));
		calender_on = Calendar.getInstance();
	}
	private void _TerimaPesanAdmin () {
		isi_pesan_sistem.setText(databaseDanSetting.getString("infoAdmin", ""));
	}
	private void _spinnerOperator () {
		{
		HashMap<String, Object> _item = new HashMap<>();
		_item.put("operator", "Operator");
		listOperator.add(_item);
		}

		{
		HashMap<String, Object> _item = new HashMap<>();
		_item.put("operator", "Telkomsel");
		listOperator.add(_item);
		}

		{
		HashMap<String, Object> _item = new HashMap<>();
		_item.put("operator", "Smartfren");
		listOperator.add(_item);
		}

		{
		HashMap<String, Object> _item = new HashMap<>();
		_item.put("operator", "Indosat");
		listOperator.add(_item);
		}

		{
		HashMap<String, Object> _item = new HashMap<>();
		_item.put("operator", "Three");
		listOperator.add(_item);
		}

		{
		HashMap<String, Object> _item = new HashMap<>();
		_item.put("operator", "XL");
		listOperator.add(_item);
		}

		{
		HashMap<String, Object> _item = new HashMap<>();
		_item.put("operator", "Axis");
		listOperator.add(_item);
		}

		operator.setAdapter(new ViewoperatorAdapter(listOperator));
	}
	private void _kurangi1000_ (final double _seribu) {
		databaseDanSetting.edit().putString("poin", String.valueOf((long)(Double.parseDouble(databaseDanSetting.getString("poin", "")) - _seribu))).commit();
	}
	private void _kurangi5000_ (final double _limaribu) {
		databaseDanSetting.edit().putString("poin", String.valueOf((long)(Double.parseDouble(databaseDanSetting.getString("poin", "")) - _limaribu))).commit();
	}
	private void _kurangi10000_ (final double _sepuluhribu) {
		databaseDanSetting.edit().putString("poin", String.valueOf((long)(Double.parseDouble(databaseDanSetting.getString("poin", "")) - _sepuluhribu))).commit();
	}
	private void _spinnerMenu () {
		{
		HashMap<String, Object> _item = new HashMap<>();
		_item.put("menu", "");
		listMenu.add(_item);
		}

		{
		HashMap<String, Object> _item = new HashMap<>();
		_item.put("menu", "Hubungi via WA");
		listMenu.add(_item);
		}

		{
		HashMap<String, Object> _item = new HashMap<>();
		_item.put("menu", "Hubungi via Email");
		listMenu.add(_item);
		}

		{
		HashMap<String, Object> _item = new HashMap<>();
		_item.put("menu", "Tentang Kami");
		listMenu.add(_item);
		}

		spinner_menu.setAdapter(new List_menuAdapter(listMenu));
	}


	public class List_menuAdapter extends BaseAdapter {		ArrayList<HashMap<String, Object>> _data;
		public List_menuAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _i) {
			return _data.get(_i);
		}
		
		@Override
		public long getItemId(int _i) {
			return _i;
		}
		@Override
		public View getView(final int _position, View _view, ViewGroup _viewGroup) {
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _v = _view;
			if (_v == null) {
				_v = _inflater.inflate(R.layout.menu_c, null);
			}
			final LinearLayout linear1 = (LinearLayout) _v.findViewById(R.id.linear1);
			final ImageView imageview1 = (ImageView) _v.findViewById(R.id.imageview1);
			final TextView textview1 = (TextView) _v.findViewById(R.id.textview1);

				namaMenu = listMenu.get((int)_position).get("menu").toString();
				textview1.setText(namaMenu);
				if (namaMenu.equals("")) {
					imageview1.setImageResource(R.drawable.menu_main);
				}
				if (namaMenu.equals("Hubungi via WA")) {
					imageview1.setImageResource(R.drawable.whatsapp);
				}
				if (namaMenu.equals("Hubungi via Email")) {
					imageview1.setImageResource(R.drawable.email);
				}
				if (namaMenu.equals("Tentang Kami")) {
					imageview1.setImageResource(R.drawable.tentang);
				}
		
			return _v;
		}
	}	public class ViewoperatorAdapter extends BaseAdapter {		ArrayList<HashMap<String, Object>> _data;
		public ViewoperatorAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _i) {
			return _data.get(_i);
		}
		
		@Override
		public long getItemId(int _i) {
			return _i;
		}
		@Override
		public View getView(final int _position, View _view, ViewGroup _viewGroup) {
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _v = _view;
			if (_v == null) {
				_v = _inflater.inflate(R.layout.spinner_c, null);
			}
			final LinearLayout linear1 = (LinearLayout) _v.findViewById(R.id.linear1);
			final ImageView imageview1 = (ImageView) _v.findViewById(R.id.imageview1);
			final LinearLayout linear2 = (LinearLayout) _v.findViewById(R.id.linear2);
			final TextView textview1 = (TextView) _v.findViewById(R.id.textview1);

				namaOperator = listOperator.get((int)_position).get("operator").toString();
				textview1.setText(namaOperator);
				if (namaOperator.equals("Operator")) {
					imageview1.setImageResource(R.drawable.cari);
				}
				if (namaOperator.equals("Telkomsel")) {
					imageview1.setImageResource(R.drawable.telkomsel);
				}
				if (namaOperator.equals("Smartfren")) {
					imageview1.setImageResource(R.drawable.smartfren);
				}
				if (namaOperator.equals("Indosat")) {
					imageview1.setImageResource(R.drawable.indosat);
				}
				if (namaOperator.equals("Three")) {
					imageview1.setImageResource(R.drawable.three);
				}
				if (namaOperator.equals("XL")) {
					imageview1.setImageResource(R.drawable.myxl);
				}
				if (namaOperator.equals("Axis")) {
					imageview1.setImageResource(R.drawable.axis);
				}
		
			return _v;
		}
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
