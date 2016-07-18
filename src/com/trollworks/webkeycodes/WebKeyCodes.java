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
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TextArea;

public class WebKeyCodes implements EntryPoint {
	@Override
	public void onModuleLoad() {
		RootLayoutPanel root = RootLayoutPanel.get();
		TextArea log = new TextArea();
		log.setStylePrimaryName("log");
		log.setEnabled(false);
		FlowPanel outer = new FlowPanel();
		outer.setStylePrimaryName("outer");
		outer.add(log);
		KeyCapturePanel capturePanel = new KeyCapturePanel(log);
		Canvas canvas = capturePanel.getCanvas();
		outer.add(canvas);
		root.add(outer);
		canvas.setFocus(true);
	}

	private static native void log(String message)
	/*-{
		if (!!(window.console && window.console.firebug)) {
			window.console.warn(message);
		} else if ((window.console != null) && (window.console.firebug == null) && (window.console.log != null) && (typeof (window.console.log) == 'function')) {
			window.console.log(message);
		}
	}-*/;
}
