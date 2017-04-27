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

public class QuestionRightFragment extends Fragment {
    private TextView mQuestionRightView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_answer_right, container, false);

        return v;
    }
}
