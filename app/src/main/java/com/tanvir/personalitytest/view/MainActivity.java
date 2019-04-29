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

import com.tanvir.personalitytest.R;
import com.tanvir.personalitytest.adapter.QuestionsAdapter;
import com.tanvir.personalitytest.databinding.ActivityMainBinding;
import com.tanvir.personalitytest.model.Question;
import com.tanvir.personalitytest.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding binding;
    //    private ArrayList<HashMap<String,Question>> questionList = new ArrayList<>();
    private ArrayList<Question> hardFactList = new ArrayList<>();
    private ArrayList<Question> lifeStyleList = new ArrayList<>();
    private ArrayList<Question> introversionList = new ArrayList<>();
    private ArrayList<Question> passionList = new ArrayList<>();
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


            }
        });


        mainActivityViewModel.getAllQuestions().observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {


                for (Question question : questions) {


                    HashMap<String, Question> questionMap = new HashMap<String, Question>();

                    questionMap.put(question.getCategory(), question);

                    // questionList.add(questionMap);

                    if (questionMap.containsKey(categoryList.get(0))) {
                        // Log.i(TAG, "Size is " + questionMap.get("hard_fact").getQuestion());
                        hardFactList.add(question);
                    }
                    if (questionMap.containsKey(categoryList.get(1))) {

                        lifeStyleList.add(question);
                    }
                    if (questionMap.containsKey(categoryList.get(2))) {

                        introversionList.add(question);
                    }
                    if (questionMap.containsKey(categoryList.get(3))) {

                        passionList.add(question);
                    }
                }


                loadRecyclerView(hardFactList);

                // Log.i(TAG,"Size is "+questionList.size());

            }
        });
    }

    private void loadRecyclerView(ArrayList<Question> arrayList) {


        recyclerView = binding.rvQuestionList;

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new QuestionsAdapter(arrayList, this);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.navigation, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (loadCount == 0) {
            loadCount++;
            loadRecyclerView(lifeStyleList);
            adapter.notifyDataSetChanged();

        } else if (loadCount == 1) {
            loadCount++;
            loadRecyclerView(passionList);
            adapter.notifyDataSetChanged();
        } else if (loadCount == 2) {
            loadRecyclerView(introversionList);
            adapter.notifyDataSetChanged();
            loadCount++;
            item.setTitle("SAVE");
        }


        return super.onOptionsItemSelected(item);
    }


}
