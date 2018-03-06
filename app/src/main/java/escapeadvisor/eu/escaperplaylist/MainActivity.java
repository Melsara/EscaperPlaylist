package escapeadvisor.eu.escaperplaylist;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Song> songs = new ArrayList<Song>();

        songs.add(new Song("spotify:track:08boNC19ELI4p6QJhNDFX7", "Title1", "Artist1", "Album1", (getDrawable(R.drawable.art1))));
        songs.add(new Song("spotify:track:08boNC19ELI4p6QJhNDFX7", "Title2", "Artist2", "Album2", (getDrawable(R.drawable.art2))));
        songs.add(new Song("spotify:track:08boNC19ELI4p6QJhNDFX7", "Title3", "Artist3", "Album3", (getDrawable(R.drawable.art3))));
        songs.add(new Song("spotify:track:08boNC19ELI4p6QJhNDFX7", "Title4", "Artist4", "Album4", (getDrawable(R.drawable.art4))));
        songs.add(new Song("spotify:track:08boNC19ELI4p6QJhNDFX7", "Title5", "Artist5", "Album5", (getDrawable(R.drawable.art5))));
        songs.add(new Song("spotify:track:08boNC19ELI4p6QJhNDFX7", "Title6", "Artist6", "Album6", (getDrawable(R.drawable.art6))));

        SongAdapter songAdapter =  new SongAdapter(this, songs);
        GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(songAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Song song = (Song) adapterView.getItemAtPosition(i);
                String uri = song.getSongUri();
                String title = song.getSongTitle();
                String artist = song.getSongArtist();
                String album = song.getSongAlbum();
                Drawable art = song.getSongArt();
                Intent openSongDetail = new Intent(MainActivity.this, DetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(URI_KEY, uri);
                bundle.putString(TITLE_KEY, title);
                bundle.putString(ARTIST_KEY, artist);
                bundle.putString(ALBUM_KEY, album);
                openSongDetail.putExtras(bundle);
                startActivity(openSongDetail);

            }
        });

    }
}
