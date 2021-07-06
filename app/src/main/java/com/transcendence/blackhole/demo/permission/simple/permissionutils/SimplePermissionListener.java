package com.transcendence.blackhole.demo.permission.simple.permissionutils;

import java.util.List;

public interface SimplePermissionListener {
    void onGranted();

    void onDenied(List<String> deniedPermission);

    void onShouldShowRationale(List<String> deniedPermission);
}
