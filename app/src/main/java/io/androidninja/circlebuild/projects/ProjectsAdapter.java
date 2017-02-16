package io.androidninja.circlebuild.projects;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.androidninja.circlebuild.R;
import io.androidninja.models.Project;
import java.util.List;

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectViewHolder> {

    ProjectsContract.ProjectSelectedListener projectSelectedListener;
    List<Project> projects;

    public ProjectsAdapter (List<Project> projects, ProjectsContract.ProjectSelectedListener projectSelectedListener) {
        this.projects = projects;
        this.projectSelectedListener = projectSelectedListener;
    }

    @Override
    public ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project, parent, false);
        return new ProjectViewHolder(view, projectSelectedListener);
    }

    @Override
    public void onBindViewHolder(ProjectViewHolder holder, int position) {
        holder.bind(projects.get(position));
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }


}
