package io.androidninja.circlebuild.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

public class NetworkUtils {

    ConnectivityManager connectivityManager;

    public NetworkUtils(Context context) {
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public boolean isConnectedToNetwork() {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return (networkInfo != null && networkInfo.isConnected());
        } else {
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            for (int i = 0; i<info.length; i++){
                if (info[i].getState() == NetworkInfo.State.CONNECTED){
                    return true;
                }
            }
            return false;
        }
    }

}
