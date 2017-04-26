package edu.andrews.cptr252.matthewa.quizcreator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Matthew Agard on 4/7/2017.
 */

public class QuizModeActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private ArrayList<Question> mQuestions;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        mQuestions = QuestionList.getInstance(this).getQuestions();

        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int i) {
                Question question = mQuestions.get(i);
                return QuizModeFragment.newInstance(question.getId());
            }

            @Override
            public int getCount() {
                return mQuestions.size();
            }
        });

        UUID questionId = (UUID) getIntent()
                .getSerializableExtra(QuestionDetailsFragment.EXTRA_QUESTION_ID);

        for(int i=0; i < mQuestions.size(); i++) {
            if(mQuestions.get(i).getId().equals(questionId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i,float v, int i2)  {}

            @Override
            public void onPageSelected(int i) {
                Question question = mQuestions.get(i);
                if(question.getQuestion() != null)
                    setTitle(question.getQuestion());
            }

            @Override
            public void onPageScrollStateChanged(int i) {}
        });
    }
}
