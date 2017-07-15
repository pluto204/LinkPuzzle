package com.hai.link;

import android.app.Activity;
import android.widget.ImageView;

public class LevelBlock {

	public static int Orig_x = 10;
	public static int Orig_y = 68;
	public static int CELL_HEIGHT = 50;
	public static int CELL_WIDTH = 50;
	public int curx;
	public int cury;
	public int direction;
	public int height;
	public boolean isBlocked;
	public int length;
	public int origx;
	public int origy;
	public ImageView sprite;
	public int width;

	public LevelBlock(int x, int y, int dir, int mLength, boolean isBlkd,
			boolean isDo, Activity act) {
		origx = x;
		origy = y;
		curx = x;
		cury = y;
		direction = dir;
		length = mLength;
		isBlocked = isBlkd;
		sprite = null;
		if (isBlocked) {
			if (isDo) {
				sprite = new ImageView(act);
//				sprite.setImageResource(2130837509);
			}

			width = 2 * CELL_WIDTH;
			height = CELL_HEIGHT;
		} else if (direction == 1) {
			if (isDo) {
				sprite = new ImageView(act);
				if (length == 2) {
//					sprite.setImageResource(2130837515);
				} else if (length == 3) {
//					sprite.setImageResource(2130837516);
				}
			}

			width = CELL_WIDTH * length;
			height = CELL_HEIGHT;
		} else {
			if (isDo) {
				sprite = new ImageView(act);
				if (length == 2) {
//					sprite.setImageResource(2130837532);
				} else if (length == 3) {
//					sprite.setImageResource(2130837533);
				}
			}

			width = CELL_WIDTH;
			height = CELL_HEIGHT * length;
		}
	}
}
