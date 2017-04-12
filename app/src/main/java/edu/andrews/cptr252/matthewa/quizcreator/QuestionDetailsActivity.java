package edu.andrews.cptr252.matthewa.quizcreator;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

/**
 * Created by Matthew Agard on 4/7/2017.
 */

public class QuestionDetailsActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private ArrayList<Question> mQuestions;

    public void onBugUpdated(Question question) {
        // do nothing
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        mQuestions = QuestionList.getInstance(this).getQuestions();
    }
}
