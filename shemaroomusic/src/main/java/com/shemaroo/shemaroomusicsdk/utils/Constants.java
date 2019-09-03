package com.shemaroo.shemaroomusicsdk.utils;

public interface Constants {
    String API_NETWORK_ISSUE_ERROR_MESSAGE = "Network issue please try again.";
    String INTERNET_NOT_WORKING_ERROR_MESSAGE = "Internet May not work. Please check Internet Connection.";

//    String SHEMAROO_MUSIC_BASE_URL_LIVE = "https://www.shemaroomusic.com/api/api/";
    String SHEMAROO_MUSIC_BASE_URL_LIVE = "http://uat.shemaroomusic.com/api/api/";

    int DEFAULT_CONNECT_TIMEOUT = 30;
    int DEFAULT_WRITE_TIMEOUT = 30;
    int DEFAULT_READ_TIMEOUT = 30;

    interface Encryption{
        String characterEncoding = "UTF-8";
        String cipherTransformation = "AES/CBC/PKCS5PADDING";
        String aesEncryptionAlgorithem = "AES";

        String encryptionSeprator = "_|_|_|_";
        String encryptionFromOS = "Android";
        String encryptionFromOSKey = "android.apikey";

        String encryptionDFromHeaderKey = "dFrom";
        String encryptionApiAuthorizationHeaderKey = "ApiAuthorization";

        //TODO Cahnge According to New version
        String encryptionKey = "bQeThWmZq4t6w9zS";
        String encryptionPassword = "@ndro!d_@p!k@y_W_!fnybxS_b_5Y@4_9G!";
    }

    String BHAKTI_API_REQ_PARAMETER = "n1rP4mryBfkhQvj+8QJ5aB2iZPdBZXc=";
    String BHAKTI_MUSIC_BASE_URL_LIVE = "http://180.179.103.228/BhaktiRadioAPI/";

}
