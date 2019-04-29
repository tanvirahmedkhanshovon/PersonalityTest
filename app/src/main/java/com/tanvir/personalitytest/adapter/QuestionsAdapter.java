package com.tanvir.personalitytest.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tanvir.personalitytest.R;
import com.tanvir.personalitytest.databinding.QuestionsItemBinding;
import com.tanvir.personalitytest.model.Question;

import java.util.ArrayList;
import java.util.HashMap;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder> {

    private static final String TAG = "QuestionsAdapter";
    private ArrayList<Question> questionArrayList;




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
        RadioButtonClickHandler radioButtonClickHandler = new RadioButtonClickHandler(position);
        Question question = questionArrayList.get(position);

        holder.binding.setQuestion(question);
        holder.binding.setRadioButton(radioButtonClickHandler);
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

   public class RadioButtonClickHandler{
        private int position;

       public RadioButtonClickHandler(int position) {
           this.position = position;
       }

       public void onOpt1Clicked(View v){


            Log.i(TAG,"SELECTED item " + questionArrayList.get(position).getQuestionType().getOptions().get(0));
        }
       public void onOpt2Clicked(View v){


           Log.i(TAG,"SELECTED item " + questionArrayList.get(position).getQuestionType().getOptions().get(1));
       }

       public void onOpt3Clicked(View v){


           Log.i(TAG,"SELECTED item " + questionArrayList.get(position).getQuestionType().getOptions().get(2));
       }
       public void onOpt4Clicked(View v){


           Log.i(TAG,"SELECTED item " + questionArrayList.get(position).getQuestionType().getOptions().get(3));
       }
       public void onOpt5Clicked(View v){


           Log.i(TAG,"SELECTED item " + questionArrayList.get(position).getQuestionType().getOptions().get(4));
       }
    }
}
