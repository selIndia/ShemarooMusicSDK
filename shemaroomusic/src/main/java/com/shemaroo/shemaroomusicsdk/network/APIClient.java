package com.shemaroo.shemaroomusicsdk.network;

import android.util.Log;


import com.shemaroo.shemaroomusicsdk.BuildConfig;
import com.shemaroo.shemaroomusicsdk.utils.Constants;
import com.shemaroo.shemaroomusicsdk.utils.ShemarooMusicSDKInstance;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static Retrofit retrofit = null;

    public static APIInterface getApiShemarooMusicLiveClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(Constants.DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constants.DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new DynamicHeaderInterceptor())
                .addInterceptor(loggingInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(ShemarooMusicSDKInstance.getShamarooMusicBaseUrlLive())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit.create(APIInterface.class);
    }

    public static APIInterface getApiBhaktiMusicLiveClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(Constants.DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constants.DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS)
//                .addInterceptor(new DynamicHeaderInterceptor())
                .addInterceptor(loggingInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(ShemarooMusicSDKInstance.getBhaktiMusicBaseUrlLive())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit.create(APIInterface.class);
    }


    public static class DynamicHeaderInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            /*if (!"/posts".contains(originalRequest.url().toString())) {
                return chain.proceed(originalRequest);
            }*/
            String base64Test = ShemarooMusicSDKInstance.getBase64FromString(Constants.Encryption.encryptionPassword);

            //TODO String keyReq = Android + _|_|_|_ + android.apikey + _|_|_|_ + base64Test
            String keyReq = Constants.Encryption.encryptionFromOS + Constants.Encryption.encryptionSeprator
                    + Constants.Encryption.encryptionFromOSKey + Constants.Encryption.encryptionSeprator + base64Test;

            String token = ShemarooMusicSDKInstance.encryptHeader(keyReq);

            Request newRequest = originalRequest.newBuilder()
                    .header(Constants.Encryption.encryptionApiAuthorizationHeaderKey, token)
                    .header(Constants.Encryption.encryptionDFromHeaderKey, Constants.Encryption.encryptionFromOS)
                    .build();

            Log.d("Headers", newRequest.headers().toString());

            return chain.proceed(newRequest);
        }

    }
}
