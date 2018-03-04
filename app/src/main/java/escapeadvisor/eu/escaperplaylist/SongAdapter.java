package escapeadvisor.eu.escaperplaylist;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
        Song currentWord = getItem(position);

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.grid_item, parent, false);
        }

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.title_textView);
        miwokTextView.setText(currentWord.getSongTitle());
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.artist_textView);
        defaultTextView.setText(currentWord.getSongArtist());

        return listItemView;
    }
}
