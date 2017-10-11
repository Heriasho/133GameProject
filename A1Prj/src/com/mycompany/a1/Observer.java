package com.mycompany.a1;

import java.util.Observable;

public interface Observer {
	public void update(Observable o, Object arg);
}
