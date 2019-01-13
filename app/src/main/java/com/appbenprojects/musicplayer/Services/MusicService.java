package com.appbenprojects.musicplayer.Services;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.appbenprojects.musicplayer.Models.Song;

import java.util.ArrayList;
import java.util.List;

public class MusicService {
	// Handle device music files
	
	private final Uri AUDIO_URI = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
	private ContentResolver audioResolver;
	private final String[] musicColumns = {
			MediaStore.Audio.Media._ID,
			MediaStore.Audio.Media.TITLE,
			MediaStore.Audio.Media.ARTIST,
			MediaStore.Audio.Media.ALBUM
	};
	private final String[] genreColumns = {
			MediaStore.Audio.Genres._ID,
			MediaStore.Audio.Genres.NAME
	};
	
	public MusicService(Application application) {
		audioResolver = application.getContentResolver();
	}
	
	public List<Song> getDeviceSongs() {
		Cursor musicCursor = audioResolver.query(AUDIO_URI, musicColumns, null, null, "ASC LIMIT 10");
		
		ArrayList<Song> songArrayList = new ArrayList<>();
		
		if (musicCursor.moveToFirst()) {
			int idColumnIndex = musicCursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID);
			int titleColumnIndex = musicCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE);
			int artistColumnIndex = musicCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST);
			int albumColumnIndex = musicCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM);
			
			do {
				int songId = musicCursor.getInt(idColumnIndex);
				String songTitle = musicCursor.getString(titleColumnIndex);
				String songArtist = musicCursor.getString(artistColumnIndex);
				String songAlbum = musicCursor.getString(albumColumnIndex);
				List<String> songGenres = getGenresForSong(songId);
				songArrayList.add(new Song(songId, songTitle, songArtist, songAlbum, songGenres));
			} while (musicCursor.moveToNext());
		}
		musicCursor.close();
		return songArrayList;
	}
	
	private List<String> getGenresForSong(int songId) {
		Uri genreUri = MediaStore.Audio.Genres.getContentUriForAudioId("external", songId);
		Cursor genreCursor = audioResolver.query(genreUri, genreColumns, null, null, null);
		
		ArrayList<String> genreArrayList = new ArrayList<>();
		
		if (genreCursor.moveToFirst()) {
			int genreColumnIndex = genreCursor.getColumnIndexOrThrow(MediaStore.Audio.Genres.NAME);
			do {
				genreArrayList.add(genreCursor.getString(genreColumnIndex));
			} while (genreCursor.moveToNext());
		}
		
		genreCursor.close();
		
		return genreArrayList;
	}
}
