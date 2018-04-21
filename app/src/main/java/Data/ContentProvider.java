package Data;

import java.io.Serializable;
import java.util.List;

import Models.Category;
import Models.CategoryList;
import Models.Movie;
import Models.MovieList;

/**
 * Created by kaarel on 20.04.18.
 */

public class ContentProvider implements Serializable {


    private MovieList movieList;
    private CategoryList categoryList;

    public ContentProvider() {

        categoryList=new CategoryList();
        movieList = new MovieList();

        for (int i = 0; i < 10; i++) {


            Category c = new Category();
            c.setCategoryId(i);
            c.setName("Category "+Integer.toString(i));
            categoryList.add(c);

            Movie m = new Movie();
            m.setMovieId(i);
            m.setTitle("Movie "+Integer.toString(i));
            m.setDescription("Very long and specific description of the film. " +
                    "This film definitely does not exist. This is the "+Integer.toString(i)+
            " by the director John Madden");
            m.setCategoryId(i);

            movieList.add(m);



        }
    }

    public MovieList getMovieList() {

        return movieList;
    }

    public void setMovieList(MovieList movieList) {
        this.movieList = movieList;
    }

    public CategoryList getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(CategoryList categoryList) {
        this.categoryList = categoryList;
    }


}
