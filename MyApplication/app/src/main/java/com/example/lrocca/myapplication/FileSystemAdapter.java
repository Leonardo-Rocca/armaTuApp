package com.example.lrocca.myapplication;

import com.example.lrocca.myapplication.Activities.Login;

/**
 * Created by lrocca on 03/05/2017.
 */
public class FileSystemAdapter {

    private static FileSystem FS;

    public static FileSystem getFS() {
        return FS;
    }

    public static void setContext(Login context) {
        FS=new FileSystem(context, "administracion", null, 1);
    }
}
