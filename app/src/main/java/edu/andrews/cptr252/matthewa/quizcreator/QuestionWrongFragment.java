package edu.andrews.cptr252.matthewa.quizcreator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Inflates the layout that displays the user answered incorrectly
 */

public class QuestionWrongFragment extends Fragment {
    private TextView mQuestionWrongView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_answer_wrong, container, false);

        return v;
    }
}
