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

public class QuestionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<HashMap<String,Question>>  questionArrayList;


    private Context context;

    public QuestionsAdapter(ArrayList<HashMap<String,Question>> questionArrayList, Context context) {
        this.questionArrayList = questionArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        QuestionsItemBinding questionsItemBinding = DataBindingUtil.inflate(inflater, R.layout.questions_item, parent, false);

        return new QuestionViewHolder(questionsItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Question question = questionArrayList.get(position).get("passion");





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
