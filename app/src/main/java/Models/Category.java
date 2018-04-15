package Models;

import java.io.Serializable;

/**
 * Created by kaarel on 15.04.18.
 */

public class Category implements Serializable {
    private int categoryId;
    private String name;

    public Category() {
    }


    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
