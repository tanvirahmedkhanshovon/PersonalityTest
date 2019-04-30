package com.tanvir.personalitytest;

import android.content.Context;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.tanvir.personalitytest.model.PersonalityForm;
import com.tanvir.personalitytest.service.PersonalityTestService;
import com.tanvir.personalitytest.service.RetrofitInstance;

import org.junit.Test;
import org.junit.runner.RunWith;

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
                assertEquals("hard_fact", response.body().getCategories().get(0));
                assertEquals("What is your gender?", response.body().getQuestions().get(0));
                assertEquals("single_choice", response.body().getQuestions().get(0).getQuestionType().getType());
                assertEquals(18, response.body().getQuestions().get(2).getQuestionType().getCondition().getIfPositive().getQuestionType().getRange().getFrom());
            }

            @Override
            public void onFailure(Call<PersonalityForm> call, Throwable t) {

            }
        });


    }
}
