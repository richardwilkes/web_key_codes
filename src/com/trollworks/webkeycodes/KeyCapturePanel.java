/*
 * Copyright (c) 2011 by Richard A. Wilkes. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * version 2.0. If a copy of the MPL was not distributed with this file, You
 * can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * This Source Code Form is "Incompatible With Secondary Licenses", as defined
 * by the Mozilla Public License, version 2.0.
 */

package com.trollworks.webkeycodes;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.TextArea;

public class KeyCapturePanel implements KeyDownHandler, KeyPressHandler, KeyUpHandler {
	private Canvas		mCanvas;
	private TextArea	mLog;

	public KeyCapturePanel(TextArea log) {
		mLog = log;
		mCanvas = Canvas.createIfSupported();
		mCanvas.setStylePrimaryName("canvas");
		mCanvas.addKeyDownHandler(this);
		mCanvas.addKeyPressHandler(this);
		mCanvas.addKeyUpHandler(this);
	}

	public Canvas getCanvas() {
		return mCanvas;
	}

	@Override
	public void onKeyDown(KeyDownEvent event) {
		logKey("down", event.getNativeEvent());
	}

	@Override
	public void onKeyPress(KeyPressEvent event) {
		logKey("press", event.getNativeEvent());
	}

	@Override
	public void onKeyUp(KeyUpEvent event) {
		logKey("up", event.getNativeEvent());
	}

	private void logKey(String msg, NativeEvent nativeEvent) {
		StringBuilder buffer = new StringBuilder();
		buffer.append(msg);
		buffer.append(" [char code: ");
		buffer.append(nativeEvent.getCharCode());
		buffer.append(", key code:");
		buffer.append(nativeEvent.getKeyCode());
		if (nativeEvent.getAltKey()) {
			buffer.append(", ALT");
		}
		if (nativeEvent.getCtrlKey()) {
			buffer.append(", CTRL");
		}
		if (nativeEvent.getMetaKey()) {
			buffer.append(", META");
		}
		if (nativeEvent.getShiftKey()) {
			buffer.append(", SHIFT");
		}
		buffer.append("]");
		String text = mLog.getText();
		if (text.trim().length() > 0) {
			buffer.append("\n");
			buffer.append(text);
		}
		mLog.setText(buffer.toString());
	}
}
