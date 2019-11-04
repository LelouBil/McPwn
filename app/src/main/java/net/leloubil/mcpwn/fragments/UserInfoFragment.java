package net.leloubil.mcpwn.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import net.leloubil.mcpwn.R;
import net.leloubil.mcpwn.mcapi.UserData;


@SuppressWarnings("NullableProblems")
public class UserInfoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "userData";

    // TODO: Rename and change types of parameters
    private UserData userData;
    private View view;

    public UserInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment UserInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserInfoFragment newInstance(UserData param1) {
        UserInfoFragment fragment = new UserInfoFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.fragment_user_info, container, false);

        if (getArguments() != null) {
            userData = (UserData) getArguments().get(ARG_PARAM1);
        }
        //erreure, view était nul donc faut trouver pk
        userData.loadBarCode(view.findViewById(R.id.barcodeView));
        ((TextView) view.findViewById(R.id.pointsView)).setText(Integer.toString(Math.toIntExact(userData.getMemberShip().getNbPoints())));
        ((TextView) view.findViewById(R.id.idView)).setText(userData.getInfo().getExternalRef());
        ((RecyclerView) view.findViewById(R.id.recyclerView)).setAdapter(new OrderAdapter(userData));
        return view;
    }

}
