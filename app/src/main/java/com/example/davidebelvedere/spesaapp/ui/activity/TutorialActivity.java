package com.example.davidebelvedere.spesaapp.ui.activity;

import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.davidebelvedere.spesaapp.R;
import com.example.davidebelvedere.spesaapp.ui.adapter.FragmentChooserAdapter;

public class TutorialActivity extends AppCompatActivity {

    private ViewPager viewPager;

    private int[] layout = {R.layout.first_tutorial, R.layout.second_tutorial, R.layout.third_tutorial};

    private LinearLayout Dots_Layout;
    private ImageView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        Dots_Layout = (LinearLayout) findViewById(R.id.dotsLayout);

        createDots(0);

        // Find the view pager that will allow the user to swipe between fragments
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        FragmentChooserAdapter adapter = new FragmentChooserAdapter(this, getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        final Button nextButton = (Button) findViewById(R.id.next);
        final Button skipButton = (Button) findViewById(R.id.skip);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPager.getCurrentItem() == 0){
                    viewPager.setCurrentItem(1);
                }else if (viewPager.getCurrentItem() == 1) {
                    viewPager.setCurrentItem(2);
                }

            }
        });




        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                createDots(position);

                if(viewPager.getCurrentItem() == 0){
                    nextButton.setText("Next");
                }else if(viewPager.getCurrentItem() == 1){
                    nextButton.setText("Next");
                }else{
                    nextButton.setText("Finish");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void createDots(int current_position) {
        if(Dots_Layout!=null){
            Dots_Layout.removeAllViews();

            dots = new ImageView[layout.length];

            for (int i = 0; i<layout.length; i++){
                dots[i] = new ImageView(this);
                if(i==current_position){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_dots));
                }else{
                    dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.default_dots));
                }

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(5,0,5,0);

                Dots_Layout.addView(dots[i],params);
            }
        }
    }
}
