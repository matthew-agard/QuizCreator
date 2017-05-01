package edu.andrews.cptr252.matthewa.quizcreator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

/**
 * Inflates the layout seen by the user upon the app's creation.
 * Buttons for creating a question list or taking your quiz
 * are avaiable.
 */
public class MainActivity extends AppCompatActivity {

    private Button mQuestionList, mQuizMode;

    /** Inflates the layout the user sees once the
     *  app is started
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mQuestionList = (Button) findViewById(R.id.question_list_go);
        mQuestionList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuestionList();
            }
        });

        mQuizMode = (Button) findViewById(R.id.quiz_mode_go);
        mQuizMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuizMode();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /** Launches the question list activity */
    private void startQuestionList() {
        Intent i = new Intent(MainActivity.this, QuestionListActivity.class);
        startActivity(i);
    }

    /** Launches the quiz mode */
    private void startQuizMode() {
        Intent i = new Intent(MainActivity.this, QuizModeActivity.class);
        startActivity(i);
    }
}
