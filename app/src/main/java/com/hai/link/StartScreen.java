package com.hai.link;

import java.util.Random;

import com.hai.link.utils.DataHandler;
import com.hai.link.utils.PrefClass;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StartScreen extends Activity implements OnClickListener {

	private Button bstart, bmore, bselectPack, bsettings;
	private Intent intent;
	private Context context = this;

	private LinearLayout llmain;
	private Random rand = new Random();

	private int[] draw = new int[] { R.drawable.pressed_roundrect_five, R.drawable.pressed_roundrect_six, R.drawable.pressed_roundrect_seven,
			R.drawable.pressed_roundrect_eight, R.drawable.pressed_roundrect_nine, R.drawable.pressed_roundrect_ten, R.drawable.pressed_roundrect_eleven,
			R.drawable.pressed_roundrect_twelve, R.drawable.pressed_roundrect_thirteen, R.drawable.pressed_roundrect_fourteen };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startscreen);
		getScreenDimen();

		bstart = (Button) findViewById(R.id.bstart);
		bmore = (Button) findViewById(R.id.bmore);
		bselectPack = (Button) findViewById(R.id.bselectPack);
		bsettings = (Button) findViewById(R.id.bstore);
		llmain = (LinearLayout) findViewById(R.id.llmainheading);
		bstart.setTypeface(DataHandler.getTypeface(context));
		bmore.setTypeface(DataHandler.getTypeface(context));
		bselectPack.setTypeface(DataHandler.getTypeface(context));
		bsettings.setTypeface(DataHandler.getTypeface(context));

		llmain.setBackgroundResource(draw[rand.nextInt(10)]);

		bstart.setOnClickListener(this);
		bmore.setOnClickListener(this);
		bselectPack.setOnClickListener(this);
		bsettings.setOnClickListener(this);

		((TextView) findViewById(R.id.tvheading)).setTypeface(DataHandler.getTypeface(context));

		@SuppressWarnings("unused")
		PrefClass p1 = new PrefClass(context);
		DataHandler.checkAndReturnColor(GameSaver.sharedInstance(context.getApplicationContext()).lastPackSelected, context);

	}

	private void getScreenDimen() {
		DisplayMetrics dm = getResources().getDisplayMetrics();
		DataHandler.setDevice_width(dm.widthPixels);
		DataHandler.setDevice_height(dm.heightPixels);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bstart:
			intent = new Intent(context, GameActivity.class);
			DataHandler.setCurrentPack(GameSaver.sharedInstance(context.getApplicationContext()).lastPackSelected);
			DataHandler.setCurrentLevel(GameSaver.sharedInstance(context.getApplicationContext()).lastLevelSlected);
			startActivity(intent);
			break;
		case R.id.bmore:
			startActivity(new Intent(context, MoreScreen.class));
			break;
		case R.id.bselectPack:
			startActivity(new Intent(context, PackType.class));
			break;
		case R.id.bstore:
			startActivity(new Intent(context, GetHints.class));
			break;
		}
	}
}
