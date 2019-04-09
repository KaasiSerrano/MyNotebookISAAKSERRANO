package com.example.catapi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.catapi.adapter.BreedAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


public class CatFragment extends Fragment {
    ListView listView;
    RecyclerView recyclerView;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.catapi_layout_fragment, container, false);
        listView = view.findViewById(R.id.list_view);
        recyclerView = view.findViewById(R.id.rv_shibidawg);



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
