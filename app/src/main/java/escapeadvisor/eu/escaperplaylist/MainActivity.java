package escapeadvisor.eu.escaperplaylist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    final static String URI_KEY = "uri_key";
    final static String TITLE_KEY = "title_key";
    final static String ARTIST_KEY = "artist_key";
    final static String ALBUM_KEY = "album_key";
    final static String ART_KEY = "art_key";
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActivityComponent();
        createSongList();

/**
 * Set clickable action for all items in the GridView
 */
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Song song = (Song) adapterView.getItemAtPosition(i);
                String uri = song.getSongUri();
                String title = song.getSongTitle();
                String artist = song.getSongArtist();
                String album = song.getSongAlbum();
                int art = song.getSongArt();
                Intent openSongDetail = new Intent(MainActivity.this, DetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(URI_KEY, uri);
                bundle.putString(TITLE_KEY, title);
                bundle.putString(ARTIST_KEY, artist);
                bundle.putString(ALBUM_KEY, album);
                bundle.putInt(ART_KEY, art);
                openSongDetail.putExtras(bundle);
                startActivity(openSongDetail);

            }
        });

    }

    /**
     * Find views on activity_main
     */
    public void setActivityComponent () {
        gridView = (GridView) findViewById(R.id.gridView);
    }

    /**
     * Create Song list and set items through adapter
     */
    public void createSongList () {
        ArrayList<Song> songs = new ArrayList<Song>();

        songs.add(new Song((getString(R.string.uri1)), (getString(R.string.title1)), (getString(R.string.artist1)), (getString(R.string.album1)), R.mipmap.art1));
        songs.add(new Song((getString(R.string.uri2)), (getString(R.string.title2)), (getString(R.string.artist2)), (getString(R.string.album2)), R.mipmap.art2));
        songs.add(new Song((getString(R.string.uri3)), (getString(R.string.title3)), (getString(R.string.artist3)), (getString(R.string.album3)), R.mipmap.art3));
        songs.add(new Song((getString(R.string.uri4)), (getString(R.string.title4)), (getString(R.string.artist4)), (getString(R.string.album4)), R.mipmap.art4));
        songs.add(new Song((getString(R.string.uri5)), (getString(R.string.title5)), (getString(R.string.artist5)), (getString(R.string.album5)), R.mipmap.art5));
        songs.add(new Song((getString(R.string.uri6)), (getString(R.string.title6)), (getString(R.string.artist6)), (getString(R.string.album6)), R.mipmap.art6));

        SongAdapter songAdapter =  new SongAdapter(this, songs);
        gridView.setAdapter(songAdapter);

    }

}
