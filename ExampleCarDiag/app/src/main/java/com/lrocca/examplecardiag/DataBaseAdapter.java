package com.lrocca.examplecardiag;

import com.lrocca.examplecardiag.persistence.DataBaseService;

/**
 * Created by lrocca on 20/07/2017.
 */
public class DataBaseAdapter {

    private static DataBaseService database;

    public static DataBaseService getDatabase() {
        return database;
    }

    public static void setDatabase(DataBaseService database) {
        DataBaseAdapter.database = database;
    }
}
