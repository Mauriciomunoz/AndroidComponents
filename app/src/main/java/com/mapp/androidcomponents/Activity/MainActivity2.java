package com.mapp.androidcomponents.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.mapp.androidcomponents.R;
import com.mapp.androidcomponents.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{

    private ActivityMain2Binding bindingMainActivity2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindingMainActivity2 = DataBindingUtil.setContentView(this, R.layout.activity_main2);
        bindingMainActivity2.btnClose.setOnClickListener(this);
        bindingMainActivity2.onCreate.setText("Binding linked. Layout set.");
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindingMainActivity2.onStart.setText("OnStart: View visible");
    }

    @Override
    protected void onResume(){
        super.onResume();
        bindingMainActivity2.onResume.setText("OnResume: Now I am interactable");
        bindingMainActivity2.onPause.setText("");
    }

    @Override
    protected void onPause(){
        super.onPause();
        bindingMainActivity2.onResume.setText("");
        bindingMainActivity2.onRestart.setText("");
        bindingMainActivity2.onPause.setText("OnPause: I am going to Background");
    }

    @Override
    protected void onStop(){
        super.onStop();
        bindingMainActivity2.onStop.setText("OnStop: After OnPause called. Activated in background");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        bindingMainActivity2.onDestroy.setText("OnDestroy: Closed");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        bindingMainActivity2.onRestart.setText("OnRestart: I back from background and I call onResume");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnClose:
                //If Finish() is called from onCreate, it doesn't will call onStop and onPause and goes directly to onDestroy
                finish();
                break;

        }
    }
}