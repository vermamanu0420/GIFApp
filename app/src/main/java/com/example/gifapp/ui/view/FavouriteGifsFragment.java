package com.example.gifapp.ui.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gifapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavouriteGifsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavouriteGifsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "position";

    // TODO: Rename and change types of parameters
    private String position;

    public FavouriteGifsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FavouriteGifsFragment newInstance(Integer param2) {
        FavouriteGifsFragment fragment = new FavouriteGifsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favoutites, container, false);
    }
}