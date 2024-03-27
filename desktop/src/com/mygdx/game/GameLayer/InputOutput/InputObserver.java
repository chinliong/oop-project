package com.mygdx.game.GameLayer.InputOutput;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

//Observer interface for input events
public interface InputObserver {
	void onKeyEvent(KeyEvent event);

	void onMouseEvent(MouseEvent event);
}
