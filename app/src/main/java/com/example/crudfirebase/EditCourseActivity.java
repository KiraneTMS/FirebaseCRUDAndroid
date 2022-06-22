package com.example.crudfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.crudfirebase.databinding.ActivityAddCourseBinding;
import com.example.crudfirebase.databinding.ActivityEditCourseBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditCourseActivity extends AppCompatActivity {

    private ActivityEditCourseBinding binding;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private String courseID;
    private CourseRVModal courseRVModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditCourseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseDatabase = FirebaseDatabase.getInstance();
        courseRVModal = getIntent().getParcelableExtra("course");
        if (courseRVModal!=null){
            binding.idEdtCourseName.setText(courseRVModal.getName());
            binding.idEdtCoursePrice.setText(courseRVModal.getPrice());
            binding.idEdtCourseFor.setText(courseRVModal.getSuitedFor());
            binding.idEdtCourseLink.setText(courseRVModal.getLink());
            binding.idEdtCourseImageLink.setText(courseRVModal.getImageLink());
            binding.idEdtCourseDescription.setText(courseRVModal.getDescription());
            courseID = courseRVModal.getID();
        }

        databaseReference = firebaseDatabase.getReference("Course").child(courseID);
        binding.idBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.idPBLoading.setVisibility(View.VISIBLE);
                String courseName = binding.idEdtCourseName.getText().toString();
                String courseFor = binding.idEdtCourseFor.getText().toString();
                String coursePrice = binding.idEdtCoursePrice.getText().toString();
                String courseImageLink = binding.idEdtCourseImageLink.getText().toString();
                String courseLink = binding.idEdtCourseLink.getText().toString();
                String courseDescription = binding.idEdtCourseDescription.getText().toString();

                Map<String, Object> map = new HashMap<>();
                map.put("name", courseName);
                map.put("suitedFor", courseFor);
                map.put("price", coursePrice);
                map.put("imageLink", courseImageLink);
                map.put("link", courseLink);
                map.put("description", courseDescription);
                map.put("ID", courseID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        binding.idPBLoading.setVisibility(View.GONE);
                        databaseReference.updateChildren(map);
                        Toast.makeText(EditCourseActivity.this, "Course Edited!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditCourseActivity.this, MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(EditCourseActivity.this, "Course Edit Failed!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        binding.idBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCourse();
            }
        });
    }

    private void deleteCourse(){
        databaseReference.removeValue();
        Toast.makeText(EditCourseActivity.this, "Course Deleted!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(EditCourseActivity.this, MainActivity.class));

    }
}