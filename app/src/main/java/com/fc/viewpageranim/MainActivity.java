package com.fc.viewpageranim;

import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.fc.scrollviewpagel.anim.Roll3DAnimation;
import com.fc.scrollviewpagel.interf.ViewPagerItemClick;
import com.fc.scrollviewpagel.widget.FcViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import static android.R.attr.animation;
import static com.fc.viewpageranim.R.id.viewpager;

public class MainActivity extends AppCompatActivity {

    private FcViewPager mViewPager;
    private List<Integer> imgIds;
    private LinearLayout mLinearLayout;
    private Timer mTimer;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (FcViewPager) findViewById(viewpager);
        mLinearLayout = (LinearLayout) findViewById(R.id.activity_main);
        mImageView = (ImageView) findViewById(R.id.img);
        initImageIds();

        mViewPager.setDatas(imgIds, new ViewPagerItemClick() {
            @Override
            public void onItemClick(int position, View view) {
                Toast.makeText(MainActivity.this, ""+position, Toast.LENGTH_SHORT).show();
            }
        });
//        initImg();
    }

    private void initImg() {
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int centerX = v.getWidth() / 2;
                int centerY = v.getHeight() / 2;
                Roll3DAnimation animation = new Roll3DAnimation(MainActivity.this,0, 360, centerX, centerY, 0f, true);
                animation.setDuration(5000);
                animation.setFillAfter(true);
                animation.setInterpolator(new LinearInterpolator());
                v.startAnimation(animation);
            }
        });
    }

    private void initImageIds() {
        imgIds = new ArrayList<>();
        imgIds.add(R.drawable.b9);
        imgIds.add(R.drawable.b9);
        imgIds.add(R.drawable.b9);
        imgIds.add(R.drawable.b9);
        imgIds.add(R.drawable.b9);

    }

    private static final String TAG = "MainActivity";


    public void start(View view) {
        /*Matrix matrix = new Matrix();
        matrix.preRotate(30);

        Log.e(TAG, "start: "+matrix.toString());*/

    }
}
