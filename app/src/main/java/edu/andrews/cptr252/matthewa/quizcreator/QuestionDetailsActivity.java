package edu.andrews.cptr252.matthewa.quizcreator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.UUID;

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

        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int i) {
                Question question = mQuestions.get(i);
                return QuestionDetailsFragment.newInstance(question.getId());
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
            public void onPageScrolled(int i, float v, int i2); {}

            @Override
            public void onPageSelected(int i) {
                Question question = mQuestions.get(i);
                if(question.getQuestion() != null)
                    setQuestion(question.getQuestion());
            }

            @Override
            public void onPageScrollStateChanged(int i) {}
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it's present
        getMenuInflater().inflate(R.menu.menu_quiz_creator);
        return true;
    }

    @Override
    public boolean onOptionsItemsSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // No inspection SimplifiableIfStatement
        if(id == R.id.action_settings)
            return true;

        return super.onOptionsItemSelected(item);
    }
}
