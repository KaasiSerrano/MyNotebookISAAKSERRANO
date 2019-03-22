package com.example.jsontime;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jsontime.listview.ShibeAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ImageFragment extends Fragment {

    private static final String TAG = "ImageFragment";
    ListView listView;
    RecyclerView recyclerView;



    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(ImageFragment.this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.imagefragment,container,false);
        listView = view.findViewById(R.id.list_view);
        recyclerView = view.findViewById(R.id.list_view);

        return view;
}

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(null);
    }

    @SuppressWarnings("unused")
        @Subscribe(threadMode = ThreadMode.MAIN)
        public void imageEvent(ImageEvent imageEvent)
    {
        if(imageEvent.imageURL != null)
        {
            String url = imageEvent.imageURL;
            Toast.makeText(getContext(), "ImageEvent", Toast.LENGTH_SHORT).show();

            Log.d(TAG, imageEvent.imageURL);

//            Glide.with(getActivity()).load(url).into(imageView);

            listView.setAdapter(new ShibeAdapter(getContext(),imageEvent.imageUrls));
        }
    }



    @Override
    public void onStop() {
        EventBus.getDefault().unregister(ImageFragment.this);
        super.onStop();

    }
}
