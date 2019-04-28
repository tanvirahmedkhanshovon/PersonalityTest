package com.tanvir.personalitytest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tanvir.personalitytest.R;
import com.tanvir.personalitytest.databinding.QuestionsItemBinding;
import com.tanvir.personalitytest.model.Question;

import java.util.ArrayList;
import java.util.HashMap;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder> {

    ArrayList<Question> questionArrayList;


    private Context context;

    public QuestionsAdapter(ArrayList<Question> questionArrayList, Context context) {
        this.questionArrayList = questionArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        QuestionsItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.questions_item,parent,false);
        return new QuestionViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        Question question = questionArrayList.get(position);

        holder.binding.setQuestion(question);

    }



    @Override
    public int getItemCount() {
        return questionArrayList != null ? questionArrayList.size() : 0;
    }

    class QuestionViewHolder extends RecyclerView.ViewHolder {
        private QuestionsItemBinding binding;

        public QuestionViewHolder(@NonNull QuestionsItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
