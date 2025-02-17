package com.exampl.reserver;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FloorPlan extends AppCompatActivity {

    private TextView mTest;

    private LinearLayout table_one;
    private LinearLayout table_two;
    private LinearLayout table_three;

    public static final String USER_INPUT = "userInput";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_plan);

        table_one = findViewById(R.id.table_one);
        table_two = findViewById(R.id.table_two);
        table_three = findViewById(R.id.table_three);

        Intent intent = getIntent();
        String theInput = intent.getStringExtra(USER_INPUT);

        if (!theInput.equals("")) {
            int guests = Integer.parseInt(theInput);
            if (guests > 4) {
                for (int i = 0; i < table_one.getChildCount(); i++) {
                    View view = table_one.getChildAt(i);

                    if (view instanceof ImageView) {
                        ((ImageView) view).setImageResource(R.drawable.round_solid);
                    }
                }
            }
        }

    }

}