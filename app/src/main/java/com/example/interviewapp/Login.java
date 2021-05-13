package com.example.interviewapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.user;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class Login extends AppCompatActivity {

    ImageButton vForwardArrowImageButtonLoginActivity;
    TextView vSignUpTextViewLoginActivity,vEditTextPhone;
    String vUserPhoneNumber,mVerificationId;
    PhoneAuthProvider.ForceResendingToken mResendToken;
    FirebaseAuth mAuth;
    private Spinner spinner;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private static final String KEY_VERIFICATION_ID = "key_verification_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.configure(getApplicationContext());

            Log.i("Tutorial", "Initialized Amplify");
        } catch (AmplifyException failure) {
            Log.e("Tutorial", "Could not initialize Amplify", failure);
        }

        Amplify.DataStore.observe(user.class,
                started -> Log.i("Tutorial", "Observation began."),
                change -> Log.i("Tutorial", change.item().toString()),
                failure -> Log.e("Tutorial", "Observation failed.", failure),
                () -> Log.i("Tutorial", "Observation complete.")
        );

        Amplify.DataStore.query(user.class,
                users -> {
                    while (users.hasNext()) {
                        user u = users.next();

                        Log.i("Tutorial", "==== User Info ====");
                        Log.i("Tutorial", "User Name: " + u.getUsername());

                        if(u.getUsername() != null) {
                            Log.i("Tutorial", "Description: " + u.getMobilenumber());
                        }
                    }
                },
                failure -> Log.e("Tutorial", "Could not query DataStore", failure)
        );



        mAuth = FirebaseAuth.getInstance();
        vEditTextPhone = findViewById(R.id.editTextPhone);
        vSignUpTextViewLoginActivity = (TextView) findViewById(R.id.signUpTextViewLoginActivity);
        vForwardArrowImageButtonLoginActivity = (ImageButton) findViewById(R.id.forwardArrowImageButtonLoginActivity);
        spinner = findViewById(R.id.spinnerCountries);
        spinner.setAdapter(new ArrayAdapter<String>(Login.this, android.R.layout.simple_spinner_dropdown_item, CountryData.countryNames));
        vEditTextPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Login.this, "Phone Number is required", Toast.LENGTH_LONG).show();
            }
        });

        vSignUpTextViewLoginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vSignUpIntentLoginActivity = new Intent(Login.this, SignUp.class);
                startActivity(vSignUpIntentLoginActivity);
            }
        });

        vForwardArrowImageButtonLoginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = CountryData.countryAreaCodes[spinner.getSelectedItemPosition()];

                vUserPhoneNumber = vEditTextPhone.getText().toString().trim();

                if (vUserPhoneNumber.isEmpty() || vUserPhoneNumber.length() < 10) {
                    vEditTextPhone.setError("Valid Phone number is required");
                    vEditTextPhone.requestFocus();
                    return;
                }
                String phoneNumber = "+" + code + vUserPhoneNumber;


                Intent vLoginOTPIntentLoginActivity = new Intent(Login.this, LoginOTP.class);
                vLoginOTPIntentLoginActivity.putExtra("phonenumber",phoneNumber);
                startActivity(vLoginOTPIntentLoginActivity);
            }
        });

    }

}