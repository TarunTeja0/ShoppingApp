package com.example.shoppingapp.Domain;

public class CategoryDomain {
    private  String categoryTitle;
    private String categoryPic;

    public CategoryDomain(String categoryTitle, String categoryPic) {
        this.categoryTitle = categoryTitle;
        this.categoryPic = categoryPic;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getCategoryPic() {
        return categoryPic;
    }

    public void setCategoryPic(String categoryPic) {
        this.categoryPic = categoryPic;
    }
}
