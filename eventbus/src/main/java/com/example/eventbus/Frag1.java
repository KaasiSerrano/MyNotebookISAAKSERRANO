package com.example.eventbus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class Frag1 extends Fragment{
TextView btn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_1_layout, container, false);
        btn =  v.findViewById(R.id.tv_1);
        return v;
    }
//Subscribe method must be caleld on the fragment that needs it.
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        btn.setText(event.Message);
    }
//This line would be onStart if this wen't a fragment
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //this registers this fragment to recieve any EventBus
        EventBus.getDefault().register(this);
    }

//Since this is a fragment this should be onDetatch
    @Override
    public void onStop() {
        super.onStop();
        //this unregister this fragment to stop any EventBus messages
        EventBus.getDefault().unregister(this);
    }

}
