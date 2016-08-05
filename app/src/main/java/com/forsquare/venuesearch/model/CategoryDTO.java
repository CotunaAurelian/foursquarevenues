package com.forsquare.venuesearch.model;

/**
 * DTO object for a category displayed in the categories {@link android.support.v7.widget.RecyclerView}
 * Created by Wraith
 */

public class CategoryDTO {

    private int imageResId;

    private String categoryName;

    public CategoryDTO(int imageResId, String categoryName){
        this.imageResId = imageResId;
        this.categoryName = categoryName;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
