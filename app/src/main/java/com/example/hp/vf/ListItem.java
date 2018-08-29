package com.example.hp.vf;

/**
 * Created by suraj on 15-08-2018.
 */
public abstract class ListItem {

    public static final int TYPE_DATE = 0;
    public static final int TYPE_GENERAL = 1;

    abstract public int getType();
}
