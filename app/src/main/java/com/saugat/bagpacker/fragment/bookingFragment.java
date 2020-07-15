package com.saugat.bagpacker.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.saugat.bagpacker.R;
import com.saugat.bagpacker.adapter.SectionPagerAdapter;


public class bookingFragment extends Fragment {


    View myFragment;

    ViewPager viewPager;
    TabLayout tabLayout;
    public bookingFragment(){
        //
    }

    public static bookingFragment getInstance()    {
        return new bookingFragment();}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myFragment = inflater.inflate(R.layout.booking, container, false);

            viewPager = myFragment.findViewById(R.id.viewPager);
            tabLayout = myFragment.findViewById(R.id.tabLayout);

        return myFragment;
    }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            setUpViewPager(viewPager);
            tabLayout.setupWithViewPager(viewPager);

            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });

        }

        private void setUpViewPager(ViewPager viewPager) {
            SectionPagerAdapter adapter = new SectionPagerAdapter(getChildFragmentManager());

            adapter.addFragment(new BookedFragment(), "Booked");
            adapter.addFragment(new CancelFragment(), "Canceled");
            adapter.addFragment(new CompletedFragment(), "Completed");


            viewPager.setAdapter(adapter);
        }
}
