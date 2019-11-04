package net.leloubil.mcpwn;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import lombok.NonNull;
import net.leloubil.mcpwn.mcapi.UserData;

import java.util.List;
import java.util.function.Consumer;

@SuppressWarnings("NullableProblems")
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserRecyclerViewHolder> {

    private int layout = R.layout.layout_item;
    private RecyclerView mRecyclerView;

    private Consumer<UserData> lst;

    private List<UserData> userDataList;

    public UserAdapter(Consumer<UserData> lst) {
        this.lst = lst;
        this.userDataList = DataManagement.getUserDataList();
    }

    public UserAdapter(Consumer<UserData> lst, List<UserData> dataList) {
        this.lst = lst;
        this.userDataList = dataList;
    }

    public UserAdapter(int layout, Consumer<UserData> lst, List<UserData> dataList) {
        this.lst = lst;
        this.layout = layout;
        this.userDataList = dataList;
    }

    @NonNull
    @Override
    public UserAdapter.UserRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mRecyclerView.getContext())
                .inflate(layout, mRecyclerView, false);

        return new UserRecyclerViewHolder(v, lst);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserRecyclerViewHolder userRecyclerViewHolder, int i) {
        if (i % 2 == 1) {
            userRecyclerViewHolder.itemView.setBackgroundColor(Color.CYAN);
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        } else {
            userRecyclerViewHolder.itemView.setBackgroundColor(Color.WHITE);
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFAF8FD"));
        }
        userRecyclerViewHolder.display(userDataList.get(i));
    }

    @Override
    public int getItemCount() {
        return userDataList.size();
    }

    static class UserRecyclerViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private final TextView usermail;
        private final TextView userRef;
        private final ImageView barcode;
        private final TextView points;
        private final View v;
        private UserData user;
        private Consumer<UserData> listener;


        UserRecyclerViewHolder(@NonNull View itemView, Consumer<UserData> listsner) {
            super(itemView);
            this.v = itemView;
            this.listener = listsner;
            usermail = itemView.findViewById(R.id.user_email);
            userRef = itemView.findViewById(R.id.user_ref);
            barcode = itemView.findViewById(R.id.barcodeView);
            points = itemView.findViewById(R.id.points_view);
        }


        @SuppressLint("SetTextI18n")
        void display(UserData userData) {

            user = userData;
            long start = System.currentTimeMillis();
            userData.getUserToken().getToken(token -> user.syncInfo(userinfo -> {
                if (usermail != null) usermail.setText(user.getInfo().getEmail());
                userRef.setText(user.getInfo().getExternalRef());
                //userData.loadBarCode(barcode);
                points.setText(userData.getMemberShip().getNbPoints().toString());
            }));
            itemView.setOnClickListener(v -> listener.accept(userData));
            long end = System.currentTimeMillis();
            Log.d("Time limiterdsdf ", "ddd: display took " + (end - start) + "ms");
        }

    }
}
