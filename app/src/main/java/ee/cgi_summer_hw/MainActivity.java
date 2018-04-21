package ee.cgi_summer_hw;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import Data.ContentProvider;
import Data.MovieListAdapter;
import Models.Movie;
import Models.MovieList;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();
    ListView mListView;
    SwipeRefreshLayout mRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.mainListView);
        mRefresh = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);

        ContentProvider contentProvider = new ContentProvider();
       final Context context = this;

        final MovieListAdapter adapter = new MovieListAdapter(this, contentProvider.getMovieList());
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Movie m = adapter.getItem(position);
                Intent i = new Intent(context, DetailActivity.class);

                i.putExtra("movie",m);
                startActivity(i);

            }
        });



        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d(TAG, "onRefresh: ");
                mRefresh.setRefreshing(false);
            }
        });


    }


}
