package com.hello.aleks.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private static final String TAG = "Cheat Activity";
    private static final String EXTRA_ANSWER_IS_TRUE = "com.hello.aleks.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN = "com.hello.aleks.geoquiz.answer_shown";
    private static final String ANSWER_IS_SHOW_FLAG = "com.hello.aleks.geoquiz.answer_is_show_flag";
    private boolean mAnswerIsTrue;
    private boolean mAnswerIsShown;
    private TextView mAnswerTextView;
    private Button mShowAnswerButton;

    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
        Intent i = new Intent(packageContext, CheatActivity.class);
        i.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return i;
    }

    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);
        mShowAnswerButton = (Button) findViewById(R.id.show_answer_button);

        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRightAnswerInTextView();
                mAnswerIsShown = true;
                setAnswerShownResult(mAnswerIsShown);

            }
        });


        if (savedInstanceState != null) {
            mAnswerIsShown = savedInstanceState.getBoolean(ANSWER_IS_SHOW_FLAG, false);
            if (mAnswerIsShown) {
                setRightAnswerInTextView();
                setAnswerShownResult(mAnswerIsShown);
            }
        }
    }

    private void setRightAnswerInTextView() {
        if (mAnswerIsTrue) {
            mAnswerTextView.setText(R.string.true_button);
        } else {
            mAnswerTextView.setText(R.string.false_button);
        }
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "OnSaveInstance");
        outState.putBoolean(ANSWER_IS_SHOW_FLAG, mAnswerIsShown);
    }
}
