package com.appbenprojects.musicplayer.ViewModels;

import android.app.Application;
import android.arch.lifecycle.ViewModel;

import com.appbenprojects.musicplayer.Models.Song;
import com.appbenprojects.musicplayer.Services.MusicService;

import java.util.List;

public class MusicViewModel extends ViewModel {
	private MusicService musicService;
	public MusicViewModel(Application application) {
		musicService = new MusicService(application);
	}
	
	public List<Song> getAllSongs() {
		return musicService.getDeviceSongs();
	}
}
