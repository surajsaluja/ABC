package com.example.hp.vf;

/**
 * Created by suraj on 15-08-2018.
 */
public class GeneralItem extends ListItem {
    private Gst pojoOfJsonArray;

    public Gst getPojoOfJsonArray() {
        return pojoOfJsonArray;
    }

    public void setPojoOfJsonArray(Gst pojoOfJsonArray) {
        this.pojoOfJsonArray = pojoOfJsonArray;
    }

    @Override
    public int getType() {
        return TYPE_GENERAL;
    }


}
