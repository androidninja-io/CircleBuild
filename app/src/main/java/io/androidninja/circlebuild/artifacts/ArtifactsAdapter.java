package io.androidninja.circlebuild.artifacts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.androidninja.circlebuild.R;
import io.androidninja.models.Artifact;
import java.util.List;

public class ArtifactsAdapter extends RecyclerView.Adapter<ArtifactViewHolder> {

    private List<Artifact> artifacts;
    private ArtifactsContract.ArtifactSelectedListener listener;

    public ArtifactsAdapter(List<Artifact> artifacts, ArtifactsContract.ArtifactSelectedListener listener) {
        this.artifacts = artifacts;
        this.listener = listener;
    }

    @Override
    public ArtifactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artifact, parent, false);
        return new ArtifactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArtifactViewHolder holder, int position) {
        holder.bind(artifacts.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return artifacts.size();
    }
}
