package com.stash.application.Action;
/*
 * @author harsh mishra
 * */

import androidx.appcompat.app.AppCompatActivity;

import com.stash.application.R;
import com.stash.application.processors.StashHomeScreenProcessor;
import com.stash.application.utility.StashFirestoreDBUtilities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Spinner;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class InputActivity extends AppCompatActivity {
    Spinner statesSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        statesSpinner = (Spinner) findViewById(R.id.statesSpinner);


    }
    @Override
    protected void onStart() {
        super.onStart();
        Map<String,Integer> stateVsIdMap;
        stateVsIdMap = StashFirestoreDBUtilities.getStatesData("states");
        if(null!=stateVsIdMap){
            stateVsIdMap.forEach((state,Id)->{
                Log.d("RES",state+":::"+Id);

            });
        }else{
            Log.d("error","empty result");
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        super.onStart();
        Map<String, Integer> stateVsIdMap;
        stateVsIdMap = StashFirestoreDBUtilities.getStatesData("states");
        if (null != stateVsIdMap) {
            stateVsIdMap.forEach((state, Id) -> {
                Log.d("RES", state + ":::" + Id);

            });
        } else {
            Log.d("error", "empty result");
        }
    }



}

