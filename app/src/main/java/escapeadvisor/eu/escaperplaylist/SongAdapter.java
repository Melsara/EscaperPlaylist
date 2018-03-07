package escapeadvisor.eu.escaperplaylist;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/**
 * Created by Sara on 04/03/2018.
 */

public class SongAdapter extends ArrayAdapter<Song> {

    public SongAdapter (Activity context, ArrayList<Song> words){
        super(context, 0, words);
    }

    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent) {
        Song currentSong = getItem(position);

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.grid_item, parent, false);
        }

        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title_textView);
        titleTextView.setText(currentSong.getSongTitle());
        TextView artistTextView = (TextView) listItemView.findViewById(R.id.artist_textView);
        artistTextView.setText(currentSong.getSongArtist());
        ImageView artImageView = (ImageView) listItemView.findViewById(R.id.art_imageView);
        artImageView.setImageResource(currentSong.getSongArt());

        return listItemView;
    }
}
