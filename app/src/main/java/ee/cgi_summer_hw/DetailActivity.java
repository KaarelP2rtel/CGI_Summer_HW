package ee.cgi_summer_hw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import Models.Movie;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Movie movie =(Movie) getIntent().getSerializableExtra("movie");

        TextView mTitleText= findViewById(R.id.infoTitle);
        mTitleText.setText(movie.getTitle());

        TextView mDescription= findViewById(R.id.infoDescription);
        mDescription.setText(movie.getDescription());

        TextView mYearText= findViewById(R.id.infoYear);
        mYearText.setText(movie.getYear());


    }
}
