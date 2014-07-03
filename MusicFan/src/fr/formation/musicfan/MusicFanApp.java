package fr.formation.musicfan;

import android.app.Application;

public class MusicFanApp extends Application {

	private String name = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
