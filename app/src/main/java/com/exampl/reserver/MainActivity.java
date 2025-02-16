package com.exampl.reserver;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView mWarning;
    private TextView intro;
    private EditText mUserInput;

    private String receivedInput;
    private String warningMsg;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        intro = findViewById(R.id.intro);
        mWarning = findViewById(R.id.warning_msg);
        mWarning.setVisibility(View.INVISIBLE);

        mUserInput = findViewById(R.id.user_input);
        mUserInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals("")) {
                    int num = Integer.parseInt(s.toString());
                    if (num <= 0) {
                        warningMsg = "Please enter number from 1-6";
                        mWarning.setText(warningMsg);
                        mWarning.setVisibility(View.VISIBLE);
                    } else if (num > 6) {
                        warningMsg = "Please call 123-456-7890 to make reservation";
                        mWarning.setText(warningMsg);
                        mWarning.setVisibility(View.VISIBLE);
                    } else {
                        warningMsg = "Thank you for ordering!";
                        mWarning.setText(warningMsg);
                        mWarning.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void giveInput(View view) {
        Intent intent = new Intent(this, FloorPlan.class);
        intent.putExtra(FloorPlan.USER_INPUT, mUserInput.getText().toString());
        mInputLauncher.launch(intent);
    }

    private final ActivityResultLauncher<Intent> mInputLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {

                @Override
                public void onActivityResult(ActivityResult o) {

                }
            });
}