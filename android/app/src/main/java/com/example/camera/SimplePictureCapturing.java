package com.example.camera;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.Surface;

import java.util.TreeMap;

public class SimplePictureCapturing implements PictureCapturingListener {
    private static final String TAG = SimplePictureCapturing.class.getSimpleName();
    private String[] ss = null;

    SimplePictureCapturing(String[] ss) {
        this.ss = ss;
    }

    /**
     * We've finished taking pictures from all phone's cameras
     */
    @Override
    public void onDoneCapturingAllPhotos(TreeMap<String, byte[]> picturesTaken) {
        if (picturesTaken != null && !picturesTaken.isEmpty()) {
            Log.d(TAG, "Done capturing all photos!");
            return;
        }
        Log.d(TAG, "No camera detected!");
    }

    /**
     * Displaying the pictures taken.
     */
    @Override
    public void onCaptureDone(String pictureUrl, byte[] pictureData) {
        if (pictureData != null && pictureUrl != null) {
//            activity.runOnUiThread(() -> {
//                final Bitmap bitmap = BitmapFactory.decodeByteArray(pictureData, 0, pictureData.length);
//            });
            Log.d(TAG, "Picture saved to " + pictureUrl);
        }
    }

    public static int startCapturing(Context context, String[] ss) {
        SimplePictureCapturing simplePictureCapturing = new SimplePictureCapturing(ss);
        PictureCapturingAbstract pictureCapturing = PictureCapturingImpl.getInstance((Activity) context);
        pictureCapturing.startCapturing(simplePictureCapturing);
        return 1;
    }
}
