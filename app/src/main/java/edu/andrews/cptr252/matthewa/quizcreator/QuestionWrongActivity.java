package edu.andrews.cptr252.matthewa.quizcreator;

import android.support.v4.app.Fragment;

/**
 * Hosts QuestionWrongFragment
 */

public class QuestionWrongActivity extends SingleFragmentActivity {
    protected Fragment createFragment() {
        return new QuestionWrongFragment();
    }
}
