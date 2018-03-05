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

        songs.add(new Song("spotify:track:08boNC19ELI4p6QJhNDFX7", "Title1", "Artist1", "Album1", (getDrawable(R.drawable.art1))));
        songs.add(new Song("spotify:track:08boNC19ELI4p6QJhNDFX7", "Title2", "Artist2", "Album2", (getDrawable(R.drawable.art2))));
        songs.add(new Song("spotify:track:08boNC19ELI4p6QJhNDFX7", "Title3", "Artist3", "Album3", (getDrawable(R.drawable.art3))));
        songs.add(new Song("spotify:track:08boNC19ELI4p6QJhNDFX7", "Title4", "Artist4", "Album4", (getDrawable(R.drawable.art4))));
        songs.add(new Song("spotify:track:08boNC19ELI4p6QJhNDFX7", "Title5", "Artist5", "Album5", (getDrawable(R.drawable.art5))));
        songs.add(new Song("spotify:track:08boNC19ELI4p6QJhNDFX7", "Title6", "Artist6", "Album6", (getDrawable(R.drawable.art6))));

        SongAdapter songAdapter =  new SongAdapter(this, songs);
        GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(songAdapter);

    }
}
