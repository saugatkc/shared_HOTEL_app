package com.saugat.bagpacker.model;

public class Feature {
    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    private String _id;
    private String feature;
}
