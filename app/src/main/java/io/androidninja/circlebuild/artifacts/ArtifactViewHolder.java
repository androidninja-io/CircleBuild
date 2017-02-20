package io.androidninja.circlebuild.artifacts;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.androidninja.circlebuild.R;
import io.androidninja.models.Artifact;

public class ArtifactViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.artifact_path)
    TextView tv_artifactPath;

    View itemView;

    public ArtifactViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.itemView = itemView;
    }

    public void bind(Artifact artifact, final ArtifactsContract.ArtifactSelectedListener listener) {
        tv_artifactPath.setText(artifact.getPrettyPath());
        itemView.setTag(artifact);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Artifact artifact = (Artifact) view.getTag();
                listener.artifactSelected(artifact);
            }
        });
    }


}
