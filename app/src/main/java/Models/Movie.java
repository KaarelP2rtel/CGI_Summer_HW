package Models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by kaarel on 15.04.18.
 */

public class Movie implements Serializable {
    private int movieId;
    private String title;
    private String description;
    private Date year;

    private int categoryId;


    public Movie() {
    }




    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




}
