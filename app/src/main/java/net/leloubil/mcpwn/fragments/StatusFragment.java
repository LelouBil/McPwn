package net.leloubil.mcpwn.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import io.sentry.Sentry;
import lombok.Getter;
import lombok.NonNull;
import net.leloubil.mcpwn.DataManagement;
import net.leloubil.mcpwn.R;
import net.leloubil.mcpwn.UserAdapter;
import net.leloubil.mcpwn.activities.MainActivity;
import net.leloubil.mcpwn.frontpage.SpecialDataGetter;
import net.leloubil.mcpwn.mcapi.UserData;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.stream.Collectors;


@SuppressWarnings({"NullableProblems", "ConstantConditions"})
public class StatusFragment extends Fragment {

    @Getter
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View machin = inflater.inflate(R.layout.fragment_status, container, false);
        view = machin;
        UserData most = SpecialDataGetter.mostPoints();


        UserData least = SpecialDataGetter.leastPoints();

        if (most != null) populate(most, view.findViewById(R.id.mostPoints));

        if (least != null) populate(least, view.findViewById(R.id.leastPoints));

        RecyclerView rv = view.findViewById(R.id.ListLessFifteen);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(new UserAdapter(R.layout.layout_item_min, data -> ((MainActivity) getActivity()).loadFragment(UserInfoFragment.newInstance(data)),
                DataManagement.getUserDataList().stream().filter(u -> u.getMemberShip().getNbPoints() <= 15).sorted(Comparator.comparingLong(u -> u.getMemberShip().getNbPoints())).collect(Collectors.toList())));

        RecyclerView rrv = view.findViewById(R.id.ListMoreFourty);
        rrv.setLayoutManager(new LinearLayoutManager(getContext()));
        rrv.setAdapter(new UserAdapter(R.layout.layout_item_min, data -> ((MainActivity) getActivity()).loadFragment(UserInfoFragment.newInstance(data)),
                DataManagement.getUserDataList().stream().filter(u -> u.getMemberShip().getNbPoints() >= 40).sorted(Comparator.comparingLong((UserData u) -> u.getMemberShip().getNbPoints()).reversed()).collect(Collectors.toList())));

        return machin;
    }

    private void populate(UserData data, View layout) {
        layout.setOnClickListener(o -> ((MainActivity) getActivity()).loadFragment(UserInfoFragment.newInstance(data)));
        for (int index = 0; index < ((ViewGroup) layout).getChildCount(); ++index) {
            View nextChild = ((ViewGroup) layout).getChildAt(index);
            if (nextChild instanceof TextView) {
                TextView textView = (TextView) nextChild;
                String text = textView.getText().toString();
                if (text.startsWith("#") && text.endsWith("#")) {
                    String[] args = text.replaceAll("#", "").split(";");
                    Object lastO = data;
                    try {
                        for (int i = 0; i < args.length; i++) {
                            String arg = args[i];
                            Field f = lastO.getClass().getDeclaredField(arg);
                            f.setAccessible(true);
                            lastO = f.get(lastO);
                            if (i + 1 == args.length) {
                                textView.setText(String.valueOf(lastO));
                            }
                        }
                    } catch (IllegalAccessException | NoSuchFieldException e) {
                        Sentry.capture(e);
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
