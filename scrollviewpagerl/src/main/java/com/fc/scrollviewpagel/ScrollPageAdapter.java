package com.fc.scrollviewpagel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fc.scrollviewpagel.interf.ViewPagerItemClick;

import java.util.List;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public class ScrollPageAdapter extends PagerAdapter {

    private List<Integer> imgIds;
    private List<View> viewList;
    private Context mContext;
    private ViewPagerItemClick mViewPagerItemClick;

    public ScrollPageAdapter(Context context, List<Integer> imgIds) {
        this(context, imgIds, null);
    }

    public ScrollPageAdapter(Context context, @NonNull List<Integer> imgIds, ViewPagerItemClick viewPagerItemClick) {
        this.mContext = context;
        this.imgIds = imgIds;
        this.mViewPagerItemClick = viewPagerItemClick;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return initView(container, position);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;// TODO: 2017-11-06 添加是否无限循环
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    private View initView(ViewGroup container, final int position) {
        final ImageView view = new ImageView(container.getContext());
        Glide.with(container.getContext()).load(imgIds.get(position % imgIds.size())).into(view);
        container.addView(view);
        ViewPager.LayoutParams params = (ViewPager.LayoutParams) view.getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        view.setLayoutParams(params);
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        if (mViewPagerItemClick != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPagerItemClick.onItemClick(position%imgIds.size(),view);
                }
            });
        }

        return view;
    }

   /* public class Builder{
        private boolean


    }*/
}
