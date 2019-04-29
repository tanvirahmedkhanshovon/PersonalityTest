package com.tanvir.personalitytest.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tanvir.personalitytest.R;
import com.tanvir.personalitytest.adapter.QuestionsAdapter;
import com.tanvir.personalitytest.databinding.ActivityMainBinding;
import com.tanvir.personalitytest.model.Answers;
import com.tanvir.personalitytest.model.Question;
import com.tanvir.personalitytest.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private MainActivityViewModel mainActivityViewModel;
    //    private ArrayList<HashMap<String,Question>> questionList = new ArrayList<>();
    private ActivityMainBinding binding;
    private ArrayList<ArrayList<Question>> questionList ;
    private ArrayList<ArrayList<Answers>> answerLists ;
    private ArrayList<Question> qList = new ArrayList<>();
    private ArrayList<String> categoryList;
    private RecyclerView recyclerView;
    private QuestionsAdapter adapter;
    private int loadCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Personality Test");
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        loadData();


    }

    private void loadData() {

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

    for(int i = 0;i<categoryList.size();i++) {
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


        if (loadCount < categoryList.size()) {
            answerLists.add(QuestionsAdapter.answerList);
            loadRecyclerView(questionList.get(loadCount));
            adapter.notifyDataSetChanged();


        }
        else {
            item.setTitle("SAVE");
        }

        return super.onOptionsItemSelected(item);
    }


}
