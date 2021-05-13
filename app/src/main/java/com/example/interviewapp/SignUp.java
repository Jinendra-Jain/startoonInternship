package com.example.interviewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.user;

public class SignUp extends AppCompatActivity {

    ImageButton vBackArrowSignUpActivity,vForwardArrowImageButtonLoginActivity;
    EditText vEditTextUserNameSignUpActivity,vEditTextPhoneSignUpActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().hide();

        try {
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.configure(getApplicationContext());

            Log.i("Tutorial", "Initialized Amplify");
        } catch (AmplifyException e) {
            Log.e("Tutorial", "Could not initialize Amplify", e);
        }

        vBackArrowSignUpActivity = findViewById(R.id.backArrowSignUpActivity);
        vForwardArrowImageButtonLoginActivity = findViewById(R.id.forwardArrowImageButtonLoginActivity);
        vEditTextUserNameSignUpActivity = findViewById(R.id.editTextUserNameSignUpActivity);
        vEditTextPhoneSignUpActivity = findViewById(R.id.editTextPhoneSignUpActivity);

        vBackArrowSignUpActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vLoginIntentSignUpActivity = new Intent(SignUp.this, Login.class);
                startActivity(vLoginIntentSignUpActivity);
            }
        });

        vForwardArrowImageButtonLoginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user item = user.builder()
                        .username(vEditTextUserNameSignUpActivity.getText().toString())
                        .mobilenumber(vEditTextPhoneSignUpActivity.getText().toString())
                        .build();

                Amplify.DataStore.save(item,
                        success -> Log.i("Tutorial", "Saved item: " + success.item().getUsername()),
                        error -> Log.e("Tutorial", "Could not save item to DataStore", error)
                );

                Intent vLoginIntentSignUpActivity = new Intent(SignUp.this, Login.class);
                startActivity(vLoginIntentSignUpActivity);
            }
        });

    }

}