package com.appbenprojects.musicplayer.DataAccess;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.appbenprojects.musicplayer.Models.Tag;

import java.util.List;

public class MusicPlayerRepository {
	private TagDAO tagDAO;
	private LiveData<List<Tag>> tagList;
	
	public MusicPlayerRepository(Application application) {
		MusicPlayerDatabase db = MusicPlayerDatabase.getInstance(application);
		tagDAO = db.tagDAO();
		tagList = tagDAO.getAllTags();
	}
	
	public LiveData<List<Tag>> getAllTags() {
		return tagList;
	}
	
	public void insert(Tag tag) {
		new insertAsyncTask(tagDAO).execute(tag);
	}
	
	private static class insertAsyncTask extends AsyncTask<Tag, Void, Void> {
		private TagDAO AsyncTaskDao;
		
		insertAsyncTask(TagDAO dao) {
			AsyncTaskDao = dao;
		}
		
		@Override
		protected Void doInBackground(final Tag... params) {
			AsyncTaskDao.insert(params[0]);
			return null;
		}
	}
}
