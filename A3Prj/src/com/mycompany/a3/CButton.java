package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;

public class CButton extends Button {              
	
	public CButton(String label) {              
		super(label);
		this.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		this.getAllStyles().setFgColor(ColorUtil.WHITE);
		this.getPressedStyle().setBgColor(ColorUtil.WHITE);
		this.getPressedStyle().setFgColor(ColorUtil.LTGRAY);
		this.getAllStyles().setPadding(4, 4, 2, 2);
		this.getAllStyles().setMargin(1, 1, 1, 1);
		this.getAllStyles().setBgTransparency(255);
	} 
}