package com.appbenprojects.musicplayer.DataAccess;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.appbenprojects.musicplayer.Models.Tag;

import java.util.List;

@Dao
public interface TagDAO {
	@Insert
	void insert(Tag tag);
	
	@Query("SELECT * FROM Tags ORDER BY tagName ASC")
	LiveData<List<Tag>> getAllTags();
}
