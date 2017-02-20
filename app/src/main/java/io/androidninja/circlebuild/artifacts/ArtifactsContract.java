package io.androidninja.circlebuild.artifacts;

import io.androidninja.models.Artifact;
import java.util.List;

public interface ArtifactsContract {

    interface View {
        void showProgress(boolean show);
        void showArtifacts(List<Artifact> artifacts);
        void showError(String message);
    }

    interface Presenter {
        void loadArtificats(String vcs_type, String project, String userName, String branch);
        void artifactClicked(Artifact artifact);

    }

    interface ArtifactSelectedListener {
        void artifactSelected(Artifact artifact);
    }

}
