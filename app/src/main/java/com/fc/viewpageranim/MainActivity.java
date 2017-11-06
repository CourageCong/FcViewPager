package com.fc.viewpageranim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.fc.scrollviewpagel.interf.ViewPagerItemClick;
import com.fc.scrollviewpagel.widget.FcViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import static com.fc.viewpageranim.R.id.viewpager;

public class MainActivity extends AppCompatActivity {

    private FcViewPager mViewPager;
    private List<Integer> imgIds;
    private LinearLayout mLinearLayout;
    private Timer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (FcViewPager) findViewById(viewpager);
        mLinearLayout = (LinearLayout) findViewById(R.id.activity_main);

        initImageIds();

        mViewPager.setDatas(imgIds, new ViewPagerItemClick() {
            @Override
            public void onItemClick(int position, View view) {
                Toast.makeText(MainActivity.this, ""+position, Toast.LENGTH_SHORT).show();
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


}
