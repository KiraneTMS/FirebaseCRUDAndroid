package com.example.crudfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.crudfirebase.databinding.ActivityAddCourseBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddCourseActivity extends AppCompatActivity {

    private ActivityAddCourseBinding binding;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private String courseID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityAddCourseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Course");

        binding.idBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.idPBLoading.setVisibility(View.VISIBLE);
                String courseName = binding.idEdtCourseName.getText().toString();
                String courseFor = binding.idEdtCourseFor.getText().toString();
                String coursePrice = binding.idEdtCoursePrice.getText().toString();
                String courseImageLink = binding.idEdtCourseImageLink.getText().toString();
                String courseLink = binding.idEdtCourseLink.getText().toString();
                String courseDescription = binding.idEdtCourseDescription.getText().toString();

                courseID = courseName;
                CourseRVModal courseRVModal = new CourseRVModal(courseName, coursePrice, courseFor, courseImageLink, courseLink, courseDescription, courseID);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child(courseID).setValue(courseRVModal);
                        Toast.makeText(AddCourseActivity.this, "Course Added!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddCourseActivity.this, MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(AddCourseActivity.this, "Error is "+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}