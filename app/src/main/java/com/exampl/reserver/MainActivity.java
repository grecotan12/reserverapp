package com.exampl.reserver;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements Confirmation.OnYesClick{
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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // Determine which menu option was selected
        if (item.getItemId() == R.id.info) {
            startActivity(new Intent(this, About.class));
            return true;
        }
        else if (item.getItemId() == R.id.help) {
            startActivity(new Intent(this, Help.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void giveInput(View view) {
        if (TextUtils.isEmpty(mUserInput.getText().toString())) {
            mUserInput.setError("Please enter an input");
            return;
        } else {
            FragmentManager manager = getSupportFragmentManager();
            Confirmation confirmation = new Confirmation();
            if (Integer.parseInt(mUserInput.getText().toString()) > 0) {
                confirmation.show(manager, "confirmation");
            }
        }
    }

    private void launchFloorPlan() {
        Intent intent = new Intent(this, FloorPlan.class);
        intent.putExtra(FloorPlan.USER_INPUT, mUserInput.getText().toString());
        mInputLauncher.launch(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_menu, menu);
        for(int i = 0; i < menu.size(); i++){
            Drawable drawable = menu.getItem(i).getIcon();
            if(drawable != null) {
                drawable.mutate();
                drawable.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
            }
        }
        return true;
    }


    private final ActivityResultLauncher<Intent> mInputLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {

                @Override
                public void onActivityResult(ActivityResult o) {

                }
            });

    @Override
    public void onYesClick() {
        this.launchFloorPlan();
    }
}