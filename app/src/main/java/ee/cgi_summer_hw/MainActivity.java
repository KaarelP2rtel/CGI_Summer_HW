package ee.cgi_summer_hw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import Data.ContentProvider;

public class MainActivity extends AppCompatActivity {

    ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView  = (ListView) findViewById(R.id.mainListView);

        ContentProvider contentProvider = new ContentProvider();

        String[] list = new String[10];
        for(int i=0;i<10;i++){
            list[i]=contentProvider.getMovieList().get(i).toString();
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        mListView.setAdapter(adapter);
    }
}
