package com.fc.scrollviewpagel.anim;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public class Roll3DAnimation extends Animation{

    private final float mFromDegrees;
    private final float mToDegrees;
    private final float mCenterX;
    private final float mCenterY;
    private final float mDepthZ;
    private final boolean mReverse;
    private Camera mCamera;
    float scale = 1;    //  像素密度

    /**
     * 创建一个绕y轴旋转的3D动画效果，旋转过程中具有深度调节，可以指定旋转中心。
     *
     * @param mFromDegrees	起始时角度
     * @param mToDegrees 	结束时角度
     * @param mCenterX 		旋转中心x坐标
     * @param mCenterY 		旋转中心y坐标
     * @param mDepthZ		最远到达的z轴坐标
     * @param reverse 		true 表示由从0到depthZ，false相反
     */
    public Roll3DAnimation(Context context ,float mFromDegrees,float mToDegrees
            ,float mCenterX,float mCenterY,float mDepthZ,boolean reverse) {
        this.mFromDegrees = mFromDegrees;
        this.mToDegrees = mToDegrees;
        this.mCenterX = mCenterX;
        this.mCenterY = mCenterY;
        this.mDepthZ = mDepthZ;
        mReverse = reverse;
        scale = context.getResources().getDisplayMetrics().density;
    }

    /*public Roll3DAnimation(Context context, AttributeSet attrs) {
        super(context, attrs);
    }*/

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        mCamera = new Camera();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {

        final float fromDegrees = mFromDegrees;
        float degrees = fromDegrees + ((mToDegrees - fromDegrees) * interpolatedTime);
        final float centerX = mCenterX;
        final float centerY = mCenterY;
        final Camera camera = mCamera;
        final Matrix matrix = t.getMatrix();//重点
        camera.save();

        if(mReverse){
            camera.translate(0,0,mDepthZ*interpolatedTime);
        }else{
            camera.translate(0,0,mDepthZ*(1.0f-interpolatedTime));
        }

        camera.rotateY(degrees);
        camera.getMatrix(matrix);//重点  把camera中的矩阵赋值给matrix
        camera.restore();

        float[] f = new float[9];
        matrix.getValues(f);
        f[6] = f[6] / scale;
        f[7] = f[7] / scale;
        matrix.setValues(f);

        matrix.postTranslate(centerX, centerY);
        matrix.preTranslate(-centerX, -centerY);
//        matrix.preTranslate(-centerX, centerY);
//        matrix.postTranslate(centerX, -centerY);
    }
}
