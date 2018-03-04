package escapeadvisor.eu.escaperplaylist;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Song> songs = new ArrayList<Song>();

        songs.add(new Song("spotify:track:08boNC19ELI4p6QJhNDFX7", "Title", "Artist", "Album", (getDrawable(R.drawable.logo))));

        SongAdapter songAdapter =  new SongAdapter(this, songs);
        GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(songAdapter);

    }
}
