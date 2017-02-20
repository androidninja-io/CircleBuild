package io.androidninja.circlebuild.projects;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.androidninja.CircleCiClient;
import io.androidninja.circlebuild.R;
import io.androidninja.circlebuild.Utils.NetworkUtils;
import io.androidninja.circlebuild.Utils.PrefUtils;
import io.androidninja.circlebuild.Utils.UIUtils;
import io.androidninja.circlebuild.projectdetails.ProjectDetailActivity;
import io.androidninja.models.Project;
import java.util.List;

public class ProjectsActivity extends AppCompatActivity implements ProjectsContract.View, ProjectsContract.ProjectSelectedListener {


    private static final int ANIMATION_DURATION = 300;

    @BindView(R.id.progressBar)
    ProgressBar pb_loading;

    @BindView(R.id.recyclerView)
    RecyclerView rv_projects;

    Snackbar sb_error;

    ProjectsContract.Presenter presenter;

    public static void launch(Context context) {
        Intent intent = new Intent(context, ProjectsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        PrefUtils prefUtils = new PrefUtils(this);
        CircleCiClient circleCiClient = new CircleCiClient(prefUtils.getCircleCiToken());
        presenter = new ProjectsPresenter(this, circleCiClient, new NetworkUtils(this));
        presenter.loadProjects();
    }

    @Override
    public void showProgress(boolean show) {
        if(show) {
            if(sb_error != null && sb_error.isShown()) {
                sb_error.dismiss();
            }
            UIUtils.crossfadeViews(pb_loading, rv_projects, ANIMATION_DURATION);
        } else {
            UIUtils.hideView(pb_loading, ANIMATION_DURATION);
        }
    }

    @Override
    public void showProjects(List<Project> projects) {
        ProjectsAdapter projectsAdapter = new ProjectsAdapter(projects, this);
        rv_projects.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_projects.setLayoutManager(layoutManager);
        rv_projects.setAdapter(projectsAdapter);
        UIUtils.showView(rv_projects, ANIMATION_DURATION);
    }

    @Override
    public void showError(String message) {
        sb_error = Snackbar.make(pb_loading, message, Snackbar.LENGTH_INDEFINITE);
        sb_error.setAction("Retry", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.loadProjects();
            }
        });
        sb_error.show();
    }

    @Override
    public void goToProject(Project project) {
        ProjectDetailActivity.launch(this, project);
    }

    @Override
    public void projectSelected(Project project) {
        presenter.projectClicked(project);
    }
}
