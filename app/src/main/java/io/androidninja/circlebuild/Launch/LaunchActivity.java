package io.androidninja.circlebuild.Launch;

import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.androidninja.CircleCiClient;
import io.androidninja.circlebuild.R;
import io.androidninja.circlebuild.Utils.NetworkUtils;
import io.androidninja.circlebuild.Utils.PrefUtils;
import io.androidninja.circlebuild.Utils.StringUtils;
import io.androidninja.circlebuild.Utils.UIUtils;
import io.androidninja.models.Project;
import java.util.ArrayList;
import java.util.List;

public class LaunchActivity extends AppCompatActivity implements LaunchContract.View {

    static final String TAG = LaunchActivity.class.getSimpleName();
    static final int ANIMATION_DURATION = 200;
    LaunchContract.Presenter presenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.input_token)
    EditText et_token;
    @BindView(R.id.input_layout_token)
    TextInputLayout til_token;
    @BindView(R.id.button)
    Button btn_submit;
    @BindView(R.id.progressBar)
    ProgressBar pb_loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        presenter = new LaunchPresenter(this, new CircleCiClient(), new PrefUtils(this), new NetworkUtils(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
        UIUtils.showKeyboard(this);
    }

    @Override
    public void showEnterToken(String errorMessage) {
        UIUtils.showKeyboard(this);
        List<View> viewsToShow = new ArrayList<>(2);
        List<View> viewsToHide = new ArrayList<>(1);
        viewsToHide.add(pb_loading);
        viewsToShow.add(til_token);
        viewsToShow.add(btn_submit);
        UIUtils.crossfadeViews(viewsToShow, viewsToHide, ANIMATION_DURATION);
        if(StringUtils.isEmpty(errorMessage)) {
            til_token.setErrorEnabled(false);
        } else {
            til_token.setError(errorMessage);
            et_token.requestFocus();
        }
    }

    @Override
    public void showProgress(boolean show) {
        UIUtils.hideKeyboard(this, pb_loading);
        List<View> loadingViews = new ArrayList<>(1);
        loadingViews.add(pb_loading);
        List<View> dataViews = new ArrayList<>(2);
        dataViews.add(til_token);
        dataViews.add(btn_submit);
        if(show) {
            UIUtils.crossfadeViews(loadingViews, dataViews, ANIMATION_DURATION);
        } else {
            UIUtils.hideViews(loadingViews, ANIMATION_DURATION);
        }
    }

    @Override
    public void goToProjects(List<Project> projects) {
        Toast.makeText(this, "Your in!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNetworkError() {
        final Snackbar snackbar = Snackbar.make(toolbar, "Network not connected", BaseTransientBottomBar.LENGTH_INDEFINITE);
        snackbar.setAction("Retry", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.dismiss();
                presenter.retryTokenValidation();
            }
        });
        snackbar.show();
    }

    @Override
    public void setPresenter(LaunchContract.Presenter presenter) {

    }

    @OnClick(R.id.button)
    void submit() {
        presenter.tokenEntered(et_token.getText().toString());
    }

}
