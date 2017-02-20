package io.androidninja.circlebuild.projectdetails.branches;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.androidninja.circlebuild.R;
import io.androidninja.circlebuild.Utils.Pair;
import io.androidninja.models.Branch;
import java.util.List;

public class MyBranchRecyclerViewAdapter extends RecyclerView.Adapter<BranchViewHolder> {

    private final List<Pair<String, Branch>> mValues;

    public interface BranchClickListener {
        void onClick(String branchName, Branch branch);
    }

    private BranchClickListener listener;

    public MyBranchRecyclerViewAdapter(List<Pair<String, Branch>> items, BranchClickListener listener) {
        mValues = items;
        this.listener = listener;
    }

    @Override
    public BranchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_branch, parent, false);
        return new BranchViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(final BranchViewHolder holder, int position) {
        holder.bind(mValues.get(position).second, mValues.get(position).first, position == 0);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
}
