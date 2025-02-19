package com.exampl.reserver;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_floor_plan);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.floor_plan), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        table_one = findViewById(R.id.table_one);
        table_two = findViewById(R.id.table_two);
        table_three = findViewById(R.id.table_three);

        Intent intent = getIntent();
        String theInput = intent.getStringExtra(USER_INPUT);

        if (!theInput.equals("")) {
            int guests = Integer.parseInt(theInput);
            if (guests <= 4) {
                this.setImageEachLayout(table_one, R.drawable.round_solid);
                this.setImageEachLayout(table_two, R.drawable.round_solid);
                this.setImageEachLayout(table_three, R.drawable.round_solid);
            } else {
                this.setImageEachLayout(table_one, R.drawable.round_faded);
                this.setImageEachLayout(table_two, R.drawable.round_faded);
                this.setImageEachLayout(table_three, R.drawable.round_faded);
            }
        }

    }

    private void setImageEachLayout(LinearLayout theLayOut, int theDrawable) {
        for (int i = 0; i < theLayOut.getChildCount(); i++) {
            View view = theLayOut.getChildAt(i);
            if (view instanceof ImageView) {
                ((ImageView) view).setImageResource(theDrawable);
            }
        }
    }

}