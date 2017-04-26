package edu.andrews.cptr252.matthewa.quizcreator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Matthew Agard on 4/7/2017.
 */

public class QuizModeFragment extends Fragment {
    public static final String EXTRA_ANSWER_RIGHT =
            "edu.andrews.cptr252.matthewa.quizcreator.answer_right";

    public static final String EXTRA_ANSWER_WRGNG =
            "edu.andrews.cptr252.matthewa.quizcreator.answer_wrong";

    private TextView mQuestion;
    private Button mTrue, mFalse;
    /**
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mQuestion = QuestionList.getInstance(getActivity()).getQuestion(...);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quiz_mode, container, false);

        mTrue = (Button) v.findViewById(R.id.)
    }
    */
}
