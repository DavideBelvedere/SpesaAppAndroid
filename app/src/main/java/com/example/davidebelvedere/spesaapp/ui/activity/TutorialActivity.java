package com.example.davidebelvedere.spesaapp.ui.activity;

import android.content.Intent;
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
import com.example.davidebelvedere.spesaapp.data.MainSingleton;
import com.example.davidebelvedere.spesaapp.logic.DBUserManager;
import com.example.davidebelvedere.spesaapp.logic.DBUtility;
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
                if (viewPager.getCurrentItem() == 0) {
                    viewPager.setCurrentItem(1);
                } else if (viewPager.getCurrentItem() == 1) {
                    viewPager.setCurrentItem(2);
                }

            }
        });
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTutorialAlreadySeen();
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                createDots(position);

                if (viewPager.getCurrentItem() == 0 || viewPager.getCurrentItem() == 1) {
                    nextButton.setText("Next");
                    nextButton.setOnClickListener(null);
                    nextButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (viewPager.getCurrentItem() == 0) {
                                viewPager.setCurrentItem(1);
                            } else if (viewPager.getCurrentItem() == 1) {
                                viewPager.setCurrentItem(2);
                            }

                        }
                    });
                } else {
                    nextButton.setText("Finish");
                    nextButton.setOnClickListener(null);
                    nextButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                           setTutorialAlreadySeen();
                        }
                    });
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void createDots(int current_position) {
        if (Dots_Layout != null) {
            Dots_Layout.removeAllViews();

            dots = new ImageView[layout.length];

            for (int i = 0; i < layout.length; i++) {
                dots[i] = new ImageView(this);
                if (i == current_position) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_dots));
                } else {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.default_dots));
                }

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(20, 0, 20, 0);

                Dots_Layout.addView(dots[i], params);
            }
        }
    }
    private void setTutorialAlreadySeen(){
        DBUtility.getDBUserManager().open();
        DBUtility.getDBUserManager().updateUser(MainSingleton.getCurrentUser().getUsername(),MainSingleton.getCurrentUser().getEmail(),MainSingleton.getCurrentUser().getName(),MainSingleton.getCurrentUser().getSurname(),"",1,"");
        DBUtility.getDBUserManager().close();
        Intent intent = new Intent(TutorialActivity.this, UserListActivity.class);
        startActivity(intent);
        finish();
    }
}
