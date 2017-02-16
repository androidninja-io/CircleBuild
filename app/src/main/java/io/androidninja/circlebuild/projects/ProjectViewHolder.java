package io.androidninja.circlebuild.projects;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.androidninja.circlebuild.R;
import io.androidninja.models.Project;

public class ProjectViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.project_title)
    TextView projectTitle;

    ProjectsContract.ProjectSelectedListener projectSelectedListener;


    public ProjectViewHolder(View itemView, ProjectsContract.ProjectSelectedListener projectSelectedListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.projectSelectedListener = projectSelectedListener;
    }

    public void bind(Project project) {
        projectTitle.setText(project.getReponame());
        projectTitle.setTag(project);
    }

    @OnClick(R.id.project_title)
    void projectClicked(View view) {
        Project project = (Project) view.getTag();
        projectSelectedListener.projectSelected(project);
    }
}
