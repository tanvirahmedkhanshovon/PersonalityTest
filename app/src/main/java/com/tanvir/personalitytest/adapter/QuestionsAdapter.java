package com.tanvir.personalitytest.adapter;

import android.content.Context;
import android.icu.lang.UCharacter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tanvir.personalitytest.R;
import com.tanvir.personalitytest.databinding.QuestionsItemBinding;
import com.tanvir.personalitytest.model.Answers;
import com.tanvir.personalitytest.model.Question;

import java.util.ArrayList;


public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder> {

    private static final String TAG = "QuestionsAdapter";
    public static ArrayList<Answers> answerList = new ArrayList<>();
    private ArrayList<Question> questionArrayList;
    private Context context;

    public QuestionsAdapter(ArrayList<Question> questionArrayList, Context context) {
        this.questionArrayList = questionArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        QuestionsItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.questions_item, parent, false);
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

    public ArrayList<Question> getQuestionArrayList() {

        return questionArrayList;
    }

    class QuestionViewHolder extends RecyclerView.ViewHolder {
        private QuestionsItemBinding binding;

        public QuestionViewHolder(@NonNull QuestionsItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
//            if(binding.rg.getCheckedRadioButtonId()!=R.id.opt3){
//                if(questionArrayList.get(getAdapterPosition()).getQuestionType().getCondition()!=null) {
//
//                    binding.subquestionTitle.setVisibility(View.VISIBLE);
//                    binding.rangecontent.setVisibility(View.VISIBLE);
//
//                }
//                else {
//
//                    binding.subquestionTitle.setVisibility(View.GONE);
//                    binding.rangecontent.setVisibility(View.GONE);
//
//                }
//            }

        }
    }

    public class RadioButtonClickHandler {
        private int position;
        private String question;
        private String answer;


        public RadioButtonClickHandler(int position) {
            this.position = position;

            question = questionArrayList.get(position).getQuestion();
        }

        public void onOpt1Clicked(View v) {
            answer = questionArrayList.get(position).getQuestionType().getOptions().get(0);

            answerList.add(new Answers(question, answer));


        }

        public void onOpt2Clicked(View v) {

            answer = questionArrayList.get(position).getQuestionType().getOptions().get(1);

            answerList.add(new Answers(question, answer));

        }

        public void onOpt3Clicked(View v) {

            answer = questionArrayList.get(position).getQuestionType().getOptions().get(2);

            answerList.add(new Answers(question, answer));

        }

        public void onOpt4Clicked(View v) {
            answer = questionArrayList.get(position).getQuestionType().getOptions().get(3);

            answerList.add(new Answers(question, answer));
        }

        public void onOpt5Clicked(View v) {
            answer = questionArrayList.get(position).getQuestionType().getOptions().get(4);

            answerList.add(new Answers(question, answer));
        }
    }


}
