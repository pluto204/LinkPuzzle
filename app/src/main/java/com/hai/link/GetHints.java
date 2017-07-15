package com.hai.link;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.hai.link.utils.DataHandler;
import com.hai.link.utils.PrefClass;

public class GetHints extends Activity implements RewardedVideoAdListener{

	private Button bvideo;
	private Context context = this;
	private RewardedVideoAd mAd;
	ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.hints_choice);

		mAd = MobileAds.getRewardedVideoAdInstance(this);
		mAd.setRewardedVideoAdListener(this);
		loadRewardedVideoAd();

		progressBar = (ProgressBar)findViewById(R.id.progressBar);
		bvideo = (Button) findViewById(R.id.bvideoHints);
		TextView hintTV = (TextView)findViewById(R.id.hintTV);
		TextView hintVideoTV = (TextView)findViewById(R.id.tvvideohints);

		hintTV.setTypeface(DataHandler.getTypeface(context));
		hintVideoTV.setTypeface(DataHandler.getTypeface(context));
		bvideo.setTypeface(DataHandler.getTypeface(context));


		bvideo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mAd.isLoaded()) {
					mAd.show();
				}else{
					Toast.makeText(GetHints.this, "Sorry, Video is not available", Toast.LENGTH_SHORT).show();
				}
			}
		});

		bvideo.setEnabled(false);

	};

	private void loadRewardedVideoAd() {
		mAd.loadAd("ca-app-pub-9597010572153445/6125264819", new AdRequest.Builder().build());
	}

	private void addHint(int value) {
		int hint = PrefClass.getHint();
		hint = hint + value;
		PrefClass p1 = new PrefClass(context);
		PrefClass.saveHint(hint);
	}

	// Required to reward the user.
	@Override
	public void onRewarded(RewardItem reward) {
		Toast.makeText(this, "You get " + reward.getAmount() + " " + reward.getType() + "s", Toast.LENGTH_SHORT).show();
		addHint(reward.getAmount());
		// Reward the user.
	}

	// The following listener methods are optional.
	@Override
	public void onRewardedVideoAdLeftApplication() {
//		Toast.makeText(this, "onRewardedVideoAdLeftApplication", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onRewardedVideoAdClosed() {
//		Toast.makeText(this, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onRewardedVideoAdFailedToLoad(int errorCode) {
		Toast.makeText(this, "RewardedVideoAd failed to load", Toast.LENGTH_SHORT).show();
		progressBar.setVisibility(View.INVISIBLE);
		bvideo.setEnabled(true);
	}

	@Override
	public void onRewardedVideoAdLoaded() {
		Toast.makeText(this, "Rewarded Video Ad available", Toast.LENGTH_SHORT).show();
		progressBar.setVisibility(View.INVISIBLE);
		bvideo.setEnabled(true);
	}

	@Override
	public void onRewardedVideoAdOpened() {
//		Toast.makeText(this, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onRewardedVideoStarted() {
//		Toast.makeText(this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onResume() {
		mAd.resume(this);
		super.onResume();
	}

	@Override
	public void onPause() {
		mAd.pause(this);
		super.onPause();
	}

	@Override
	public void onDestroy() {
		mAd.destroy(this);
		super.onDestroy();
	}
}
