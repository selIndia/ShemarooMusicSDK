package com.shemaroo.shemaroomusicsdk.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.util.Base64;
import android.view.View;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.shemaroo.shemaroomusicsdk.R;
import com.shemaroo.shemaroomusicsdk.activity.PlayerActivity;
import com.shemaroo.shemaroomusicsdk.model.getbhaktipartnerdata.GetBhaktiPartnerDataRequestParser;
import com.shemaroo.shemaroomusicsdk.model.getbhaktipartnerdata.GetBhaktiPartnerDataResponseParser;
import com.shemaroo.shemaroomusicsdk.model.getslugdata.GetSlugDataRequsetParser;
import com.shemaroo.shemaroomusicsdk.model.getslugdata.GetSlugDataResponseParser;
import com.shemaroo.shemaroomusicsdk.mediaplayer.SongService;
import com.shemaroo.shemaroomusicsdk.network.APIClient;
import com.shemaroo.shemaroomusicsdk.widget.ShemarooMusicButton;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShemarooMusicSDKInstance {

    // APPLICATION CONTEXT
    public static Context mContext;

    public static Context getmContext() {
        return mContext;
    }

    public static void setAppLicationContext(Context mContext) {
        ShemarooMusicSDKInstance.mContext = mContext;
    }

    //ACTIVITY ON WHICH BUTTON VISIBLE
    public static Activity currActivity;

    public static Activity getCurrActivity() {
        return currActivity;
    }

    public static void setCurrActivity(Activity currActivity) {
        ShemarooMusicSDKInstance.currActivity = currActivity;
    }

    //SONG SERVICE
    public static boolean isServiceRunning(String serviceName, Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceName.equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean currentVersionSupportBigNotification() {
        int sdkVersion = android.os.Build.VERSION.SDK_INT;
        if (sdkVersion >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            return true;
        }
        return false;
    }

    public static boolean currentVersionSupportLockScreenControls() {
        int sdkVersion = android.os.Build.VERSION.SDK_INT;
        if (sdkVersion >= android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            return true;
        }
        return false;
    }


    // API CALLING
    public static String getShamarooMusicBaseUrlLive() {
        /*if(BuildConfig.DEBUG){
            return Constants.SAVEBARCODE_BASEURL_UAT;
        }else{
            return Constants.SAVEBARCODE_BASEURL_LIVE;
        }*/
        return Constants.SHEMAROO_MUSIC_BASE_URL_LIVE;
    }

    public static String getBhaktiMusicBaseUrlLive() {
        /*if(BuildConfig.DEBUG){
            return Constants.SAVEBARCODE_BASEURL_UAT;
        }else{
            return Constants.SAVEBARCODE_BASEURL_LIVE;
        }*/
        return Constants.BHAKTI_MUSIC_BASE_URL_LIVE;
    }

    public static boolean isConnectedToNetwork(Context mCtx) {
        // get Connectivity Manager object to check connection
        ConnectivityManager connectivityManager = (ConnectivityManager) mCtx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        if (activeNetwork != null) {
            // connected to the internet
            if ((activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) || (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)) {
                return true;
            }
        } else {
            // not connected to the internet
            return false;
        }
        return false;
    }

    // CIPHER ENCRYPTION ALGO FOR DYNAMIC API HEADER
    @SuppressLint("NewApi")
    public static String getBase64FromString(String pass) {
        try {
            return android.util.Base64.encodeToString(pass.getBytes(Constants.Encryption.characterEncoding), android.util.Base64.NO_WRAP);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    @SuppressLint("NewApi")
    public static String encryptHeader(String plainText) {
        String encryptedText = "";
        String encryptionKey = Constants.Encryption.encryptionKey;
        String characterEncoding = Constants.Encryption.characterEncoding;
        String cipherTransformation = Constants.Encryption.cipherTransformation;
        String aesEncryptionAlgorithem = Constants.Encryption.aesEncryptionAlgorithem;
        try {
            Cipher cipher = Cipher.getInstance(cipherTransformation);
            byte[] key = encryptionKey.getBytes(characterEncoding);
            SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
            IvParameterSpec ivparameterspec = new IvParameterSpec(key);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivparameterspec);
            byte[] cipherText = cipher.doFinal(plainText.getBytes(characterEncoding));
//            Base64.Encoder encoder = Base64.getEncoder();
//            encryptedText = encoder.encodeToString(cipherText);
            encryptedText = android.util.Base64.encodeToString(cipherText, Base64.NO_WRAP);

        } catch (Exception E) {
            System.err.println("Encrypt Exception : " + E.getMessage());
        }
        return encryptedText;
    }

    public static void updateThisApp(Context mCtx) {
        Uri uri = Uri.parse("market://details?id=" + mCtx.getPackageName());

        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            mCtx.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            mCtx.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + mCtx.getPackageName())));
        }
    }

    public static SpannableString getClickableStringToUpDateApp(Context mCtx, String message) {
        SpannableString string = new SpannableString(message + " Click To Update");
        string.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                updateThisApp(mCtx);
            }
        }, message.length() + 1, string.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return string;
    }

    // PLAYER CONTROLS
    public static void startPlayer() {
        Intent intent = new Intent(getCurrActivity(), PlayerActivity.class);
        getCurrActivity().startActivity(intent);
    }

    public static void stopPlayer() {
        Intent i = new Intent(getCurrActivity(), SongService.class);
        getCurrActivity().stopService(i);
    }

    // CUSTOM BUTTON IMAGE
   /* public static void getImage(final ShemarooMusicButton mShemarooMusicButton) {
        mShemarooMusicButton.showProgress();
        GetSlugDataRequsetParser reqObject = new GetSlugDataRequsetParser("radio");
        reqObject.setUserID(0);
        if (ShemarooMusicSDKInstance.isConnectedToNetwork(mContext)) {
            Call<GetSlugDataResponseParser> call = APIClient.getApiShemarooMusicLiveClient().getSlugData(reqObject);
            call.enqueue(new Callback<GetSlugDataResponseParser>() {
                @Override
                public void onResponse(Call<GetSlugDataResponseParser> call, Response<GetSlugDataResponseParser> response) {
                    mShemarooMusicButton.hideProgress();
                    if (response != null) {
                        if (response.body() != null) {
                            if (response.body().getItems() != null && response.body().getItems().size() > 0) {
                                RequestOptions requestOptions =
                                        new RequestOptions().transforms(new CenterInside(),
                                                new RoundedCorners(10));
                                Glide.with(getmContext())
                                        .load(response.body().getItems().get(0).getImageUrl())
//                                        .load("https://cdnwapdom.shemaroo.com/shemaroomusic/imagepreview/156x156/shree_420_156x156.jpg?selAppId=shemaroomusic")
                                        .placeholder(R.drawable.default_img)
                                        .error(R.drawable.default_img)
                                        .apply(requestOptions)
                                        .into(mShemarooMusicButton.imgBtn);
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<GetSlugDataResponseParser> call, Throwable t) {
                    mShemarooMusicButton.hideProgress();
                    ShemarooMusicSDKInstance.showShortToast(mContext, Constants.API_NETWORK_ISSUE_ERROR_MESSAGE);
                    String errorMessage = ((Exception) t).getMessage();
                }
            });
        } else {
            mShemarooMusicButton.hideProgress();
            ShemarooMusicSDKInstance.showShortToast(mContext, Constants.INTERNET_NOT_WORKING_ERROR_MESSAGE);
        }
    }*/

    public static void getImage(final ShemarooMusicButton mShemarooMusicButton) {
        mShemarooMusicButton.showProgress();
        GetBhaktiPartnerDataRequestParser reqObject = new GetBhaktiPartnerDataRequestParser();
        reqObject.setBhaktiPartnerEnc(Constants.BHAKTI_API_REQ_PARAMETER);
        if (ShemarooMusicSDKInstance.isConnectedToNetwork(mContext)) {
            Call<GetBhaktiPartnerDataResponseParser> call =
                    APIClient.getApiBhaktiMusicLiveClient().getBhaktiPartnerData(reqObject);
            call.enqueue(new Callback<GetBhaktiPartnerDataResponseParser>() {
                @Override
                public void onResponse(Call<GetBhaktiPartnerDataResponseParser> call, Response<GetBhaktiPartnerDataResponseParser> response) {
                    mShemarooMusicButton.hideProgress();
                    if (response != null) {
                        if (response.body() != null) {
                            if (response.body().getResult() != null && response.body().getResult().size() > 0) {
                                if (response.body().getResult().get(0).getData() != null) {
                                    RequestOptions requestOptions =
                                            new RequestOptions().transforms(new CenterInside(),
                                                    new RoundedCorners(10));
                                    Glide.with(getmContext())
                                            .load(response.body().getResult().get(0).getData().getContentImageName())
//                                        .load("https://cdnwapdom.shemaroo.com/shemaroomusic/imagepreview/156x156/shree_420_156x156.jpg?selAppId=shemaroomusic")
                                            .placeholder(R.drawable.default_img)
                                            .error(R.drawable.default_img)
                                            .apply(requestOptions)
                                            .into(mShemarooMusicButton.imgBtn);
                                }
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<GetBhaktiPartnerDataResponseParser> call, Throwable t) {
                    mShemarooMusicButton.hideProgress();
                    ShemarooMusicSDKInstance.showShortToast(mContext, Constants.API_NETWORK_ISSUE_ERROR_MESSAGE);
                    String errorMessage = ((Exception) t).getMessage();
                }
            });
        } else {
            mShemarooMusicButton.hideProgress();
            ShemarooMusicSDKInstance.showShortToast(mContext, Constants.INTERNET_NOT_WORKING_ERROR_MESSAGE);
        }
    }

    // TOAST WORK
    public static void showShortToast(Context ctx, String message) {
        Toast.makeText(ctx, "" + message, Toast.LENGTH_SHORT).show();
    }
}
