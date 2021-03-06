package escapeadvisor.eu.escaperplaylist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Error;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.PlayerEvent;
import com.spotify.sdk.android.player.Spotify;
import com.spotify.sdk.android.player.SpotifyPlayer;

import static escapeadvisor.eu.escaperplaylist.GlobalConstant.ALBUM_KEY;
import static escapeadvisor.eu.escaperplaylist.GlobalConstant.ARTIST_KEY;
import static escapeadvisor.eu.escaperplaylist.GlobalConstant.ART_KEY;
import static escapeadvisor.eu.escaperplaylist.GlobalConstant.TITLE_KEY;
import static escapeadvisor.eu.escaperplaylist.GlobalConstant.URI_KEY;

public class DetailsActivity extends Activity implements SpotifyPlayer.NotificationCallback, ConnectionStateCallback {
    // Spfy player params
    private static final String CLIENT_ID = "[CLIENT_ID]";
    private static final String REDIRECT_URI = "escaperplaylist://callback";
    // Spfy player request code that will be used to verify if the result comes from correct activity
    private static final int REQUEST_CODE = 1111;
    private Player mPlayer;
    private String uri;
    private String title;
    private String artist;
    private String album;
    private int art;
    private ImageView artImageView;
    private TextView titleTextView;
    private TextView artistTextView;
    private TextView albumTextView;
    private TextView buttonText;
    ImageButton playButton;
    private Button backButton;
    private Button stopButton;
    boolean playerIsPlaying = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        //Retrieving data from intent
        Intent openSongDetail = getIntent();
        final Bundle bundle = openSongDetail.getExtras();
        uri = bundle.getString(URI_KEY);
        title = bundle.getString(TITLE_KEY);
        artist = bundle.getString(ARTIST_KEY);
        album = bundle.getString(ALBUM_KEY);
        art = bundle.getInt(ART_KEY);
        setActivityComponent();

        /*Calling back on actionBar*/
        getActionBar().setDisplayHomeAsUpEnabled(true);

        /*OnClickListener on play button*/
        if (playButton != null) {
            playButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    /*Changing state of the play button when pressed*/
                    playButton.setImageResource(R.drawable.spfy);
                    buttonText.setText(R.string.playing);
                    playerIsPlaying = true;

                    /*Launching authentication in Spotify*/
                    AuthenticationRequest.Builder builder = new AuthenticationRequest.Builder(CLIENT_ID,
                            AuthenticationResponse.Type.TOKEN,
                            REDIRECT_URI);
                    builder.setScopes(new String[]{"user-read-private", "streaming"});
                    AuthenticationRequest request = builder.build();

                    AuthenticationClient.openLoginActivity(DetailsActivity.this, REQUEST_CODE, request);
                }
            });

            /*OnClickListener on back button*/
            if (backButton != null) {
                backButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                      onDestroy();
                      finish();
                    }
                });
            }

            /*OnClickListener on stop button*/
            if (stopButton != null) {
                stopButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onDestroy();
                        /*Changing state of the play button when pressed*/
                        playButton.setImageResource(R.drawable.spfygreen);
                        buttonText.setText(R.string.play);
                        playerIsPlaying = false;
                    }
                });
            }

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
        playButton = (ImageButton) findViewById(R.id.buttonPlay);
        buttonText = (TextView) findViewById(R.id.textViewButton);
        backButton = (Button) findViewById(R.id.backButton);
        stopButton = (Button) findViewById(R.id.stopButton);
    }

    /**
     * Start playing song (managed by Spotify library)
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);
            if (response.getType() == AuthenticationResponse.Type.TOKEN) {
                Config playerConfig = new Config(this, response.getAccessToken(), CLIENT_ID);
                Spotify.getPlayer(playerConfig, this, new SpotifyPlayer.InitializationObserver() {

                    @Override
                    public void onInitialized(SpotifyPlayer spotifyPlayer) {
                        mPlayer = spotifyPlayer;
                        mPlayer.addConnectionStateCallback(DetailsActivity.this);
                        mPlayer.addNotificationCallback(DetailsActivity.this);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.e("MainActivity", "Could not initialize player: " + throwable.getMessage());
                    }
                });
            }
        }
    }



    /*The following are methods from the Spotify SDK, handling the playback of the songs.*/
    @Override
    protected void onDestroy() {
        // VERY IMPORTANT! This must always be called or else you will leak resources
        Spotify.destroyPlayer(this);
        super.onDestroy();
    }

    @Override
    public void onPlaybackEvent(PlayerEvent playerEvent) {
        Log.d("MainActivity", "Playback event received: " + playerEvent.name());
        switch (playerEvent) {
            // Handle event type as necessary
            default:
                break;
        }
    }

    @Override
    public void onPlaybackError(Error error) {
        Log.d("MainActivity", "Playback error received: " + error.name());
        switch (error) {
            // Handle error type as necessary
            default:
                break;
        }
    }

    @Override
    public void onLoggedIn() {
        Log.d("MainActivity", "User logged in");

        mPlayer.playUri(null, uri, 0, 0);
    }

    @Override
    public void onLoggedOut() {
        Log.d("MainActivity", "User logged out");
    }

    @Override
    public void onLoginFailed(Error error) {
        Log.d("MainActivity", "Login failed");
    }

    @Override
    public void onTemporaryError() {
        Log.d("MainActivity", "Temporary error occurred");
    }

    @Override
    public void onConnectionMessage(String message) {
        Log.d("MainActivity", "Received connection message: " + message);
    }

}
