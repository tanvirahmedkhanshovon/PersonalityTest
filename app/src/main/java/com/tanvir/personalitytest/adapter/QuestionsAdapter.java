package com.tanvir.personalitytest.adapter;

import android.content.Context;
import android.icu.lang.UCharacter;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.radiobutton.MaterialRadioButton;
import com.tanvir.personalitytest.R;
import com.tanvir.personalitytest.databinding.QuestionsItemBinding;
import com.tanvir.personalitytest.model.Answers;
import com.tanvir.personalitytest.model.Question;

import java.util.ArrayList;


public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder> {

    private static final String TAG = "QuestionsAdapter";
    public static ArrayList<Answers> answerList;
    private ArrayList<Question> questionArrayList;
    private Context context;


    public ArrayList<Answers> getAnswerList() {
        return answerList;
    }

    public static boolean hasrange, min, max;


    public QuestionsAdapter(ArrayList<Question> questionArrayList, Context context) {
        this.questionArrayList = questionArrayList;
        answerList = new ArrayList<>(questionArrayList.size());
        this.context = context;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        QuestionsItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.questions_item, parent, false);
        return new QuestionViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull final QuestionViewHolder holder, final int position) {
        // RadioButtonClickHandler radioButtonClickHandler = new RadioButtonClickHandler(position);
        Question question = questionArrayList.get(position);
        holder.binding.setQuestion(question);
        // holder.binding.setRadioButton(radioButtonClickHandler);
        final Answers answers = new Answers();
        if(holder.binding.rg.getCheckedRadioButtonId()==-1) {
            holder.binding.rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {

                    String answer = "";
                    String question = questionArrayList.get(position).getQuestion();
                    switch (checkedId) {
                        case R.id.opt1:

                            answer = questionArrayList.get(position).getQuestionType().getOptions().get(0);
                            Log.i(TAG, answer);
                            //radioChecked = true;
                            holder.binding.conditionalItems.setVisibility(View.GONE);
                            hasrange = false;
                            break;
                        case R.id.opt2:
                            answer = questionArrayList.get(position).getQuestionType().getOptions().get(1);
                            holder.binding.conditionalItems.setVisibility(View.GONE);
                            hasrange = false;

                            //radioChecked = true;
                            break;
                        case R.id.opt3:
                            answer = questionArrayList.get(position).getQuestionType().getOptions().get(2);
                            if (questionArrayList.get(position).getQuestionType().getCondition() != null) {


                                holder.binding.conditionalItems.setVisibility(View.VISIBLE);

                                hasrange = true;
                                holder.binding.fromRange.addTextChangedListener(new TextWatcher() {
                                    @Override
                                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                    }

                                    @Override
                                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                                        if (Integer.parseInt(s.toString()) >= questionArrayList.get(position).getQuestionType().getCondition().getIfPositive().getQuestionType().getRange().getFrom()) {
                                            min = true;
                                            answers.setAgeMin(Integer.parseInt(s.toString()));
                                        }


                                    }

                                    @Override
                                    public void afterTextChanged(Editable s) {

                                    }
                                });
                                holder.binding.toRange.addTextChangedListener(new TextWatcher() {
                                    @Override
                                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                    }

                                    @Override
                                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                                        if (Integer.parseInt(s.toString()) <= questionArrayList.get(position).getQuestionType().getCondition().getIfPositive().getQuestionType().getRange().getTo()) {
                                            max = true;
                                            answers.setAgeMax(Integer.parseInt(s.toString()));
                                        }
                                    }

                                    @Override
                                    public void afterTextChanged(Editable s) {

                                    }
                                });
                            }
                            break;
                        case R.id.opt4:
                            answer = questionArrayList.get(position).getQuestionType().getOptions().get(3);
                            holder.binding.conditionalItems.setVisibility(View.GONE);
                            hasrange = false;
                            //  radioChecked = true;
                            break;
                        case R.id.opt5:
                            answer = questionArrayList.get(position).getQuestionType().getOptions().get(4);
                            holder.binding.conditionalItems.setVisibility(View.GONE);
                            hasrange = false;
                            // radioChecked = true;
                            break;

                        default:

                            holder.binding.opt1.setChecked(false);
                            holder.binding.opt2.setChecked(false);
                            holder.binding.opt3.setChecked(false);
                            holder.binding.opt4.setChecked(false);
                            holder.binding.opt5.setChecked(false);

                            break;


                    }


                    if (answer.matches(" ")) {
                        Log.i(TAG, "Coming HERE");
////                    radioChecked = false;
//                    answers.setAnswer("");
//                    answers.setQuestion(question);

                    } else {


                        //radioChecked = true;

                        answers.setAnswer(answer);
                        answers.setQuestion(question);

                        if (position >= answerList.size()) {
                            //index not exists
                            answerList.add(answers);

                        } else if (position <= answerList.size()) {


                            if (answerList.contains(question)) {
                                answerList.set(position, answers);

                            } else {

                                answerList.add(answers);
                            }

                        }

                    }
                }
            });
        }

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

//            if (getAdapterPosition() != RecyclerView.NO_POSITION) {
//                if (itemView.rg.getCheckedRadioButtonId()==-1) {
//                    Log.i(TAG,"Coming HERE");
////                    radioChecked = false;
//
//                }
//
//            }


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

//        public void bind(Answers answers) {
//
//            this.binding.setAnswers(answers);
//        }


//        public QuestionsItemBinding getBinding() {
//            return binding;
//        }
    }
//
//    public class RadioButtonClickHandler {
//        private int position;
//        private String question;
//        private String answer;
//
//
//        public RadioButtonClickHandler(int position) {
//            this.position = position;
//
//            //   question = questionArrayList.get(position).getQuestion();
//        }
//
//        public void onOpt1Clicked(View v) {
//            //   answer = questionArrayList.get(position).getQuestionType().getOptions().get(0);
//            // radioChecked = true;
//            // answerList.add(new Answers(question, answer));
//
//
//        }
//
//        public void onOpt2Clicked(View v) {
//
//            //  answer = questionArrayList.get(position).getQuestionType().getOptions().get(1);
//            // radioChecked = true;
//            //  answerList.add(new Answers(question, answer));
//
//        }
//
//        public void onOpt3Clicked(View v) {
//
//            //    answer = questionArrayList.get(position).getQuestionType().getOptions().get(2);
//            //   radioChecked = true;
//            //  answerList.add(new Answers(question, answer));
//
//        }
//
//        public void onOpt4Clicked(View v) {
//            //      answer = questionArrayList.get(position).getQuestionType().getOptions().get(3);
//            //   radioChecked = true;
//            //   answerList.add(new Answers(question, answer));
//        }
//
//        public void onOpt5Clicked(View v) {
//            //  answer = questionArrayList.get(position).getQuestionType().getOptions().get(4);
//            // radioChecked = true;
//            // answerList.add(new Answers(question, answer));
//        }
//    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
