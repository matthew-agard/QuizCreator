package edu.andrews.cptr252.matthewa.quizcreator;

import android.support.v4.app.Fragment;

/**
 * Hosts QuestionRightFragment
 */

public class QuestionRightActivity extends SingleFragmentActivity{
    protected Fragment createFragment() {
        return new QuestionRightFragment();
    }
}
