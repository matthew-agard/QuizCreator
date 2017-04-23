package edu.andrews.cptr252.matthewa.quizcreator;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

/**
 * Created by Matthew Agard on 4/6/2017.
 */

public class Question {
    private UUID mId;
    private String mQuestion;
    private boolean mAnswer;

    public Question() {
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public void setAnswer(boolean answer) {
        mAnswer = answer;
    }

    public boolean getAnswer() {
        return mAnswer;
    }

    private static final String JSON_ID = "id";
    private static final String JSON_QUESTION = "question";
    private static final String JSON_ANSWER = "answer";

    public Question(JSONObject json) throws JSONException {
        mId = UUID.fromString(json.getString(JSON_ID));
        mQuestion = json.getString(JSON_QUESTION);
        mAnswer = json.getBoolean(JSON_ANSWER);
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();

        json.put(JSON_ID, mId.toString());
        json.put(JSON_QUESTION, mQuestion);
        json.put(JSON_ANSWER, mAnswer);

        return json;
    }
}
