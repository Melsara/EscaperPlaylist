package escapeadvisor.eu.escaperplaylist;

import android.graphics.drawable.Drawable;
import android.net.Uri;

import java.net.URI;

/**
 * Created by Sara on 04/03/2018.
 */

public class Song {

    private String mSongUri;
    private String mSongTitle;
    private String mSongArtist;
    private String mSongAlbum;
    private Drawable mSongArt;

    public Song (String songUri, String songTitle, String songArtist, String songAlbum, Drawable songArt){
        mSongUri = songUri;
        mSongTitle = songTitle;
        mSongArtist = songArtist;
        mSongAlbum = songAlbum;
        mSongArt = songArt;
    }

    public String getSongUri () {
        return mSongUri;
    }

    public String getSongTitle () {
        return mSongTitle;
    }

    public String getSongArtist () {
        return mSongArtist;
    }

    public String getSongAlbum () {
        return mSongAlbum;
    }

    public Drawable getSongArt () {
        return mSongArt;
    }

}
