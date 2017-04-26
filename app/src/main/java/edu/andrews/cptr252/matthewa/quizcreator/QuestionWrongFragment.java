package edu.andrews.cptr252.matthewa.quizcreator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Matthew Agard on 4/24/2017.
 */

public class QuestionWrongFragment extends Fragment {
    private TextView mQuestionWrongView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_answer_wrong, container, false);

        mQuestionWrongView = (TextView) v.findViewById(R.id.answer_right);
        int questionWrong = getActivity().getIntent().getIntExtra(QuizModeFragment.EXTRA_ANSWER_WRGNG, R.string.answer_error);
        mQuestionWrongView.setText(questionWrong);

        return v;
    }
}
