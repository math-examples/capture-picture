package com.example.camera;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.TreeMap;

public class MainActivity extends Activity {

    private static final String[] requiredPermissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA,};
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermissions(requiredPermissions, MY_PERMISSIONS_REQUEST_ACCESS_CODE);
        final Button btn = (Button) findViewById(R.id.startCaptureBtn);
        // getting instance of the Service from PictureCapturingServiceImpl

        btn.setOnClickListener(v -> {
            SimplePictureCapturing.startCapturing(MainActivity.this, null);
            showToast("Starting capture!");
        });
    }

    private void showToast(final String text) {
        runOnUiThread(() -> Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_CODE: {
                if (!(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    showToast("requestPermissions error!");
                }
            }
        }
    }
}

