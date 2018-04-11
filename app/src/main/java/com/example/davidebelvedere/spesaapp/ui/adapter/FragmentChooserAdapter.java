package com.example.davidebelvedere.spesaapp.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.ImageView;

import com.example.davidebelvedere.spesaapp.R;
import com.example.davidebelvedere.spesaapp.ui.fragment.FirstTutorialFragment;
import com.example.davidebelvedere.spesaapp.ui.fragment.SecondTutorialFragment;
import com.example.davidebelvedere.spesaapp.ui.fragment.ThirdTutorialFragment;

public class FragmentChooserAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public FragmentChooserAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }


    @Override
    public Fragment getItem(int position) {


        if (position == 0) {
            return new FirstTutorialFragment();
        } else if (position == 1) {
            return new SecondTutorialFragment();
        } else if (position == 2) {
            return new ThirdTutorialFragment();
        }
        return null;

    }

    @Override
    public int getCount() {
        return 3;
    }
}
