package com.fc.scrollviewpagel.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.fc.scrollviewpagel.R;
import com.fc.scrollviewpagel.ScrollPageAdapter;
import com.fc.scrollviewpagel.interf.ViewPagerItemClick;
import com.fc.scrollviewpagel.transformer.AlphaTransformer;
import com.fc.scrollviewpagel.transformer.ScaleTransformer;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public class FcViewPager extends FrameLayout {

    private static final int DEFAUT_SCROLL_PERIOD = 2;

    private boolean autoScroll = false;//开启自动轮播
    private boolean infiniteScroll = false;//无限滑动
    private int scrollPeriod;//滚动间隔时间
    private int marginLR;//左右间距
    private int offScreenLimit;//缓存页数
    private int pageMargin;//页间距
    private int transformerType;//动画种类

    private ViewPagerItemClick mViewPagerItemClick;
    private ScrollPageAdapter mScrollPageAdapter;
    private Timer mTimer;

    private ViewPager mViewPager;

    public FcViewPager(Context context) {
        this(context, null);
    }

    public FcViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

        View view = LayoutInflater.from(context).inflate(R.layout.normal_with_margin, this);
        init(view);
        initArray(context, attrs);
        initViewPager();
    }


    /**
     * 入口 配置参数
     */
    public void setDatas(List<Integer> idList) {

        setDatas(idList, null);
    }

    public void setDatas(List<Integer> idList, ViewPagerItemClick mViewPagerItemClick) {
        if (mViewPagerItemClick != null) {
            mScrollPageAdapter = new ScrollPageAdapter(getContext(), idList, mViewPagerItemClick);
        } else {
            mScrollPageAdapter = new ScrollPageAdapter(getContext(), idList);
        }
        if (transformerType != -1) {
            switch (transformerType) {
                case 0:
                    mViewPager.setPageTransformer(false, new AlphaTransformer());
                    break;
                case 1:
                    mViewPager.setPageTransformer(false, new ScaleTransformer());
                    break;
                default:
                    mViewPager.setPageTransformer(false, new AlphaTransformer());
                    break;
            }
        }
        mViewPager.setPageMargin(pageMargin);
        mViewPager.setOffscreenPageLimit(offScreenLimit);
        mViewPager.addOnPageChangeListener(new ChangeListenter());
        mViewPager.setAdapter(mScrollPageAdapter);
        mViewPager.setCurrentItem(1000 * idList.size());
    }


    /**
     * 初始化参数
     */
    private void initArray(Context context, AttributeSet attrs) {
        TypedArray array = null;
        try {
            array = context.obtainStyledAttributes(attrs, R.styleable.FcViewPager);
            autoScroll = array.getBoolean(R.styleable.FcViewPager_auto_scroll, false);
            scrollPeriod = array.getInt(R.styleable.FcViewPager_scroll_period, DEFAUT_SCROLL_PERIOD);
            marginLR = (int) array.getDimension(R.styleable.FcViewPager_margin_left_right, 0);
            offScreenLimit = array.getInt(R.styleable.FcViewPager_off_screen_limit, 1);
            infiniteScroll = array.getBoolean(R.styleable.FcViewPager_infinite_scroll, false);
            pageMargin = (int) array.getDimension(R.styleable.FcViewPager_page_margin, 0);
            transformerType = array.getInt(R.styleable.FcViewPager_transformer_type, -1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            array.recycle();
        }

    }

    /**
     * 初始化view
     */
    private void init(View view) {

        mViewPager = view.findViewById(R.id.fc_view_pager);

        initTouch(view);
    }

    /**
     * 处理两侧超出viewpager部分的滑动事件
     */
    private void initTouch(View view) {
        view.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mViewPager.dispatchTouchEvent(event);
            }
        });
    }

    /**
     * 初始化viewpager
     */
    private void initViewPager() {
        mViewPager.setOffscreenPageLimit(offScreenLimit);

        if (mViewPager.getParent() != null) {
            ViewGroup.MarginLayoutParams params = (MarginLayoutParams) mViewPager.getLayoutParams();
            params.leftMargin = marginLR;
            params.rightMargin = marginLR;
            mViewPager.setLayoutParams(params);
        }
    }


    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus) {
            if (autoScroll) {
                autoScroll();
            }
        } else {
            stopAutoScroll();
        }
    }

    private void autoScroll() {
        if (mTimer != null) {
            return;
        }

        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mViewPager.post(new Runnable() {
                    @Override
                    public void run() {
                        int position = mViewPager.getCurrentItem();
                        mViewPager.setCurrentItem(++position);
                    }
                });
            }
        }, scrollPeriod * 1000, scrollPeriod * 1000);
    }

    private void stopAutoScroll() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    class ChangeListenter implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                // TODO: 2017-11-06 停止
                stopAutoScroll();
            } else {
                autoScroll();
            }
        }
    }


}
