package com.stash.application.Action;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.stash.application.R;

public class HomeActivity extends AppCompatActivity implements  View.OnClickListener{
    private static final String TAG = "***************";
    private TextView homeScreenTextView ;
    private Button getStartedButton;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        homeScreenTextView = (TextView)findViewById(R.id.homeScreenTextContainerId);
        getStartedButton = (Button)findViewById(R.id.getStartedButton);
        getStartedButton.setOnClickListener(this);
        db = FirebaseFirestore.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();
        db.collection("general")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                homeScreenTextView.setSingleLine(false);
                                homeScreenTextView.setText((String)document.getData().get("home_welcome_message"));
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }
    @Override
    public void onClick(View view)
    {
        if(view==getStartedButton) {
            Intent intent = new Intent(this,InputActivity.class);
            startActivity(intent);

        }
    }
}