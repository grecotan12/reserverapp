package com.exampl.reserver;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView mWarning;
    private EditText mUserInput;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        mWarning = findViewById(R.id.warning_msg);
        mWarning.setVisibility(View.INVISIBLE);

        mUserInput = findViewById(R.id.user_input);
        mUserInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int num = Integer.parseInt(s.toString());
                if (num <= 0) {
                    mWarning.setText("Please enter number from 1-6");
                    mWarning.setVisibility(View.VISIBLE);
                }
                else if (num > 6) {
                    mWarning.setText("Please call 123-456-7890 to make reservation");
                    mWarning.setVisibility(View.VISIBLE);
                } else {
                    mWarning.setText("Thank you for ordering!");
                    mWarning.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}