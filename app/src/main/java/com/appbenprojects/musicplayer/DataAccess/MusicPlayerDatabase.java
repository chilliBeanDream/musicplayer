package com.appbenprojects.musicplayer.DataAccess;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.appbenprojects.musicplayer.Models.Tag;

@Database(entities = {Tag.class}, version = 1)
public abstract class MusicPlayerDatabase extends RoomDatabase {
	public abstract TagDAO tagDAO();
	
	private static volatile MusicPlayerDatabase instance;
	
	static MusicPlayerDatabase getInstance(final Context context) {
		if (instance == null) {
			synchronized (MusicPlayerDatabase.class) {
				if (instance == null) {
					instance = Room.databaseBuilder(
							context.getApplicationContext(),
							MusicPlayerDatabase.class,
							"music_player_database")
							.build();
				}
			}
		}
		return instance;
	}
}
