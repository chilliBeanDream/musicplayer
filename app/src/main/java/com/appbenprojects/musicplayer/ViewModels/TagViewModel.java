package com.appbenprojects.musicplayer.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.appbenprojects.musicplayer.DataAccess.MusicPlayerRepository;
import com.appbenprojects.musicplayer.Models.Tag;

import java.util.List;

public class TagViewModel extends AndroidViewModel {
	private MusicPlayerRepository musicPlayerRepository;
	private LiveData<List<Tag>> tagList;
	
	public TagViewModel(Application application) {
		super(application);
		musicPlayerRepository = new MusicPlayerRepository(application);
		tagList = musicPlayerRepository.getAllTags();
	}
	
	public LiveData<List<Tag>> getAllTags() {
		return tagList;
	}
	
	public void insert(Tag tag) {
		musicPlayerRepository.insert(tag);
	}
}
