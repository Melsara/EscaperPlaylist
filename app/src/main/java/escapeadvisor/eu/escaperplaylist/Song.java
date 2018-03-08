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
    private int mSongArt;

    public Song (String songUri, String songTitle, String songArtist, String songAlbum, int songArt){
        mSongUri = songUri;
        mSongTitle = songTitle;
        mSongArtist = songArtist;
        mSongAlbum = songAlbum;
        mSongArt = songArt;
    }

    /**
     * Get song URI
     * @return mSongUri
     */

    public String getSongUri () {
        return mSongUri;
    }

    /**
     * Get song Title
     * @return mSongTitle
     */

    public String getSongTitle () {
        return mSongTitle;
    }

    /**
     * Get song Artist
     * @return mSongArtist
     */

    public String getSongArtist () {
        return mSongArtist;
    }

    /**
     * Get song Album
     * @return mSongAlbum
     */

    public String getSongAlbum () {
        return mSongAlbum;
    }

    /**
     * Get song Art
     * @return mSongArt
     */

    public int getSongArt () {
        return mSongArt;
    }

}
