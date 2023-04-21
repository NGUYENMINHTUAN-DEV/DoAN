package com.example.doanandroid.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.doanandroid.Adapter.Photo;
import com.example.doanandroid.Fragment.FeedFragment;
import com.example.doanandroid.Fragment.MessagesFragment;
import com.example.doanandroid.Fragment.MusicFragment;
import com.example.doanandroid.Fragment.NewsFragment;
import com.example.doanandroid.Fragment.NhanVienFragment;
import com.example.doanandroid.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import me.relex.circleindicator.CircleIndicator;

public class Menu extends AppCompatActivity {




    private BottomNavigationView nBottomNavigationView;
    //Global Declaration
    private SNavigationDrawer sNavigationDrawer;
    int color1=0;
    Class fragmentClass;
    public static Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().hide();
        }
        sNavigationDrawer = findViewById(R.id.navigationDrawer);

        List<com.shrikanthravi.customnavigationdrawer2.data.MenuItem> menuItems = new ArrayList<>();

        //Use the MenuItem given by this library and not the default one.
        //First parameter is the title of the menu item and then the second parameter is the image which will be the background of the menu item.

        menuItems.add(new com.shrikanthravi.customnavigationdrawer2.data.MenuItem("Thực Đơn",R.drawable.news_bg));
        menuItems.add(new com.shrikanthravi.customnavigationdrawer2.data.MenuItem("Đặt Bàn",R.drawable.feed_bg));
        menuItems.add(new com.shrikanthravi.customnavigationdrawer2.data.MenuItem("Gọi Món",R.drawable.message_bg));
        menuItems.add(new com.shrikanthravi.customnavigationdrawer2.data.MenuItem("Chi Tiết",R.drawable.music_bg));
        menuItems.add(new com.shrikanthravi.customnavigationdrawer2.data.MenuItem("Đánh Giá",R.drawable.music_bg));
        menuItems.add(new com.shrikanthravi.customnavigationdrawer2.data.MenuItem("Thanh Toán",R.drawable.music_bg));
        menuItems.add(new com.shrikanthravi.customnavigationdrawer2.data.MenuItem("Đánh giá",R.drawable.music_bg));
        menuItems.add(new com.shrikanthravi.customnavigationdrawer2.data.MenuItem("Nhân Viên",R.drawable.music_bg));
        menuItems.add(new com.shrikanthravi.customnavigationdrawer2.data.MenuItem("Đăng Xuất",R.drawable.music_bg));
        sNavigationDrawer.setMenuItemList(menuItems);
        fragmentClass =  NewsFragment.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();
        }


        sNavigationDrawer.setOnMenuItemClickListener(new SNavigationDrawer.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClicked(int position) {
                System.out.println("Position "+position);

                switch (position){
                    case 0:{
                        color1 = R.color.red;
                        fragmentClass = NewsFragment.class;
                        break;
                    }
                    case 1:{
                        color1 = R.color.orange;
                        fragmentClass = FeedFragment.class;
                        break;
                    }
                    case 2:{
                        color1 = R.color.green;
                        fragmentClass = MessagesFragment.class;
                        break;
                    }
                    case 3:{
                        color1 = R.color.blue;
                        fragmentClass = MusicFragment.class;
                        break;
                    }
                    case 4:{
                        color1 = R.color.blue;
                        fragmentClass = NhanVienFragment.class;
                        break;
                    }

                }
                sNavigationDrawer.setDrawerListener(new SNavigationDrawer.DrawerListener() {

                    @Override
                    public void onDrawerOpened() {

                    }

                    @Override
                    public void onDrawerOpening(){

                    }

                    @Override
                    public void onDrawerClosing(){
                        System.out.println("Drawer closed");

                        try {
                            fragment = (Fragment) fragmentClass.newInstance();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (fragment != null) {
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();

                        }
                    }

                    @Override
                    public void onDrawerClosed() {

                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        System.out.println("State "+newState);
                    }
                });
            }
        });



    }





}
