package com.example.gifapp.ui.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gifapp.database.FavouriteGif;
import com.example.gifapp.databinding.FragmentFavoutitesBinding;
import com.example.gifapp.ui.view.adapter.FavouritesGridAdapter;
import com.example.gifapp.ui.view.adapter.OnItemClickListener;
import com.example.gifapp.viewmodel.GifsSearchViewModel;

import java.util.ArrayList;

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
    private GifsSearchViewModel gifsSearchViewModel;
    private FragmentFavoutitesBinding binding;
    private FavouritesGridAdapter adapter;

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
            //position = getArguments().getString(ARG_PARAM1);
        }
        gifsSearchViewModel = new ViewModelProvider(this).get(GifsSearchViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFavoutitesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final RecyclerView recyclerView = binding.favGifsList;
        adapter = new FavouritesGridAdapter(new ArrayList<>(), new OnItemClickListener() {
            @Override
            public void onUnFavClick(FavouriteGif item) {
                Toast.makeText(getActivity(),"Removing from favorites",Toast.LENGTH_LONG).show();
                gifsSearchViewModel.DeleteData(item);
            }
            @Override
            public void onFavClick(FavouriteGif item) { }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(root.getContext(), 2));
        recyclerView.setAdapter(adapter);
        observerViewModel();
        return root;
    }

    private void observerViewModel() {
        gifsSearchViewModel.getFavouriteGifs().observe(getViewLifecycleOwner(), items -> {
            adapter.updateImages(items);
        });
    }
}