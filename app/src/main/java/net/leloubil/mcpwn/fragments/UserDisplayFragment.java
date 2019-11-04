package net.leloubil.mcpwn.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import lombok.AllArgsConstructor;
import net.leloubil.mcpwn.R;
import net.leloubil.mcpwn.UserAdapter;
import net.leloubil.mcpwn.mcapi.UserData;

import java.util.function.Consumer;

@SuppressWarnings("NullableProblems")
@AllArgsConstructor
public class UserDisplayFragment extends Fragment {

    private Consumer<View> onFabCLick;

    private Consumer<UserData> userData;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        long start = System.currentTimeMillis();
        final View vieww = inflater.inflate(R.layout.fragment_user_display, container, false);
        RecyclerView view = vieww.findViewById(R.id.recyclerview_users);
        view.setLayoutManager(new LinearLayoutManager(getContext()));
        view.setAdapter(new UserAdapter(userData));
        vieww.findViewById(R.id.floatingActionButton).setOnClickListener(this::onActionClick);
        long end = System.currentTimeMillis();
        Log.d("Time limiterdsdf ", "ddd: display took " + (end - start) + "ms");
        return vieww;
    }

    private void onActionClick(View fab) {
        onFabCLick.accept(fab);
    }


}
