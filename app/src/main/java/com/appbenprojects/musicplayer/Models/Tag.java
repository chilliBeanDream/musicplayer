package com.appbenprojects.musicplayer.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Tags")
public class Tag {
	@PrimaryKey(autoGenerate = true)
	public int id;
	
	@NonNull
	public String songURI;
	
	@NonNull
	public String tagName;
}