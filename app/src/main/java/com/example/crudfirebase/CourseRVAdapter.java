package com.example.crudfirebase;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseRVAdapter extends RecyclerView.Adapter<CourseRVAdapter.ViewHolder> {
    private ArrayList<CourseRVModal> courseRVModalsArrayList;
    private Context context;
    int lastPost = -1;
    private CourseClickInterface courseClickInterface;

    public CourseRVAdapter(ArrayList<CourseRVModal> courseRVModalsArrayList, Context context, CourseClickInterface courseClickInterface) {
        this.courseRVModalsArrayList = courseRVModalsArrayList;
        this.context = context;
        this.courseClickInterface = courseClickInterface;
    }

    @NonNull
    @Override
    public CourseRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseRVAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public interface CourseClickInterface{
        void onCourseClick(int position);
    }
}
