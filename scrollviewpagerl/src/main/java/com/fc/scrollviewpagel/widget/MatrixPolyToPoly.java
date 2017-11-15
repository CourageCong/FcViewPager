package com.fc.scrollviewpagel.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;

import com.fc.scrollviewpagel.R;

import java.util.Arrays;

/**
 * 斜切图片，变换四边形
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public class MatrixPolyToPoly extends View{

    private Matrix mMatrix;
    private Bitmap mBitmap;

    public MatrixPolyToPoly(Context context) {
        this(context,null);
    }

    public MatrixPolyToPoly(Context context, AttributeSet attrs) {
        super(context, attrs);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.b8);
        initMatrix();
    }

    private void initMatrix() {
        mMatrix = new Matrix();
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        float[] src = new float[]{0, 0,
                width, 0,
                width, height,
                0, height};
        float[] dst = new float[]{0, 0,
                width, height * 0.2f,
                width, height * 0.8f,
                0, height};
        mMatrix.setPolyToPoly(src, 0, dst, 0, src.length / 2);
        Log.e(TAG, "原始: "+mMatrix.toString());
        mMatrix.postScale(0.2f, 0.2f);
        Log.e(TAG, "Scale: "+mMatrix.toString());
        mMatrix.postTranslate(50, 50);
        Log.e(TAG, "Translate: "+mMatrix.toString());


    }

    private static final String TAG = "MatrixPolyToPoly";


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, mMatrix, null);
        int[] loaction = new int[2];
        this.getLocationOnScreen(loaction);
        Log.e(TAG, "onDraw: "+ Arrays.toString(loaction));

        Camera c = new Camera();
        c.translate(0, 100, 0);
        Matrix matrix = new Matrix();
        c.getMatrix(matrix);
        Log.e(TAG, "matrix0: "+matrix);
        matrix.postTranslate(0, 100);
        Log.e(TAG, "matrix1: "+matrix);
    }
}
