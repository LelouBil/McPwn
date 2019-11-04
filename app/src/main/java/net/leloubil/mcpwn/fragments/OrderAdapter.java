package net.leloubil.mcpwn.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import net.leloubil.mcpwn.carvinglabs.CLConsumption;
import net.leloubil.mcpwn.mcapi.UserData;

import java.util.List;

@SuppressWarnings({"NullableProblems", "ConstantConditions"})
public class OrderAdapter extends RecyclerView.Adapter {

    private List<CLConsumption> orders;

    OrderAdapter(UserData userData) {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
