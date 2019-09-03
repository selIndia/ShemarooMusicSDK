package com.shemaroo.shemaroomusicsdk.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.shemaroo.shemaroomusicsdk.R;
import com.shemaroo.shemaroomusicsdk.model.getbhaktipartnerdata.GetBhaktiPartnerDataRequestParser;
import com.shemaroo.shemaroomusicsdk.model.getbhaktipartnerdata.GetBhaktiPartnerDataResponseData;
import com.shemaroo.shemaroomusicsdk.model.getbhaktipartnerdata.GetBhaktiPartnerDataResponseParser;
import com.shemaroo.shemaroomusicsdk.model.getslugdata.GetSlugDataRequsetParser;
import com.shemaroo.shemaroomusicsdk.model.getslugdata.GetSlugDataResponseParser;
import com.shemaroo.shemaroomusicsdk.model.getslugdata.ItemSlugDataResponseParser;
import com.shemaroo.shemaroomusicsdk.listner.ISongsGettingDownloadedByAsync;
import com.shemaroo.shemaroomusicsdk.mediaplayer.AsyncTaskToLoadSong;
import com.shemaroo.shemaroomusicsdk.mediaplayer.Controls;
import com.shemaroo.shemaroomusicsdk.mediaplayer.PlayerConstants;
import com.shemaroo.shemaroomusicsdk.mediaplayer.SongService;
import com.shemaroo.shemaroomusicsdk.network.APIClient;
import com.shemaroo.shemaroomusicsdk.network.APIInterface;
import com.shemaroo.shemaroomusicsdk.utils.Constants;
import com.shemaroo.shemaroomusicsdk.utils.ShemarooMusicSDKInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayerActivity extends BaseActivity implements ISongsGettingDownloadedByAsync, View.OnClickListener, View.OnTouchListener {
    APIInterface apiInterfaceLive;
    private static boolean activityVisible;
    static Context mContext;

    public static TextView lblTitleTrack, lblArtistName, lblSongTitle;
    public static ImageView imgAlbumMain, btnPlay, btnPause, imgBack;
    public static SeekBar progressPlayedDuration;

    public static RelativeLayout playerView;
//    public static TextView lblErrorMessage;

    public static boolean isActivityVisible() {
        return activityVisible;
    }

    public static void activityResumed() {
        activityVisible = true;
    }

    public static void activityPaused() {
        activityVisible = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setContent() {
        setContentView(R.layout.activity_player);
    }

    @Override
    protected void initializeComponents() {
        apiInterfaceLive = APIClient.getApiShemarooMusicLiveClient();
        mContext = ShemarooMusicSDKInstance.getmContext();
        lblTitleTrack = findViewById(R.id.lblTitleTrack);
        lblSongTitle = findViewById(R.id.lblSongTitle);
        lblArtistName = findViewById(R.id.lblArtistName);

        imgAlbumMain = findViewById(R.id.imgAlbumMain);
        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        imgBack = findViewById(R.id.imgBack);
        progressPlayedDuration = findViewById(R.id.progressPlayedDuration);

        playerView = findViewById(R.id.playerView);
//        lblErrorMessage = findViewById(R.id.lblErrorMessage);
        callGetAlbumBySlugNewAPI();
    }

    /*private void callGetAlbumBySlugNewAPI() {
        GetSlugDataRequsetParser reqObject = new GetSlugDataRequsetParser("radio");
        reqObject.setUserID(0);
        if (ShemarooMusicSDKInstance.isConnectedToNetwork(mContext)) {
            Call<GetSlugDataResponseParser> call = apiInterfaceLive.getSlugData(reqObject);
            call.enqueue(new Callback<GetSlugDataResponseParser>() {
                @Override
                public void onResponse(Call<GetSlugDataResponseParser> call, Response<GetSlugDataResponseParser> response) {
                    if (response != null) {
                        if (response.body() != null) {
                            if (response.body().getItems() != null && response.body().getItems().size() > 0) {
                                if (!isFinishing()) {
//                                    lblErrorMessage.setVisibility(View.GONE);
                                    playerView.setVisibility(View.VISIBLE);
                                    ItemSlugDataResponseParser tempModel = response.body().getItems().get(0);
                                    loadSong(tempModel);
                                }
                            } else {
                                if (!isFinishing()) {
                                    playerView.setVisibility(View.GONE);
                                    ShemarooMusicSDKInstance.showShortToast(PlayerActivity.this,
                                            "API Authentication Issue.. Please Update Library " +
                                                    "version");
                                    finish();
//                                    lblErrorMessage.setVisibility(View.VISIBLE);
//                                    lblErrorMessage.setText(response.body().getApiErrorMessage());
                                }
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<GetSlugDataResponseParser> call, Throwable t) {
                    ShemarooMusicSDKInstance.showShortToast(mContext, Constants.API_NETWORK_ISSUE_ERROR_MESSAGE);
                    String errorMessage = ((Exception) t).getMessage();
                    finish();
                }
            });
        } else {
            ShemarooMusicSDKInstance.showShortToast(mContext, Constants.INTERNET_NOT_WORKING_ERROR_MESSAGE);
            finish();
        }
    } */
    private void callGetAlbumBySlugNewAPI() {
        GetBhaktiPartnerDataRequestParser reqObject = new GetBhaktiPartnerDataRequestParser();
        reqObject.setBhaktiPartnerEnc(Constants.BHAKTI_API_REQ_PARAMETER);
        if (ShemarooMusicSDKInstance.isConnectedToNetwork(mContext)) {
            Call<GetBhaktiPartnerDataResponseParser> call =
                    APIClient.getApiBhaktiMusicLiveClient().getBhaktiPartnerData(reqObject);
            call.enqueue(new Callback<GetBhaktiPartnerDataResponseParser>() {
                @Override
                public void onResponse(Call<GetBhaktiPartnerDataResponseParser> call, Response<GetBhaktiPartnerDataResponseParser> response) {
                    if (response != null) {
                        if (response.body() != null) {
                            if (response.body().getResult() != null && response.body().getResult().size() > 0) {
                                if (response.body().getResult().get(0).getData() != null) {
                                    if (!isFinishing()) {
//
                                        playerView.setVisibility(View.VISIBLE);
                                        GetBhaktiPartnerDataResponseData tempModel = response.body().getResult().get(0).getData();
                                        loadSong(tempModel);
                                    }
                                }
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<GetBhaktiPartnerDataResponseParser> call, Throwable t) {
                    ShemarooMusicSDKInstance.showShortToast(mContext, Constants.API_NETWORK_ISSUE_ERROR_MESSAGE);
                    String errorMessage = ((Exception) t).getMessage();
                }
            });
        } else {
            ShemarooMusicSDKInstance.showShortToast(mContext, Constants.INTERNET_NOT_WORKING_ERROR_MESSAGE);
        }

    }

    private void loadSong(GetBhaktiPartnerDataResponseData tempModel) {
        PlayerConstants.SONGS_LIST.clear();
        PlayerConstants.SONGS_LIST.add(tempModel);
        PlayerConstants.CURRENT_PLAYING_IMAGE_URL = tempModel.getContentImageName() != null ?
                tempModel.getContentImageName() : "";
        PlayerConstants.CURRENT_PLAYING_ITEMSONG = tempModel;

        int CURRENT_PLAYING_SONG_POSITION = 0;
        new AsyncTaskToLoadSong(mContext, this).execute(CURRENT_PLAYING_SONG_POSITION);
    }

    @Override
    protected void setListeners() {
        imgBack.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
        progressPlayedDuration.setOnTouchListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        PlayerActivity.activityResumed();
        boolean isServiceRunning = ShemarooMusicSDKInstance.isServiceRunning(SongService.class.getName(), mContext);
        if (isServiceRunning) {
            updateUI();
        }
        changeButton();

        PlayerConstants.PROGRESSBAR_HANDLER = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Integer i[] = (Integer[]) msg.obj;
                   /* textBufferDuration.setText(JsonUtils.getDuration(i[0]));
                    textDuration.setText(JsonUtils.getDuration(i[1]));
                    //  progressBar.setProgress(i[2]);
                    // seekBar.setMax(UtilFunctions.getDuration(i[1]));
                    totalDuration=i[1];*/
                progressPlayedDuration.setProgress(progressPlayedDuration.getMax());
//                progressPlayedDuration.setEnabled(false);
            }
        };
    }

    public static void changeUI() {
        updateUI();
        changeButton();
    }

    private static void updateUI() {
        try {
            lblTitleTrack.setText(PlayerConstants.CURRENT_PLAYING_SONGNAME);
            lblSongTitle.setText(PlayerConstants.CURRENT_PLAYING_SONGNAME);
            lblSongTitle.setSelected(true);
//            String composer = null;
            lblArtistName.setText(PlayerConstants.CURRENT_PLAYING_ARTIST);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Set Image
        System.out.println("IMAGE VALUE is -AUDIO PLAYER:" + PlayerConstants.CURRENT_PLAYING_IMAGE_URL);
        Glide.with(mContext)
                .load(PlayerConstants.CURRENT_PLAYING_IMAGE_URL).fitCenter().placeholder(R.drawable.default_img)
                .into(imgAlbumMain);
    }

    public static void changeButton() {
        if (PlayerConstants.SONG_PAUSED) {
            btnPause.setVisibility(View.GONE);
            btnPlay.setVisibility(View.VISIBLE);
        } else {
            btnPause.setVisibility(View.VISIBLE);
            btnPlay.setVisibility(View.GONE);
        }
    }

    protected void onPause() {
        super.onPause();
        PlayerActivity.activityPaused();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unbinder.unbind();
    }

    @Override
    public void songDownloadCompletedByAsync() {
        changeUI();
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btnPause) {
            Controls.pauseControl(mContext);
            btnPause.setVisibility(View.GONE);
            btnPlay.setVisibility(View.VISIBLE);
        } else if (i == R.id.btnPlay) {
            Controls.playControl(mContext);
            btnPause.setVisibility(View.VISIBLE);
            btnPlay.setVisibility(View.GONE);
        } else if (i == R.id.imgBack) {
            this.onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return true;
    }
}
