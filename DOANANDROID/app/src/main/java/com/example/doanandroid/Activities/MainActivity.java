package com.example.doanandroid.Activities;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;

import com.example.doanandroid.Adapter.Photo;
import com.example.doanandroid.Adapter.PhotoAdapter;
import com.example.doanandroid.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {
     private Button btnDangNhap,btnDangKi;
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private List<Photo> mListPhoto;
    private Timer mTimer;

    private View view;


    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewpager);
        circleIndicator = findViewById(R.id.circle_indicator);
        mListPhoto =getListPhoto();
        PhotoAdapter adapter= new PhotoAdapter(mListPhoto);
        viewPager.setAdapter(adapter);
        circleIndicator.setViewPager(viewPager);
        autiSlideImage();
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        btnDangKi = (Button) findViewById(R.id.btnDangKi);
        btnDangNhap.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view)
            {
                Intent in= new Intent(MainActivity.this, Login.class);
                startActivity(in);

            }

        });

        btnDangKi.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view)
            {
                Intent in= new Intent(MainActivity.this, Register.class);
                startActivity(in);
            }

        });

        printHashKey(MainActivity.this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        CallbackManager callbackManager = CallbackManager.Factory.create();


        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });


        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code

                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();



        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));


    }

    public static void printHashKey(Context pContext) {
        try {
            PackageInfo info = pContext.getPackageManager().getPackageInfo(pContext.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Log.i("TAG", "printHashKey() Hash Key: " + hashKey);
            }
        } catch (NoSuchAlgorithmException e) {
            Log.e("TAG", "printHashKey()", e);
        } catch (Exception e) {
            Log.e("TAG", "printHashKey()", e);
        }
    }
    private List<Photo> getListPhoto()
    {
        List<Photo> list= new ArrayList<>();
        list.add(new Photo(R.drawable.image1));
        list.add(new Photo(R.drawable.image2));
        list.add(new Photo(R.drawable.image3));
        list.add(new Photo(R.drawable.image4));
        list.add(new Photo(R.drawable.image5));
        return list;
    }
    private void autiSlideImage()
    {
        if(mListPhoto ==null || mListPhoto.isEmpty() ||viewPager ==null)
        {
            return;
        }
        //init timer
        if(mTimer == null)
        {
            mTimer = new Timer();
        }
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPager.getCurrentItem();
                        int totalItem = mListPhoto.size() -1;
                        if(currentItem < totalItem)
                        {
                            currentItem ++;
                            viewPager.setCurrentItem(currentItem);
                        }
                        else{
                            viewPager.setCurrentItem(0);
                        }

                    }
                });
            }
        },500,3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mTimer != null)
        {
            mTimer.cancel();
            mTimer =null;
        }
    }
}
