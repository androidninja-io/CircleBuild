package io.androidninja.circlebuild.projectdetails.branches;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.androidninja.circlebuild.R;
import io.androidninja.circlebuild.Utils.Pair;
import io.androidninja.models.Branch;

public class BranchViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.branch_name)
    TextView tv_branchName;

    @BindView(R.id.branch_status)
    ImageView iv_branchStatus;

    @BindView(R.id.branch_default)
    ImageView iv_branchDefault;

    View itemView;

    MyBranchRecyclerViewAdapter.BranchClickListener listener;

    public BranchViewHolder(View itemView, MyBranchRecyclerViewAdapter.BranchClickListener listener) {
        super(itemView);
        this.itemView = itemView;
        ButterKnife.bind(this, itemView);
        this.listener = listener;
    }

    public void bind(Branch branch, String branchName, boolean defaultBranch) {
        tv_branchName.setText(branchName);
        int buildStatus = branch.getCurrentStatus();
        switch (buildStatus) {
            case Branch.BUILD_SUCCESS:
                iv_branchStatus.setImageResource(R.drawable.build_status_success);
                break;
            case Branch.BUILD_FAILURE:
                iv_branchStatus.setImageResource(R.drawable.build_status_failure);
                break;
            case Branch.BUILD_RUNNING:
                iv_branchStatus.setImageResource(R.drawable.build_status_running);
                break;
            default:
                iv_branchStatus.setImageResource(R.drawable.build_status_skipped);
        }
        if(defaultBranch) {
            iv_branchDefault.setVisibility(View.VISIBLE);
        } else {
            iv_branchDefault.setVisibility(View.INVISIBLE);
        }

        itemView.setTag(new Pair<>(branchName, branch));

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pair<String, Branch> pair = (Pair<String, Branch>) view.getTag();
                if(pair.second.getCurrentStatus() == Branch.BUILD_SUCCESS) {
                    listener.onClick(pair.first, pair.second);
                } else {
                    Toast.makeText(view.getContext(), "Can only view successful builds", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
