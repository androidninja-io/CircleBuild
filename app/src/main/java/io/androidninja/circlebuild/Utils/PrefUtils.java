package io.androidninja.circlebuild.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtils {

    static final String PREFERENCES_FILE_NAME = "CIRCLE_BUILD_PREFERENCES";
    static final String CIRCLECI_TOKEN_KEY = "circleCiToken";

    SharedPreferences sharedPreferences;

    public PrefUtils(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void setCircleCiToken(String token) {
        sharedPreferences.edit().putString(CIRCLECI_TOKEN_KEY, token).apply();
    }

    public String getCircleCiToken() {
        return sharedPreferences.getString(CIRCLECI_TOKEN_KEY, null);
    }

}
