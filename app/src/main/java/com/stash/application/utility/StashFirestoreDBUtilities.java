package com.stash.application.utility;
/*
* @author harsh mishra
* */
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.common.util.MapUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.stash.application.constants.StashConstants;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  Interacts with Firestore instance
 *  Singleton class
 */
public class StashFirestoreDBUtilities {

    private static final String TAG = StashConstants.LOG_TAG ;
    public static FirebaseFirestore db;
    StashFirestoreDBUtilities(){
        Log.d("::::::::::",":::entered into constructor");
        db = FirebaseFirestore.getInstance();
    }


    /**
     * @param fetchTag
     * @return the display String for Home Screen
     */
    public static String getDisplayMessageForWelcomeScreen(final String fetchTag) {
        final String[] result = {null};
        db = FirebaseFirestore.getInstance();
        db.collection(StashConstants.FIRESTORE_DB_COLLECTIONS.GENERAL).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Map<String,Object> resultMap;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                resultMap =document.getData();
                                if(null!=resultMap){
                                    Log.d(TAG, document.getId() + " => " + document.getData());
                                    result[0] = (String)document.getData().get(fetchTag);
                                }

                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

        if(null!=result&&StringUtils.isNotBlank(result[0])){
            return result[0];
        }
        return StashConstants.UI_MESSAGES.ERROR_MESSAGE.OOPS;
    }




    public static Map<String,Integer> getStatesData (final String key){

        db = FirebaseFirestore.getInstance();
        final HashMap[] myresMap = new HashMap[1];
        db.collection(StashConstants.FIRESTORE_DB_COLLECTIONS.GENERAL).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Map<String,Object> resultMap;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                resultMap =document.getData();
                                if(null!=resultMap){
                                    Log.d(TAG, document.getId() + " => " + document.getData());
                                    List mylist = (List) document.getData().get(key);
                                     myresMap[0] = (HashMap) mylist.get(0);
                                }else{
                                    Log.d("****","empty map");
                                }

                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

       return  myresMap[0];
    }


}
