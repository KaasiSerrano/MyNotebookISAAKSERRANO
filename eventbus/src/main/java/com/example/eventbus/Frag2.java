package com.example.eventbus;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

public class Frag2 extends Fragment implements View.OnClickListener {

Button btn;
EditText one,two;
TextView resu;
int results;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_2_layout, container, false);

        btn =  v.findViewById(R.id.button2);
        one =  v.findViewById(R.id.editText1);
        two = v.findViewById(R.id.editText2);




        btn.setOnClickListener(this);

        return v;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button2:

                String mynum1=two.getText().toString();
                String mynum2=one.getText().toString();


                results =Integer.parseInt(mynum1)+Integer.parseInt(mynum2);
//EventBus will give this message to everyone that is set to a @Subscribe as in Frag1
                EventBus.getDefault().post(new MessageEvent(Integer.toString(results)));
                break;
        }

    }

}
