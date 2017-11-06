package com.fc.scrollviewpagel.transformer;

import android.support.v4.view.ViewPager;
import android.view.View;

import javax.xml.transform.Transformer;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public class ScaleTransformer implements ViewPager.PageTransformer {

    private float scaleSize = 0.9f;
    private float alphaSzie = 0.7f;

    public ScaleTransformer() {
    }

    public ScaleTransformer(float scaleSize) {
        this.scaleSize = scaleSize;
    }

    @Override
    public void transformPage(View page, float position) {
        int width = page.getWidth();
        int translationX = (int) (width * (1f - scaleSize) * 0.5);

        if (position < -1) {
            page.setScaleX(scaleSize);
            page.setScaleY(scaleSize);
            page.setTranslationX(translationX);
            page.setAlpha(alphaSzie);
        } else if (position <= 0) {
            float size = scaleSize + (1.0f - scaleSize) * (1 - Math.abs(position));
            page.setScaleX(size);
            page.setScaleY(size);
            page.setTranslationX(translationX *Math.abs(position));
            page.setAlpha(alphaSzie+(1-alphaSzie)*(1-Math.abs(position)));
        } else if(position <=1){
            float size = scaleSize + (1.0f - scaleSize) * (1 - Math.abs(position));
            page.setScaleX(size);
            page.setScaleY(size);
            page.setTranslationX(-translationX*position);
            page.setAlpha(alphaSzie+(1-alphaSzie)*(1-Math.abs(position)));
        }else {
            page.setScaleX(scaleSize);
            page.setScaleY(scaleSize);
            page.setTranslationX(-translationX);
            page.setAlpha(alphaSzie);
        }

    }
}
