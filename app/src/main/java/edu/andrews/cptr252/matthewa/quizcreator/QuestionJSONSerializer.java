package edu.andrews.cptr252.matthewa.quizcreator;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Used to save and retrieve the
 * questions entered by the user
 */

public class QuestionJSONSerializer {
    private Context mContext;
    private String mFilename;

    public QuestionJSONSerializer(Context c, String f) {
        mContext = c;
        mFilename = f;
    }

    /** Load the questions entered by the user by reading them in from a JSON file */
    public ArrayList<Question> loadQuestions() throws IOException, JSONException {
        ArrayList<Question> questions = new ArrayList<Question>();
        BufferedReader reader = null;
        try {
            InputStream in = mContext.openFileInput(mFilename);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;

            while((line = reader.readLine()) != null)
                jsonString.append(line);

            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();

            for(int i=0; i < array.length(); i++)
                questions.add(new Question(array.getJSONObject(i)));
        }

        catch(FileNotFoundException e) {}

        finally {
            if(reader != null)
                reader.close();
        }

        return questions;
    }

    /** Save the questions entered by the user writing them out to a JSON file */
    public void saveQuestions(ArrayList<Question> questions) throws JSONException, IOException {
        JSONArray array = new JSONArray();
        for(Question question : questions)
            array.put(question.toJSON());

        Writer writer = null;

        try {
            OutputStream out = mContext.openFileOutput(mFilename, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        }

        finally {
            if(writer != null)
                writer.close();
        }
    }
}

