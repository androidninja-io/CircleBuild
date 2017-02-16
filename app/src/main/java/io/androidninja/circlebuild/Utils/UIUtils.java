package io.androidninja.circlebuild.Utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import java.util.List;

public class UIUtils {

    public static void showKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public static void hideKeyboard(Activity activity, View view) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void crossfadeViews(List<View> viewsToShow, List<View> viewsToHide, int animationDuration) {

        hideViews(viewsToHide, animationDuration);
        showViews(viewsToShow, animationDuration);

    }

    public static void hideViews(List<View> viewsToHide, int animationDuration) {
        for(final View viewToHide : viewsToHide) {
            viewToHide.animate()
                .alpha(0f)
                .setDuration(animationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        viewToHide.setVisibility(View.GONE);
                    }
                });
        }
    }

    public static void showViews(List<View> viewsToShow, int animationDuration) {
        for(View viewToShow : viewsToShow) {
            viewToShow.setAlpha(0f);
            viewToShow.setVisibility(View.VISIBLE);

            viewToShow.animate()
                .alpha(1f)
                .setDuration(animationDuration)
                .setListener(null);
        }
    }

}
