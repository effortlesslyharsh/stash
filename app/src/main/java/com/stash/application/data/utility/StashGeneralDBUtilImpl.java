package com.stash.application.data.utility;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.stash.application.constants.StashConstants;
import com.stash.application.data.models.StashGeneral;

import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class StashGeneralDBUtilImpl implements StashGeneralDBUtil {
    private static final String TAG = "Stash general db UtilImpl";

    @Override
    public StashGeneral loadGeneralObjectInstanceFromDB() {
        Log.d("Entry","loadGeneralObjectInstanceFromDB");
        StashDataAccessTaskLifeCycleUtil stashDataAccessTaskLifeCycleUtil =new StashDataAccessTaskLifeCycleUtil();
        stashDataAccessTaskLifeCycleUtil.setTaskStartTime(Calendar.getInstance().getTimeInMillis());
        CountDownLatch countDownLatch = new CountDownLatch(1);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        StashGeneral stashGeneral = new StashGeneral();
        db.collection(StashConstants.FIRESTORE_DB_COLLECTIONS.GENERAL).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        stashDataAccessTaskLifeCycleUtil.setTaskCompleted(true);

                        if (task.isSuccessful()) {
                            Map<String, Object> resultMap;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                resultMap = document.getData();
                                countDownLatch.countDown();
                                if (null != resultMap) {
                                    Log.d(TAG, document.getId() + " => " + document.getData());
                                    document.getData().forEach((k, v) -> {
                                        switch (k) {
                                            case StashConstants.FIRESTORE_DB_COLLECTION_FIELD_TAGS.GENERAL.HOME_WELCOME_MESSAGE:
                                                Log.d(TAG,"setting home display message");
                                                stashGeneral.setHomeDisplayMessage((String) v);
                                                break;
                                            case StashConstants.FIRESTORE_DB_COLLECTION_FIELD_TAGS.GENERAL.STATES_DATA:
                                                Log.d(TAG,"setting states data");
                                                stashGeneral.setStatesIdmap((Map<String, Integer>) v);
                                                break;
                                        }

                                    });
                                }

                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
        Log.d("Exit","loadGeneralObjectInstanceFromDB");
      try{
          countDownLatch.await(StashConstants.DB_FETCH_TIMEOUT_MILLISECONDS,TimeUnit.MILLISECONDS);

      }catch(Exception e){
          Log.d("EXcpetion",e.getMessage());
      }
        Log.d("before leaving",stashGeneral.getHomeDisplayMessage()+":::::::"+stashGeneral.getStatesIdmap());
        return stashGeneral;
    }

}
