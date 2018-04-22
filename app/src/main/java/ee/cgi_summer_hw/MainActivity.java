package ee.cgi_summer_hw;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import Data.ContentProvider;
import Data.MovieListAdapter;
import Models.Movie;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();
    ListView mListView;
    SwipeRefreshLayout mRefresh;
    ContentProvider contentProvider;
    Context context;
    MovieListAdapter adapter;
    BroadcastReceiver mReceiver;
    IntentFilter receiverIntentFilter;
    ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.mainListView);
        mRefresh = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        contentProvider = new ContentProvider();
        context = this;
        mProgressBar = (ProgressBar) findViewById(R.id.mainProgressBar);


        mProgressBar.setVisibility(View.VISIBLE);
        fetchAll();


        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                if (intent.getAction().equals(".ALL_DONE")) {
                    ArrayList<Movie> ml = (ArrayList<Movie>) intent.getSerializableExtra("result");
                    adapter = new MovieListAdapter(context, ml);
                    mListView.setAdapter(adapter);
                    mRefresh.setRefreshing(false);


                } else if (intent.getAction().equals(".ONE_DONE")) {
                    Movie m = (Movie) intent.getSerializableExtra("result");
                    Intent i = new Intent(context, DetailActivity.class);

                    i.putExtra("movie", m);
                    startActivity(i);
                }
                mProgressBar.setVisibility(View.INVISIBLE);

            }
        };
        receiverIntentFilter = new IntentFilter();
        receiverIntentFilter.addAction(".ALL_DONE");
        receiverIntentFilter.addAction(".ONE_DONE");
        this.registerReceiver(mReceiver, receiverIntentFilter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                /*
                * At this point we have to ask ourselves, why we are not using the object
                * that is already in memory.
                * */
                fetchOne(adapter.getItem(position).getMovieId());

            }
        });


        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchAll();
            }
        });


    }

    private void fetchAll() {


        Intent i = new Intent(this, ContentService.class);
        i.setAction(".FETCH_ALL");
        i.putExtra("provider", contentProvider);
        startService(i);
    }

    private void fetchOne(int id) {
        mProgressBar.setVisibility(View.VISIBLE);
        Intent i = new Intent(this, ContentService.class);
        i.setAction(".FETCH_ONE");
        i.putExtra("provider", contentProvider);
        i.putExtra("id", id);
        startService(i);

    }

    @Override
    protected void onPause() {
        super.onPause();
        this.unregisterReceiver(mReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mProgressBar.setVisibility(View.INVISIBLE);
        this.registerReceiver(mReceiver, receiverIntentFilter);
    }
}
