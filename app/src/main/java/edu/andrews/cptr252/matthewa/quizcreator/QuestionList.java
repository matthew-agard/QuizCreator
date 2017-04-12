package edu.andrews.cptr252.matthewa.quizcreator;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Matthew Agard on 4/7/2017.
 */

public class QuestionList {

    private static QuestionList sOurInstance;
    private static final String TAG = "QuestionList";
    private static final String FILENAME = "questions.json";
    private QuestionJSONSerializer mSerializer;

    public static void setOurInstance(QuestionList ourInstance) {
        sOurInstance = ourInstance;
    }

    private ArrayList<Question> mQuestions;
    private Context mAppContext;

    public boolean saveQuestions() {
        try {
            mSerializer.saveQuestions(mQuestions);
            Log.d(TAG, "Questions saved to file");
            return true;
        }

        catch(Exception e) {
            Log.e(TAG, "Error saving questions: " + e);
            return false;
        }
    }

    public void addQuestion(Question question) {
        mQuestions.add(question);
        saveQuestions();
    }

    public void deleteQuestion(Question question) {
        mQuestions.remove(question);
        saveQuestions();
    }

    private QuestionList(Context appContext) {
        mAppContext = appContext;
        mSerializer = new QuestionJSONSerializer(mAppContext, FILENAME);

        try {
            mQuestions = mSerializer.loadQuestions();
        }

        catch(Exception e) {
            mQuestions = new ArrayList<Question>();
            Log.e(TAG, "Error loading questions: ", e);
        }
    }

    public static QuestionList getInstance(Context c) {
        if(sOurInstance == null)
            sOurInstance = new QuestionList(c.getApplicationContext());

        return sOurInstance;
    }

    public Question getQuestion(UUID id) {
        for(Question question : mQuestions) {
            if(question.getId().equals(id))
                return question;
        }

        return null;
    }

    public ArrayList<Question> getQuestions() {
        return mQuestions;
    }
}
