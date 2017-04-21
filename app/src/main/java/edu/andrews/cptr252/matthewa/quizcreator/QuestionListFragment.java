package edu.andrews.cptr252.matthewa.quizcreator;

/**
 * Created by Matthew Agard on 4/7/2017.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class QuestionListFragment extends ListFragment {
    private static final String TAG = "QuestionListFragment";
    private ArrayList<Question> mQuestions;

    public QuestionListFragment() {}

    private class QuestionAdapter extends ArrayAdapter<Question> {
        public QuestionAdapter(ArrayList<Question> questions) {
            super(getActivity(), 0, questions);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_question, null);
            }

            Question question = getItem(position);

            TextView questionTextView =
                    (TextView) convertView.findViewById(R.id.list_item_question);
            questionTextView.setText(question.getQuestion());

            TextView answerTextView =
                    (TextView) convertView.findViewById(R.id.list_item_answer);
            answerTextView.setText(question.isAnswerTrue());

            return convertView;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Questions");
        mQuestions = QuestionList.getInstance(getActivity()).getQuestions();

        QuestionAdapter adapter = new QuestionAdapter(mQuestions);
        setListAdapter(adapter);
    }

    @Override
    public void OnListItemClick(ListView 1, View v, int position, long id) {
        Question question = (Question) (getListAdapter()).getItem(position);

        // Let the activity decided how the selected question is displayed
        mCallbacks.onQuestionSelected(question);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((QuestionAdapter)getListAdapter()).notifyDataSetChanged();
    }

    /** Required interface to be implemented in hosting activities */
    public interface Callbacks {
        void onQuestionSelected(Question question);
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

    public void updateUI() {
        ((QuestionAdapter)getListAdapter()).notifyDataSetChanged();
    }

    private void addQuestion() {
        Question question = new Question();
        QuestionList.getInstance(getActivity()).addQuestion(question);

        // Let the activity decide to launch
        // details fragment or update question in current
        // details fragment (in case of split screen).
        mCallbacks.onQuestionSelected();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_question_list, container, false);

        View addButton = v.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { addQuestion(); }
        });

        // Get ListView for the ListFragment
        ListView listView = (ListView) v.findViewById(android.R.id.list);

        // Allow user to select multiplie questions in the list
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            public void onItemCheckedStateChanged(ActionMode mode, int position,
                                                  long id, boolean checked) {
                // Nothing to do
            }

            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.question_list_item_context, menu);
                return true;
            }

            public boolean onPrepareActionMode(ActionMode mode, Menu menu) { return false; }

            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.menu_item_delete_question:
                        QuestionAdapter adapter = (QuestionAdapter) getListAdapter();
                        QuestionList questionList = QuestionList.getInstance(getActivity());
                        // Check each question in the list
                        // If it's selected, delete it
                        for(int i = adapter.getCount()-1; i >= 0; i--) {
                            if(getListView().isItemChecked(i)) {
                                // Question has been selected. Delete it
                                questionList.deleteQuestion(adapter.getItem(i));
                            }
                        }

                        mode.finish();
                        // The question list has been changed
                        // Tell the question adapter to update the list of views
                        adapter.notifyDataSetChanged();
                        return true;

                    default: return false;
                }
            }

            public void onDestroyActionMode(ActionMode mode) {
                // nothing to do
            }
        });

        return v;
    }
}
