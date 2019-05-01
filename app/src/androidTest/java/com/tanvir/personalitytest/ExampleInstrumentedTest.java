package com.tanvir.personalitytest;

import android.content.Context;
import android.util.Log;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.tanvir.personalitytest.model.Answers;
import com.tanvir.personalitytest.model.PersonalityForm;
import com.tanvir.personalitytest.model.PublishResult;
import com.tanvir.personalitytest.service.PersonalityTestService;
import com.tanvir.personalitytest.service.RetrofitInstance;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.tanvir.personalitytest", appContext.getPackageName());
    }

    @Test
    public void testWebServiceResponse() {

        PersonalityTestService service = RetrofitInstance.getService();

        Call<PersonalityForm> personalityForm = service.getForm();
        personalityForm.enqueue(new Callback<PersonalityForm>() {
            @Override
            public void onResponse(Call<PersonalityForm> call, Response<PersonalityForm> response) {

                assertEquals(true, response.isSuccessful());
                try {
                    assertEquals("hard_fact", response.body().getCategories().get(0));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    assertEquals("What is your gender?", response.body().getQuestions().get(0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    assertEquals("single_choice", response.body().getQuestions().get(0).getQuestionType().getType());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    assertEquals(18, response.body().getQuestions().get(2).getQuestionType().getCondition().getIfPositive().getQuestionType().getRange().getFrom());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<PersonalityForm> call, Throwable t) {

            }
        });


    }
    @Test
    public void testPostResponse(){

        PersonalityTestService service = RetrofitInstance.getService();
        ArrayList<Answers> answerList = new ArrayList<>();
        Answers answers = new Answers();

        answers.setQuestion("DO YOU GO OUT?");
        answers.setAnswer("YES");
        answerList.add(answers);
        answers.setQuestion("DO YOU LIKE SPORTS?");
        answers.setAnswer("YES");
        answerList.add(answers);


        Call<PublishResult> call = service.publishResut(answerList);

        call.enqueue(new Callback<PublishResult>() {
            @Override
            public void onResponse(Call<PublishResult> call, Response<PublishResult> response) {


                try {


                    PublishResult value = response.body();
                   assertEquals("Published!",value.getStatus());

                } catch (Exception e) {


                }

            }

            @Override
            public void onFailure(Call<PublishResult> call, Throwable t) {


            }
        });
    }
}
