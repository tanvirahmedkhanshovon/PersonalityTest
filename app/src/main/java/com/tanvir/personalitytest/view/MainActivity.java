package com.tanvir.personalitytest.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.tanvir.personalitytest.R;
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
    public static ArrayList<HashMap<String,Question>> questionList = new ArrayList<>();
    public static ArrayList<String> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Personality Test");
        mainActivityViewModel= ViewModelProviders.of(this).get(MainActivityViewModel.class);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        ClickHandler clickHandler = new ClickHandler();
        binding.setStart(clickHandler);



    }

    private void loadData() {

   mainActivityViewModel.getAllQuestions().observe(this, new Observer<List<Question>>() {
       @Override
       public void onChanged(List<Question> questions) {

           binding.progressbar.hide();
           for(Question question:questions){



               HashMap<String ,Question> questionMap= new HashMap<String,Question>();

               questionMap.put(question.getCategory(),question);

               questionList.add(questionMap);
               if(questionMap.containsKey("hard_fact")) {
                   Log.i(TAG, "Size is " + questionMap.get("hard_fact").getQuestion());
               }
           }

          // Log.i(TAG,"Size is "+questionList.size());

       }
   });
    }


    public class ClickHandler {


        public void onStart(View v){

          binding.progressbar.show();
            //binding.btnStart.setVisibility(View.GONE);
            loadData();

        }
    }
}
