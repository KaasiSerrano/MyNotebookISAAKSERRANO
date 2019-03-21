package com.example.jsontime;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ImageFragment extends Fragment {

    private static final String TAG = "ImageFragment";
    ImageView imageView;
public ImageFragment()
{

}



    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(ImageFragment.this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.imagefragment,container,false);
        imageView = view.findViewById(R.id.imageView);
        return view;    }

        @SuppressWarnings("unused")
        @Subscribe(threadMode = ThreadMode.MAIN)
        public void imageEvent(ImageEvent imageEvent)
    {
        if(imageEvent.imageURL != null)
        {
            String url = imageEvent.imageURL;
            Toast.makeText(getContext(), "ImageEvent", Toast.LENGTH_SHORT).show();
            Log.d(TAG, imageEvent.imageURL);
            Glide.with(getActivity()).load(url).into(imageView);
        }
    }



    @Override
    public void onStop() {
        EventBus.getDefault().unregister(ImageFragment.this);
        super.onStop();

    }
}
