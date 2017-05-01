package edu.andrews.cptr252.matthewa.quizcreator;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Hosts the QuestionListFragments displayed in
 * fragment_question_list layout
 */

public class QuestionListActivity extends SingleFragmentActivity
    implements QuestionListFragment.Callbacks, QuestionDetailsFragment.Callbacks {

    public void onQuestionSelected(Question question) {
        if(findViewById(R.id.detailsFragmentContainer) == null) {
            // Start an instance of QuestionDetailsActivity
            Intent i = new Intent(this, QuestionDetailsActivity.class);
            i.putExtra(QuestionDetailsFragment.EXTRA_QUESTION_ID, question.getId());
            startActivityForResult(i, 0);
        } else {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            Fragment oldDetail = fm.findFragmentById(R.id.detailsFragmentContainer);
            Fragment newDetail = QuestionDetailsFragment.newInstance(question.getId());

            if(oldDetail != null)
                ft.remove(oldDetail);

            ft.add(R.id.detailsFragmentContainer, newDetail);
            ft.commit();
        }
    }

    public void onQuestionUpdated(Question question) {
        FragmentManager fm = getSupportFragmentManager();
        QuestionListFragment listFragment = (QuestionListFragment)
                            fm.findFragmentById(R.id.fragment_container);
        listFragment.updateUI();
    }

    @Override
    protected Fragment createFragment() { return new QuestionListFragment(); }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }
}
