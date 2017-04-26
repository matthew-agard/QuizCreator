package edu.andrews.cptr252.matthewa.quizcreator;

import android.support.v4.app.Fragment;

/**
 * Created by Matthew Agard on 4/24/2017.
 */

public class QuestionWrongActivity extends SingleFragmentActivity {
    protected Fragment createFragment() {
        return new QuestionWrongFragment();
    }
}
