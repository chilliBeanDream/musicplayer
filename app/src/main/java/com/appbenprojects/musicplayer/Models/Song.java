package com.appbenprojects.musicplayer.Models;

import java.util.List;

public class Song {
	private int id;
	private String title;
	private String artist;
	private String album;
	private List<String> genres;
	
	public Song(int id, String title, String artist, String album, List<String> genres) {
		this.id = id;
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.genres = genres;
	}
}
