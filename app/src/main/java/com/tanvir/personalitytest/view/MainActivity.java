package com.tanvir.personalitytest.view;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tanvir.personalitytest.R;
import com.tanvir.personalitytest.adapter.QuestionsAdapter;
import com.tanvir.personalitytest.databinding.ActivityMainBinding;
import com.tanvir.personalitytest.model.Answers;
import com.tanvir.personalitytest.model.PersonalityFormRepository;
import com.tanvir.personalitytest.model.Question;
import com.tanvir.personalitytest.receiver.InternetReceiver;
import com.tanvir.personalitytest.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private MainActivityViewModel mainActivityViewModel;
    //    private ArrayList<HashMap<String,Question>> questionList = new ArrayList<>();
    private ActivityMainBinding binding;
    private ArrayList<ArrayList<Question>> questionList;
    private ArrayList<ArrayList<Answers>> answerLists;
    private ArrayList<Question> qList = new ArrayList<>();
    private ArrayList<String> categoryList;
    private RecyclerView recyclerView;
    private QuestionsAdapter adapter;
    private int loadCount;
    private boolean connected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Personality Test");
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        if(!connected){

            binding.progressBar.setVisibility(View.GONE);
            binding.infoPortion.setVisibility(View.VISIBLE);
            binding.infoPortion.setText("Please connect internet to proceed!!");
           // binding.progressBar.setVisibility(View.GONE);
        }
        mainActivityViewModel.hasInternetConnection().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                binding.infoPortion.setVisibility(View.GONE);
                connected =true;
                    loadData();

            }
        });


    }

    private void loadData() {

        mainActivityViewModel.manyRequestResponse().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                //   Toast.makeText(MainActivity.this, s +"Please change url and provided end point in email", Toast.LENGTH_SHORT).show();
                binding.progressBar.setVisibility(View.GONE);
                binding.infoPortion.setVisibility(View.VISIBLE);
                binding.infoPortion.setText(s + "Please change url and provided end point in email");
            }
        });

        mainActivityViewModel.getAllCategories().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {


                categoryList = (ArrayList<String>) strings;

                questionList = new ArrayList<>(categoryList.size());

                answerLists = new ArrayList<>(categoryList.size());


            }
        });


        mainActivityViewModel.getAllQuestions().observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {

                for (int i = 0; i < categoryList.size(); i++) {
                    for (Question question : questions) {


                        HashMap<String, Question> questionMap = new HashMap<String, Question>();

                        questionMap.put(question.getCategory(), question);

                        if (questionMap.containsKey(categoryList.get(i))) {
                            // Log.i(TAG, "Size is " + questionMap.get("hard_fact").getQuestion());
                            qList.add(question);

                        }

                    }
                    questionList.add(qList);
                    qList = new ArrayList<>();
                }

                loadRecyclerView(questionList.get(loadCount));
                // Log.i(TAG,"Size is "+questionList.size());

            }
        });


    }

    private void loadRecyclerView(ArrayList<Question> arrayList) {

        recyclerView = binding.rvQuestionList;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new QuestionsAdapter(arrayList, this);
        recyclerView.setAdapter(adapter);
        loadCount++;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.navigation, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (adapter.getQuestionArrayList().size() == adapter.getAnswerList().size()) {

            if (connected) {
                if (adapter.hasrange) {

                    if (adapter.min && adapter.max) {

                        loadNextCategory(item);
                    } else {

                        Toast.makeText(this, "Please keep the age range to given limit!!", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    loadNextCategory(item);
                }
            } else {

                Toast.makeText(this, "Please give all answers to proceed!!", Toast.LENGTH_SHORT).show();
            }
        }
        else {

            Toast.makeText(this, "Please connect internet to proceed!!", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadNextCategory(MenuItem item) {

        if (loadCount < categoryList.size()) {
            answerLists.add(QuestionsAdapter.answerList);
            //  Log.i(TAG, "size is " + answerLists.get(0).get(3).getAnswer());
            loadRecyclerView(questionList.get(loadCount));
            adapter.notifyDataSetChanged();


        } else {
            item.setTitle("SAVE");
            ArrayList<Answers> finalAnswers = new ArrayList<>();
            for (int i = 0; i < answerLists.size(); i++) {
                for (ArrayList<Answers> answers : answerLists) {

                    finalAnswers.add(answers.get(i));
                }
            }

            mainActivityViewModel.pulishResult(finalAnswers).observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    //Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this, SuccessActivity.class);
                    intent.putExtra("message", s);
                    startActivity(intent);


                }
            });
        }


    }

    @Override
    protected void onPause() {
        super.onPause();
       // unregisterReceiver(PersonalityFormRepository.brReceiver);
    }
}
