package edu.andrews.cptr252.matthewa.quizcreator;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.UUID;

/**
 * Created by Matthew Agard on 4/7/2017.
 */

public class QuestionDetailsFragment extends Fragment {
    private static final String TAG = "QuestionDetailsFragment";
    public static final String EXTRA_QUESTION_ID =
            "edu.andrews.cptr252.matthewa.quizcreator.question_id";
    private Question mQuestion;
    private TextView mAnswer;
    private EditText mQuestionTitle;
    private Button mTrue, mFalse;

    public QuestionDetailsFragment() {}

    public interface Callbacks {
        void onQuestionUpdated(Question question);
    }

    private Callbacks mCallbacks;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbacks = (Callbacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    public static QuestionDetailsFragment newInstance(UUID questionId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_QUESTION_ID, questionId);
        QuestionDetailsFragment fragment = new QuestionDetailsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID questionId = (UUID) getArguments().getSerializable(EXTRA_QUESTION_ID);
        mQuestion = QuestionList.getInstance(getActivity()).getQuestion(questionId);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quiz_creator, container, false);

        mQuestionTitle = (EditText) v.findViewById(R.id.question_description);
        mQuestionTitle.setText(mQuestion.getQuestion());
        mQuestionTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mQuestion.setQuestion(s.toString());
                Log.d(TAG, "Question changed to " + mQuestion.getQuestion());
                mCallbacks.onQuestionUpdated(mQuestion);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        mTrue = (Button) v.findViewById(R.id.question_answer_true);
        mTrue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mQuestion.setAnswer(true);
                mAnswer = (TextView) v.findViewById(R.id.list_item_answer);
                mAnswer.setEnabled(mQuestion.getAnswer());
            }
        });

        mFalse = (Button) v.findViewById(R.id.question_answer_false);
        mFalse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mQuestion.setAnswer(false);
                mAnswer = (TextView) v.findViewById(R.id.list_item_answer);
                mAnswer.setEnabled(mQuestion.getAnswer());
            }
        });

        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        QuestionList.getInstance(getActivity()).saveQuestions();
    }
}
