package com.heady.test.data.modules.subcategories.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmSubCategoryModel extends RealmObject {
    @PrimaryKey
    public Integer id;
    public String name;
    public RealmList<Integer> products = new RealmList<>();
}