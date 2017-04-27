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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import java.util.UUID;

/**
 * Created by Matthew Agard on 4/7/2017.
 */

public class QuestionDetailsFragment extends Fragment {
    private static final String TAG = "QuestionDetailsFragment";
    public static final String EXTRA_QUESTION_ID =
            "edu.andrews.cptr252.matthewa.quizcreator.question_id";
    private Question mQuestion;
    private EditText mQuestionTitle;
    private CheckBox mAnswerCheckBox;

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

        mAnswerCheckBox = (CheckBox) v.findViewById(R.id.question_answer_checkbox);
        mAnswerCheckBox.setChecked(mQuestion.getAnswer());
        mAnswerCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mQuestion.setAnswer(isChecked);
                Log.d(TAG, "Set answer status to " + isChecked);
                mCallbacks.onQuestionUpdated(mQuestion);
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
