package ee.cgi_summer_hw;

import android.app.IntentService;

import android.content.Intent;
import android.support.annotation.Nullable;

import Data.ContentProvider;

/**
 * Created by kaarel on 22.04.18.
 */

public class ContentService extends IntentService {


    public ContentService() {
        super("ContentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        ContentProvider provider = (ContentProvider) intent.getSerializableExtra("provider");
        provider.loadData();
        Intent i = new Intent();

        if (intent.getAction().equals(".FETCH_ALL")) {
            i.putExtra("result", provider.getMovieList());
            i.setAction(".ALL_DONE");
            sendBroadcast(i);

        } else if (intent.getAction().equals(".FETCH_ONE")) {
            int id = intent.getIntExtra("id", 0);
            i.putExtra("result", provider.getMovieById(id));
            i.setAction(".ONE_DONE");
            sendBroadcast(i);
        }


    }
}