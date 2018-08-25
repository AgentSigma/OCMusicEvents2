package edu.orangecoastcollege.cs273.ocmusicevents2;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by dreyna3 on 9/26/2017.
 */

public class EventListAdapter extends ArrayAdapter<MusicEvent>
{
    private Context mContext;
    private int mResource;
    private List<MusicEvent> mAllEventsList;

    // context = Activity that uses adapter (EventListActivity)
    // resource = layout file to inflate (R.layout.music_event_list_item)
    // objects = List<MusicEvent>
    public EventListAdapter(@NonNull Context context, @LayoutRes int resource,
                            @NonNull List<MusicEvent> allMusicEvents)
    {
        super(context, resource, allMusicEvents);
        mContext = context;
        mResource = resource;
        mAllEventsList = allMusicEvents;
    }

    // TODO: Override method call getView
    // This is necessary when implementing a list adapter
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Use "Inflater" to inflate the custom layout we made (R.layout.music_event_list_item)
        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        // Inflating custom layout for one single item in the list (repeats multiple times)
        View listItemView = inflater.inflate(mResource, null);

        ImageView listItemImageView = (ImageView) listItemView.findViewById(R.id.listItemImageView);
        TextView listTitleTextView = (TextView) listItemView.findViewById(R.id.listTitleTextView);
        TextView listDateTextView = (TextView) listItemView.findViewById(R.id.listDateTextView);

        MusicEvent selectedEvent = mAllEventsList.get(position);
        listTitleTextView.setText(selectedEvent.getTitle());
        listDateTextView.setText(selectedEvent.getDate());

        // Use an asset manager to retrieve appropriate image
        AssetManager am = mContext.getAssets();

        try {
            InputStream stream = am.open(selectedEvent.getImageName());
            Drawable image = Drawable.createFromStream(stream, selectedEvent.getTitle());
            listItemImageView.setImageDrawable(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listItemView;
    }
}
