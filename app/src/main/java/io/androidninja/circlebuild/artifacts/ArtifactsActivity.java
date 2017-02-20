package io.androidninja.circlebuild.artifacts;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.androidninja.CircleCiClient;
import io.androidninja.circlebuild.R;
import io.androidninja.circlebuild.Utils.NetworkUtils;
import io.androidninja.circlebuild.Utils.PrefUtils;
import io.androidninja.circlebuild.Utils.UIUtils;
import io.androidninja.models.Artifact;
import java.util.List;

public class ArtifactsActivity extends AppCompatActivity implements ArtifactsContract.View, ArtifactsContract.ArtifactSelectedListener {


    private static final int ANIMATION_DURATION = 300;

    public static final String KEY_VCS = "vcs";
    public static final String KEY_PROJECT = "project";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_BRANCH = "branch";

    private String vcs;
    private String project;
    private String userName;
    private String branch;

    @BindView(R.id.progressBar)
    ProgressBar pb_loading;

    @BindView(R.id.recyclerView)
    RecyclerView rv_artifacts;

    Snackbar sb_error;

    ArtifactsContract.Presenter presenter;

    public static void launch(Context context, String vcs, String project, String userName, String branch) {
        Intent intent = new Intent(context, ArtifactsActivity.class);
        intent.putExtra(KEY_VCS, vcs);
        intent.putExtra(KEY_PROJECT, project);
        intent.putExtra(KEY_USERNAME, userName);
        intent.putExtra(KEY_BRANCH, branch);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artifacts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        vcs = getIntent().getStringExtra(KEY_VCS);
        project = getIntent().getStringExtra(KEY_PROJECT);
        userName = getIntent().getStringExtra(KEY_USERNAME);
        branch = getIntent().getStringExtra(KEY_BRANCH);
        PrefUtils prefUtils = new PrefUtils(this);
        CircleCiClient circleCiClient = new CircleCiClient(prefUtils.getCircleCiToken());
        presenter = new ArtifactsPresenter(this, circleCiClient, new NetworkUtils(this));
        presenter.loadArtificats(vcs, project, userName, branch);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress(boolean show) {
        if(show) {
            if(sb_error != null && sb_error.isShown()) {
                sb_error.dismiss();
            }
            UIUtils.crossfadeViews(pb_loading, rv_artifacts, ANIMATION_DURATION);
        } else {
            UIUtils.hideView(pb_loading, ANIMATION_DURATION);
        }
    }

    @Override
    public void showArtifacts(List<Artifact> artifacts) {
        ArtifactsAdapter artifactsAdapter = new ArtifactsAdapter(artifacts, this);
        rv_artifacts.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_artifacts.setLayoutManager(layoutManager);
        rv_artifacts.setAdapter(artifactsAdapter);
        UIUtils.showView(rv_artifacts, ANIMATION_DURATION);
    }

    @Override
    public void showError(String message) {
        sb_error = Snackbar.make(pb_loading, message, Snackbar.LENGTH_INDEFINITE);
        sb_error.setAction("Retry", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.loadArtificats(vcs, project, userName, branch);
            }
        });
        sb_error.show();
    }

    @Override
    public void artifactSelected(Artifact artifact) {
        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        downloadManager.enqueue(new DownloadManager.Request(Uri.parse(artifact.getUrl()+"?circle-token="+new PrefUtils(this).getCircleCiToken())));
    }
}
