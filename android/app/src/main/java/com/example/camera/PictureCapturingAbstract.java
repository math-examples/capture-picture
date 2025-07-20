package com.example.camera;

import android.app.Activity;
import android.content.Context;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.util.SparseIntArray;
import android.view.Surface;

public abstract class PictureCapturingAbstract {

    private static final SparseIntArray ORIENTATIONS = new SparseIntArray();

    // see https://medium.com/@kenodoggy/solving-image-rotation-on-android-using-camera2-api-7b3ed3518ab6
    static {
        ORIENTATIONS.append(Surface.ROTATION_0, 90);
        ORIENTATIONS.append(Surface.ROTATION_90, 0);
        ORIENTATIONS.append(Surface.ROTATION_180, 270);
        ORIENTATIONS.append(Surface.ROTATION_270, 180);
    }

    private final Activity activity;
    final Context context;
    final CameraManager manager;

    /***
     * constructor.
     *
     * @param activity the activity used to get display manager and the application context
     */
    PictureCapturingAbstract(final Activity activity) {
        this.activity = activity;
        this.context = activity.getApplicationContext();
        this.manager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
    }

    /***
     * @return  orientation
     */
    int getOrientation(CameraCharacteristics characteristics) {
        final int deviceRotation = this.activity.getWindowManager().getDefaultDisplay().getRotation();
        int surfaceRotation = ORIENTATIONS.get(deviceRotation);
        //        return surfaceRotation;
        int sensorOrientation = 0;
        {
            Object sensorOrientation_ = characteristics.get(CameraCharacteristics.SENSOR_ORIENTATION);
            if(sensorOrientation_!=null) sensorOrientation = (int) sensorOrientation_;
        }
        // return jpegOrientation
        return (surfaceRotation + sensorOrientation + 270) % 360;
    }


    /**
     * starts pictures capturing process.
     *
     * @param listener picture capturing listener
     */
    public abstract void startCapturing(final PictureCapturingListener listener);
}
