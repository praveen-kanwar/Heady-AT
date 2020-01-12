package com.heady.test.data.modules.categories.models;


import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmCategoryModel extends RealmObject {
    @PrimaryKey
    public Integer id;
    public String name;
    public RealmList<Integer> childCategories = new RealmList<>();
}