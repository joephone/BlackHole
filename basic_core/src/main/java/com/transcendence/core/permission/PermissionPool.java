package com.transcendence.core.permission;


import android.Manifest;

/**
 * @author Joephone on 2020/5/25 19:32
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public class PermissionPool {

    public static final String[] STORAGE = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };


    public static final String[] CAMERA = new String[]{
            Manifest.permission.CAMERA
    };


    public static final String[] LOCATION = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
}
