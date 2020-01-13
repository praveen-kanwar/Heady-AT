package com.heady.test.data.modules.products.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmProductModel extends RealmObject {
    @PrimaryKey
    public Integer id;
    public String name;
    public String dateAdded;
    public RealmTaxModel tax;
    public RealmList<RealmVariantModel> variants = new RealmList<>();
    public Integer viewCount = -1;
    public Integer orderCount = -1;
    public Integer shares = -1;
}
