package Data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import Models.Category;
import Models.Movie;

/**
 * Created by kaarel on 20.04.18.
 */

public class ContentProvider implements Serializable {


    private ArrayList<Movie> movieList;
    private ArrayList<Category> categoryList;

    public ContentProvider() {
        categoryList = new ArrayList<Category>();
        movieList = new ArrayList<Movie>();


    }

    public Movie getMovieById(int id) {
        for (Movie m : movieList) {
            if (m.getMovieId() == id) {
                includeCategory(m);
                return m;
            }
        }

        return null;
    }


    public ArrayList<Movie> getMovieList() {

        for (Movie m : movieList) {
            includeCategory(m);
        }

        return movieList;
    }

    private void includeCategory(Movie movie) {
        for (Category c : categoryList) {
            if (movie.getCategoryId() == c.getCategoryId()) {
                movie.setCategory(c);
            }
        }
    }


    public void setMovieList(ArrayList<Movie> movieList) {
        this.movieList = movieList;
    }

    public ArrayList<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(ArrayList<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public void loadData() throws IOException, JSONException {


        //Warning. Magic strings ahead.
        String movieUrl = "https://api.themoviedb.org/3/discover/" +
                "movie" +
                "?api_key=fe6cc7f97c99aa3f5e2caef8ed4d9408" +
                "&language=en-US" +
                "&sort_by=popularity.desc" +
                "&include_adult=false" +
                "&include_video=false" +
                "&page=1";
        String categoryUrl = "https://api.themoviedb.org/3/" +
                "genre/movie/list" +
                "?api_key=fe6cc7f97c99aa3f5e2caef8ed4d9408&language=en-US";

        JSONArray movieJsonArray = readJsonFromUrl(movieUrl).getJSONArray("results");
        JSONArray categoryJsonArray = readJsonFromUrl(categoryUrl).getJSONArray("genres");


        //Read Categories (genres) from TMDB
        for (int j = 0; j < categoryJsonArray.length(); j++) {
            JSONObject jsonObject = categoryJsonArray.getJSONObject(j);
            Category c = new Category();
            c.setCategoryId(jsonObject.getInt("id"));
            c.setName(jsonObject.getString("name"));
            categoryList.add(c);
        }

        //Read Movies from TMDB
        for (int i = 0; i < movieJsonArray.length(); i++) {
            JSONObject jsonObject = movieJsonArray.getJSONObject(i);
            Movie m = new Movie();

            m.setMovieId(jsonObject.getInt("id"));
            m.setDescription(jsonObject.getString("overview"));
            m.setRating((int) jsonObject.getLong("vote_average"));
            m.setTitle(jsonObject.getString("original_title"));
            m.setCategoryId(jsonObject.getJSONArray("genre_ids").getInt(0));
            m.setYear(jsonObject.getString("release_date").substring(0,4));
            movieList.add(m);
        }


    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }


    public void loadMockData() {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < 10; i++) {

            //Hard coded category movies

            Category c = new Category();
            c.setCategoryId(i);
            c.setName("Category " + Integer.toString(i));
            this.categoryList.add(c);

            Movie m = new Movie();
            m.setMovieId(i);
            m.setTitle("Movie " + Integer.toString(i));
            m.setDescription("Very long and specific description of the film. " +
                    "This film definitely does not exist. This is the number " + Integer.toString(i) +
                    " film by the director John Madden" +
                    "\n" +
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed pharetra quis orci eu malesuada. Aliquam gravida commodo orci, ut semper dolor hendrerit ut. Pellentesque ullamcorper sodales sodales. Maecenas pretium libero purus, in rhoncus lectus aliquet vitae. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Praesent risus dui, lobortis vel augue vitae, mattis venenatis lorem. Praesent cursus dui a justo porttitor, nec scelerisque ligula viverra.\n" +
                    "\n" +
                    "In vitae gravida lorem. Sed semper condimentum sapien, a pulvinar nulla rhoncus in. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis quis varius quam, nec sollicitudin metus. Curabitur ac tortor id lacus interdum sodales. Nunc rhoncus arcu eget lorem faucibus luctus. Vivamus varius lectus ante. Aliquam tempus sapien vel dolor varius, sed facilisis leo porta.\n" +
                    "\n" +
                    "In id dapibus eros. Sed consequat neque in pulvinar fringilla. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nam tempor sodales leo, a auctor eros sollicitudin sit amet. Nam semper lectus a dolor molestie posuere. Nunc placerat egestas metus, nec tincidunt tellus mollis sed. Morbi rhoncus imperdiet mi, non elementum ligula tristique vel. Integer eget ipsum aliquet, porta massa a, accumsan tortor. Vestibulum dignissim, arcu ut scelerisque imperdiet, mauris dui convallis diam, a maximus purus arcu a neque. Nam mollis nibh cursus felis imperdiet lacinia. Sed luctus nibh lacus, vitae mollis magna rutrum in. Nunc aliquet aliquet odio, vitae fringilla sapien venenatis eget. Vestibulum urna nunc, mollis id metus ut, iaculis molestie sapien. Sed vitae nisl tristique mauris condimentum tristique. Aliquam erat volutpat.\n" +
                    "\n" +
                    "Vestibulum orci ligula, ultrices at dolor in, pulvinar mattis arcu. Nullam eu dignissim lectus, pellentesque sodales risus. Nulla gravida mi vel velit pulvinar elementum. Nam non arcu a nulla lacinia consequat. Etiam hendrerit enim ligula, in accumsan lectus scelerisque vel. Praesent interdum venenatis massa, non tincidunt libero tincidunt vitae. Donec porttitor, ex id fermentum finibus, sem magna vulputate massa, in condimentum eros lorem eu massa. Vivamus elementum eros in purus facilisis, non tempor augue sollicitudin.\n" +
                    "\n" +
                    "Aliquam erat volutpat. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam iaculis posuere eros, et consectetur tellus hendrerit vitae. Maecenas ut velit sit amet lacus sodales finibus. Vestibulum eleifend, neque at blandit consectetur, nibh ante mollis nibh, quis varius odio massa id lorem. Quisque ut magna at massa sodales pulvinar. Maecenas tincidunt elit sapien, non posuere nisi faucibus non. In vulputate finibus purus vitae feugiat. Curabitur ultricies condimentum elit, vitae consectetur nisl lobortis tempus. Praesent fermentum nisi eu enim rhoncus pharetra. In dui justo, malesuada in imperdiet eget, interdum sit amet erat. Donec rhoncus dui a ullamcorper euismod. Pellentesque congue, velit non lacinia aliquam, nunc nulla mollis purus, id suscipit orci ante ut justo. Vestibulum tortor leo, pharetra sit amet ultricies quis, blandit a augue.\n" +
                    "\n" +
                    "Proin id ipsum eget dui venenatis suscipit. Sed mi mauris, semper sit amet porta id, posuere sit amet diam. Quisque fermentum turpis nec varius tempus. Proin finibus lorem vitae gravida facilisis. Integer et sapien placerat leo.\n");
            m.setCategoryId(i);
            m.setYear(Integer.toString(1995 + i));
            m.setRating(i % 5);

            this.movieList.add(m);


        }
    }
}
