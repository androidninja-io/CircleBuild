package io.androidninja.circlebuild.projectdetails.branches;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.androidninja.circlebuild.R;
import io.androidninja.circlebuild.Utils.Pair;
import io.androidninja.circlebuild.artifacts.ArtifactsActivity;
import io.androidninja.models.Branch;
import java.util.HashMap;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class BranchFragment extends Fragment implements BranchContract.View, MyBranchRecyclerViewAdapter.BranchClickListener {

    private static final String ARG_PROJECT_JSON = "project_json";
    private HashMap<String, Branch> branches;
    private RecyclerView recyclerView;
    private BranchContract.Presenter presenter;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BranchFragment() {
    }

    public static BranchFragment newInstance(String projectJson) {
        BranchFragment fragment = new BranchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PROJECT_JSON, projectJson);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            String projectJson = getArguments().getString(ARG_PROJECT_JSON);
            presenter = new BranchPresenter(this, projectJson);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_branch_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        }
        presenter.init();
        return view;
    }

    @Override
    public void showBranches(List<Pair<String, Branch>> branches) {
        recyclerView.setAdapter(new MyBranchRecyclerViewAdapter(branches, this));
    }

    @Override
    public void showBuildArtifacts(String vcs, String project, String userName, String branch) {
        ArtifactsActivity.launch(this.getContext(), vcs, project, userName, branch);
    }

    @Override
    public void onClick(String branchName, Branch branch) {
        presenter.branchClicked(branchName);
    }
}
