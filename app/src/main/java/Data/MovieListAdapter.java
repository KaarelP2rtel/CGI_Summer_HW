package Data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import Models.Movie;
import Models.MovieList;
import ee.cgi_summer_hw.R;

/**
 * Created by kaarel on 21.04.18.
 */

public class MovieListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private MovieList mDataSource;

    public MovieListAdapter(Context context, MovieList items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }


    @Override
    public Movie getItem(int position) {
        return mDataSource.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get view for row item
        View rowView = mInflater.inflate(R.layout.list_item_movie, parent, false);

        Movie m = getItem(position);

        TextView titleText = (TextView) rowView.findViewById(R.id.rowTitle);
        titleText.setText(m.getTitle());

        TextView descriptionText = (TextView) rowView.findViewById(R.id.rowDescription);
        descriptionText.setText(m.getDescription());

        TextView yearText = (TextView) rowView.findViewById(R.id.rowYear);
        yearText.setText(m.getYear());


        return rowView;
    }
}
