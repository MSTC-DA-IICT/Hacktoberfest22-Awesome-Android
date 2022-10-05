package com.example.news_app;

public class Categories_Modal {
    private String category;
    private String categoryImageURL;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryImageURL() {
        return categoryImageURL;
    }

    public void setCategoryImageURL(String categoryImageURL) {
        this.categoryImageURL = categoryImageURL;
    }

    public Categories_Modal(String category, String categoryImageURL) {
        this.category = category;
        this.categoryImageURL = categoryImageURL;
    }
}
