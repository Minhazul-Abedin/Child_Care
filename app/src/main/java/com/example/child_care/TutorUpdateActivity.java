package com.example.child_care;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.child_care.Models.DoctorUpdate;
import com.example.child_care.Models.TutorUpdate;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TutorUpdateActivity extends AppCompatActivity {

    TextView tvName,age,dailyHomework,studyTime;
    Button btnUpdate;
    ProgressDialog progressDialog;
    FirebaseDatabase database;
    DatabaseReference mReference;
    private String name,ageS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_update);

        tvName = findViewById(R.id.name_tutor_update);
        age = findViewById(R.id.age_tutor_update);
        dailyHomework = findViewById(R.id.work_tutor_update);
        studyTime = findViewById(R.id.study_time_tutor_update);
        btnUpdate = findViewById(R.id.update_btn_tutor_update);

        database = FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(this);

        Intent myIntent = getIntent();
        if (myIntent != null)
        {
            name = myIntent.getStringExtra("name");
            ageS = myIntent.getStringExtra("age");
        }

        tvName.setText(name);
        age.setText(ageS);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(tvName.getText().toString())) {
                    tvName.setError("This field can't be empty");
                    tvName.requestFocus();
                }

                if (TextUtils.isEmpty(age.getText().toString())) {
                    age.setError("This field can't be empty");
                    age.requestFocus();
                }
                if (TextUtils.isEmpty(dailyHomework.getText().toString())) {
                    dailyHomework.setError("This field can't be empty");
                    dailyHomework.requestFocus();
                }
                if (TextUtils.isEmpty(studyTime.getText().toString())) {
                    studyTime.setError("This field can't be empty");
                    studyTime.requestFocus();

                } else {

                    progressDialog.setTitle("Please Wait");
                    progressDialog.setMessage("We are updating you data");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    TutorUpdate tutorUpdate = new TutorUpdate(tvName.getText().toString(), age.getText().toString(), dailyHomework.getText().toString(), studyTime.getText().toString());

                    mReference = database.getReference("TutorUpdate").push();

                    mReference.setValue(tutorUpdate).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            progressDialog.dismiss();
                            Toast.makeText(TutorUpdateActivity.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(TutorUpdateActivity.this, tuitor_home.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            TutorUpdateActivity.this.finish();
                        }
                    });


                }
            }
        });

    }
}
