package com.example.amr.toregistrationasheads;

import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText txtPassword;
    String txtEmailAddress;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtPassword = (EditText) findViewById(R.id.txtPasswordRegistration);

        Spinner staticSpinner = (Spinner) findViewById(R.id.static_spinner);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.brew_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);

        staticSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String s = ((String) parent.getItemAtPosition(position));

                if (s.equals("IT")) {
                    txtEmailAddress = "sara123@gmail.com";
                }
                if (s.equals("Projects")) {
                    txtEmailAddress = "yara123@gmail.com";
                }
                if (s.equals("Laravel")) {
                    txtEmailAddress = "adam123@gmail.com";
                }
                if (s.equals("PR")) {
                    txtEmailAddress = "menna123@gmail.com";
                }
                if (s.equals("HR")) {
                    txtEmailAddress = "doha123@gmail.com";
                }
                if (s.equals("FR")) {
                    txtEmailAddress = "yokka123@gmail.com";
                }
                if (s.equals("LR")) {
                    txtEmailAddress = "zyad123@gmail.com";
                }
                if (s.equals("Art")) {
                    txtEmailAddress = "dina123@gmail.com";
                }
                if (s.equals("CCC")) {
                    txtEmailAddress = "mirna123@gmail.com";
                }
                if (s.equals("Blender")) {
                    txtEmailAddress = "saadya123@gmail.com";
                }
                if (s.equals("Linux")) {
                    txtEmailAddress = "islam123@gmail.com";
                }
                if (s.equals("English Heros")) {
                    txtEmailAddress = "shrook123@gmail.com";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void btnRegistrationUser_Click(View v) {

        if (txtPassword.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "Forget Enter Your Password", Toast.LENGTH_SHORT).show();
        } else {
            if (isNetworkAvailable()) {
                final ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this, "Please wait...", "Processing...", true);
                (firebaseAuth.createUserWithEmailAndPassword(txtEmailAddress, txtPassword.getText().toString()))
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();

                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Registration successful", Toast.LENGTH_LONG).show();
                                    txtPassword.setText("");
                                } else {
                                    Toast.makeText(MainActivity.this, "Invalid .. Please Try Again", Toast.LENGTH_LONG).show();
                                    txtPassword.setText("");
                                }
                            }
                        });
            } else {
                Toast.makeText(MainActivity.this, "No Internet", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
