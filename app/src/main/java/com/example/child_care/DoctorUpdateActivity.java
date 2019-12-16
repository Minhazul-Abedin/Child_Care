package com.example.child_care;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.child_care.Models.DoctorUpdate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DoctorUpdateActivity extends AppCompatActivity {

    TextView TvName,age,bloodGroup,healthCondition,bmi;
    Button btnUpdate;
    ProgressDialog progressDialog;
    FirebaseDatabase database;
    DatabaseReference mReference;
    private String name,ageS,blood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_update);

        TvName = findViewById(R.id.name_doctor_update);
        age = findViewById(R.id.age_doctor_update);
        bloodGroup = findViewById(R.id.blood_group_doctor_update);
        healthCondition = findViewById(R.id.health_condition_doctor_update);
        bmi = findViewById(R.id.bmi_doctor_update);
        btnUpdate = findViewById(R.id.update_button_doctor_update);

       database =FirebaseDatabase.getInstance();
       progressDialog = new ProgressDialog(this);

        Intent myIntent = getIntent();
        if (myIntent != null)
        {
            name = myIntent.getStringExtra("name");
            ageS = myIntent.getStringExtra("age");
            blood = myIntent.getStringExtra("bloodGroup");
        }

        TvName.setText(name);
        age.setText(ageS);
        bloodGroup.setText(blood);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(TvName.getText().toString())) {
                    TvName.setError("This field can't be empty");
                    TvName.requestFocus();
                }

                if (TextUtils.isEmpty(age.getText().toString())) {
                    age.setError("This field can't be empty");
                    age.requestFocus();
                }
                if (TextUtils.isEmpty(bloodGroup.getText().toString())) {
                    bloodGroup.setError("This field can't be empty");
                    bloodGroup.requestFocus();
                }
                if (TextUtils.isEmpty(healthCondition.getText().toString())) {
                    healthCondition.setError("This field can't be empty");
                    healthCondition.requestFocus();
                }
                if (TextUtils.isEmpty(bmi.getText().toString())) {
                    bmi.setError("This field can't be empty");
                    bmi.requestFocus();
                } else {

                    progressDialog.setTitle("Please Wait");
                    progressDialog.setMessage("We are updating you data");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    DoctorUpdate doctorUpdate = new DoctorUpdate(TvName.getText().toString(), age.getText().toString(), bloodGroup.getText().toString(), healthCondition.getText().toString(), bmi.getText().toString());

                    mReference = database.getReference("DoctorUpdate").push();

                    mReference.setValue(doctorUpdate).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            progressDialog.dismiss();
                            Toast.makeText(DoctorUpdateActivity.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(DoctorUpdateActivity.this, dochome.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            DoctorUpdateActivity.this.finish();

                        }
                    });


                }


            }
        });
    }
}
