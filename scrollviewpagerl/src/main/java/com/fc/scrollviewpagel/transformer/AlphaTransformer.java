package com.fc.scrollviewpagel.transformer;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public class AlphaTransformer implements ViewPager.PageTransformer{

    private static final float MIN_SCALE = 0.75f;

    @Override
    public void transformPage(View view, float position) {

        int pageWidth = view.getWidth();

        if (position < -1) {
            view.setAlpha(0);
        } else if (position <= 0) {
            // Use the default slide transition when moving to the left page
            view.setAlpha(1);
            view.setTranslationX(0);
            view.setScaleX(1);
            view.setScaleY(1);
        }else if(position <= 1){
            view.setAlpha(1 - position);
            // Counteract the default slide transition
            view.setTranslationX(pageWidth * -position);
            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) * (1 - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
        }else{
            view.setAlpha(0);
        }

    }
}
