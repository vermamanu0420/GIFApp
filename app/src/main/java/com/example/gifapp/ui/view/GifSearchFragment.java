package com.example.gifapp.ui.view;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gifapp.R;
import com.example.gifapp.database.FavouriteGif;
import com.example.gifapp.databinding.FragmentMainBinding;
import com.example.gifapp.model.GifData;
import com.example.gifapp.ui.view.adapter.GifsListAdapter;
import com.example.gifapp.ui.view.adapter.OnItemClickListener;
import com.example.gifapp.viewmodel.GifsSearchViewModel;

import java.util.ArrayList;

public class GifSearchFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private GifsSearchViewModel gifsSearchViewModel;
    private FragmentMainBinding binding;
    private GifsListAdapter adapter;

    public static GifSearchFragment newInstance(int index) {
        GifSearchFragment fragment = new GifSearchFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gifsSearchViewModel = new ViewModelProvider(this).get(GifsSearchViewModel.class);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentMainBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final RecyclerView recyclerView = binding.gifsList;
        adapter = new GifsListAdapter(new ArrayList<>(), new OnItemClickListener() {
            @Override
            public void onUnFavClick(FavouriteGif item) {
                Toast.makeText(getActivity(),"Adding to favorites",Toast.LENGTH_LONG).show();
                gifsSearchViewModel.InsertData(item);

            }
            @Override
            public void onFavClick(FavouriteGif item) {
                Toast.makeText(getActivity(),"Removing to favorites",Toast.LENGTH_LONG).show();
                gifsSearchViewModel.DeleteData(item);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setAdapter(adapter);

        observerViewModel();

        gifsSearchViewModel.fetchTrendingGifs(true);

        binding.searchButton.setOnClickListener(v -> {
            gifsSearchViewModel.fetchGifs(String.valueOf(binding.searchTextview.getText()), true);
        });

        binding.searchTextview.setOnEditorActionListener((v, actionId, event) -> {
            if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                binding.searchButton.performClick();
            }
            return false;
        });

        binding.gifsList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(1)) {
                    if (!binding.searchTextview.getText().toString().equals(""))
                        gifsSearchViewModel.fetchGifs(binding.searchTextview.getText().toString(), false);
                    else
                        gifsSearchViewModel.fetchTrendingGifs(false);
                }
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void observerViewModel() {
        gifsSearchViewModel.getGifMutableLGifLiveData().observe(getViewLifecycleOwner(), items -> {
            binding.listError.setVisibility(View.GONE);
            adapter.updateImages(items);
            binding.loadingView.setVisibility(View.GONE);

            if (items == null || items.size() == 0) {
                binding.listError.setVisibility(View.VISIBLE);
                binding.listError.setText(R.string.loadingDataErrorMsg);
            }
        });


    }
}