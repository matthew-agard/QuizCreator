package edu.andrews.cptr252.matthewa.quizcreator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.UUID;

import static edu.andrews.cptr252.matthewa.quizcreator.QuestionDetailsFragment.EXTRA_QUESTION_ID;

/**
 * Created by Matthew Agard on 4/7/2017.
 */

public class QuizModeFragment extends Fragment {
    public static final String EXTRA_ANSWER_RIGHT =
            "edu.andrews.cptr252.matthewa.quizcreator.answer_right";

    public static final String EXTRA_ANSWER_WRGNG =
            "edu.andrews.cptr252.matthewa.quizcreator.answer_wrong";

    private Question mQuestion;
    private TextView mQuestionDisplay;
    private Button mTrue, mFalse;

    public static QuizModeFragment newInstance(UUID questionId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_QUESTION_ID, questionId);
        QuizModeFragment fragment = new QuizModeFragment();
        fragment.setArguments(args);

        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID questionId = (UUID) getArguments().getSerializable(EXTRA_QUESTION_ID);
        mQuestion = QuestionList.getInstance(getActivity()).getQuestion(questionId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quiz_mode, container, false);

        mQuestionDisplay = (TextView) v.findViewById(R.id.question_text);
        mQuestionDisplay.setText(mQuestion.getQuestion());

        mTrue = (Button) v.findViewById(R.id.true_button);
        mTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mQuestion.getAnswer() == true) {
                    Intent i = new Intent(getActivity(), QuestionRightActivity.class);
                    startActivity(i);
                } else {
                    Intent i = new Intent(getActivity(), QuestionWrongActivity.class);
                    startActivity(i);
                }
            }
        });

        mFalse = (Button) v.findViewById(R.id.false_button);
        mFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mQuestion.getAnswer() == false) {
                    Intent i = new Intent(getActivity(), QuestionWrongActivity.class);
                    startActivity(i);
                } else {
                    Intent i = new Intent(getActivity(), QuestionRightActivity.class);
                    startActivity(i);
                }
            }
        });

        return v;
    }

}
