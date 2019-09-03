package com.shemaroo.shemaroomusicsdk.widget;

import android.content.Context;

import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.shemaroo.shemaroomusicsdk.R;
import com.shemaroo.shemaroomusicsdk.utils.ShemarooMusicSDKInstance;

import java.util.Timer;
import java.util.TimerTask;


public class ShemarooMusicButton extends RelativeLayout implements View.OnClickListener {

    private View rootView;
    public ImageView imgBtn;
    private ProgressBar proBtn;
    private Context mCtx;

    public ShemarooMusicButton(Context context) {
        super(context, null);
        this.mCtx = context;
        init(context);
    }

    public ShemarooMusicButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mCtx = context;
        init(context);
    }

    private void init(final Context context) {
        rootView = inflate(context, R.layout.item_cutom_button, this);
        setMeasuredDimension(WindowManager.LayoutParams.MATCH_PARENT, 100);
        imgBtn = (ImageView) rootView.findViewById(R.id.imgBtn);
        proBtn = (ProgressBar) rootView.findViewById(R.id.proBtn);

        rootView.setOnClickListener(this);
    }

    public void showProgress(){
        proBtn.setVisibility(VISIBLE);
    }
    public void hideProgress(){
        proBtn.setVisibility(GONE);
    }

    @Override
    public void onClick(View view) {
        ShemarooMusicSDKInstance.startPlayer();
    }

}
