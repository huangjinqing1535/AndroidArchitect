package com.huang.androidarchitect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private RoundClipView roundClipView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (MyApp.isHotInit){
            Log.i("hjq","MyApp.isHotInit=="+MyApp.isHotInit);
            setTheme(R.style.AppHot);
        }else {
            Log.i("hjq","MyApp.isHotInit=="+MyApp.isHotInit);
            MyApp.isHotInit = true;
            setTheme(R.style.AppCold);
        }

        setContentView(R.layout.activity_main);

        roundClipView = findViewById(R.id.round_clip_view);

        roundClipView.setRect(100, 10, 250, 250);

        ImageView mQrLineView = (ImageView) findViewById(R.id.scan_line);
        TranslateAnimation mAnimation = new TranslateAnimation(TranslateAnimation.ABSOLUTE, 0f,
                TranslateAnimation.ABSOLUTE, 0f, TranslateAnimation.RELATIVE_TO_PARENT, 0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 0.9f);
        mAnimation.setDuration(1500);
        mAnimation.setRepeatCount(-1);
        mAnimation.setRepeatMode(Animation.RESTART);
        mAnimation.setInterpolator(new LinearInterpolator());
        mQrLineView.setAnimation(mAnimation);
    }
}
