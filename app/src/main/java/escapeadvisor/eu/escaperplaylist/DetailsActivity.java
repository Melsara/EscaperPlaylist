package escapeadvisor.eu.escaperplaylist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends Activity {

    private String uri;
    private String title;
    private String artist;
    private String album;
    private int art;
    private ImageView artImageView;
    private TextView titleTextView;
    private TextView artistTextView;
    private TextView albumTextView;
    final static String URI_KEY = "uri_key";
    final static String TITLE_KEY = "title_key";
    final static String ARTIST_KEY = "artist_key";
    final static String ALBUM_KEY = "album_key";
    final static String ART_KEY = "art_key";
    ImageButton playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent openSongDetail = getIntent();
        final Bundle bundle = openSongDetail.getExtras();
        uri = bundle.getString(URI_KEY);
        title = bundle.getString(TITLE_KEY);
        artist = bundle.getString(ARTIST_KEY);
        album = bundle.getString(ALBUM_KEY);
        art = bundle.getInt(ART_KEY);
        setActivityComponent();
        playButton = (ImageButton) findViewById(R.id.buttonPlay);

        if (playButton != null) {
            playButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startPlayer();
                }
            });

        }

    }

    /**
     * Find views on activity_details
     */
    public void setActivityComponent() {
        artImageView = (ImageView) findViewById(R.id.playerImage);
        artImageView.setImageResource(art);
        titleTextView = (TextView) findViewById(R.id.textViewTitle);
        titleTextView.setText(title);
        artistTextView = (TextView) findViewById(R.id.textViewArtist);
        artistTextView.setText(artist);
        albumTextView = (TextView) findViewById(R.id.textViewAlbum);
        albumTextView.setText(album);
    }

    /**
     * Start playing song (managed by Spotify library)
     */
    public void startPlayer () {
        Intent playSong = new Intent(DetailsActivity.this, SpfyPlayer.class);
        Bundle bundle = new Bundle();
        bundle.putString(URI_KEY, uri);
        playSong.putExtras(bundle);
        startActivity(playSong);
    }

}
