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

        TextView mTitleText = rowView.findViewById(R.id.rowTitle);
        mTitleText.setText(m.getTitle());

        TextView mRatingText = rowView.findViewById(R.id.rowRating);
        mRatingText.setText(Integer.toString(m.getRating())+"/5");

        TextView mYearText = rowView.findViewById(R.id.rowYear);
        mYearText.setText(m.getYear());

        TextView mCategorytext = rowView.findViewById(R.id.rowCategory);
        mCategorytext.setText(m.getCategory().getName());




        return rowView;
    }
}
