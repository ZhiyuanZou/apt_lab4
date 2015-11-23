package easy;

import java.util.ArrayList;

public class MockMp3Player implements Mp3Player{
	/** 
	   * Begin playing the filename at the top of the
	   * play list, or do nothing if playlist 
	   * is empty. 
	   */
	ArrayList<String> songList;
	int curSong;
	double curPos;
	
	private boolean isPlay;
	
	  public void play(){
		  if(songList != null){
			  isPlay = true;
			  curPos += 0.01;
		  }else{
			  isPlay = false;
			  curPos = 0;
		  }
	  }

	  /** 
	   * Pause playing. Play will resume at this spot. 
	   */
	  public void pause(){
		  isPlay = false;
	  }

	  /** 
	   * Stop playing. The current song remains at the
	   * top of the playlist, but rewinds to the 
	   * beginning of the song.
	   */
	  public void stop(){
		  isPlay = false;
	  }
	  
	  /** Returns the number of seconds into 
	   * the current song.
	   */
	  public double currentPosition(){
		  return curPos;
	  }


	  /**
	   * Returns the currently playing file name.
	   */
	  public String currentSong(){
		  return songList.get(curSong);
	  }

	  /** 
	   * Advance to the next song in the playlist 
	   * and begin playing it.
	   */
	  public void next(){
		  curSong += 1;
		  if(curSong >= songList.size()){
			  curSong = songList.size()-1;
		  }
		  isPlay = true;
	  }

	  /**
	   * Go back to the previous song in the playlist
	   * and begin playing it.
	   */
	  public void prev(){
		  curSong -= 1;
		  isPlay = true;
		  if(curSong < 0){
			  curSong = 0;
		  }
	  }

	  /** 
	   * Returns true if a song is currently 
	   * being played.
	   */
	  public boolean isPlaying(){
		  return isPlay;
	  }

	  /**
	   * Load filenames into the playlist.
	   */
	  public void loadSongs(ArrayList names){
		  songList = names;
		  isPlay = false;
		  curSong = 0;
	  }
}
