package com.kenzadev.gads_leaderboard.ui;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.kenzadev.gads_leaderboard.R;

import java.util.Objects;

public class SubmitFormActivity extends AppCompatActivity {

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String githubLink;

    private TextInputEditText inputFirstName;
    private TextInputEditText inputLastName;
    private TextInputEditText inputEmail;
    private TextInputEditText inputGithubLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_form);

        inputFirstName = findViewById(R.id.inputFirstName);
        inputLastName = findViewById(R.id.inputLastName);
        inputEmail = findViewById(R.id.inputEmail);
        inputGithubLink = findViewById(R.id.inputGithubLink);

        ImageButton backButton = findViewById(R.id.back_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity();
            }
        });

        Button buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputValidations();
            }
        });
    }


    private void mainActivity() {
        finish();
    }

    private void inputValidations() {
        String cannotBeBlank = "Input cannot be blank";
        String mustBeValidEmail = "Enter a valid email";
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String emailPattern2 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+";

        firstName = Objects.requireNonNull(inputFirstName.getText()).toString();
        lastName = Objects.requireNonNull(inputLastName.getText()).toString();
        emailAddress = Objects.requireNonNull(inputEmail.getText()).toString();
        githubLink = Objects.requireNonNull(inputGithubLink.getText()).toString();
        if (firstName.isEmpty()) {
            inputFirstName.setError(cannotBeBlank);
            return;
        }
        if (lastName.isEmpty()) {
            inputLastName.setError(cannotBeBlank);
        }
        if (emailAddress.isEmpty()) {
            inputEmail.setError(cannotBeBlank);
        }
        if (githubLink.isEmpty()) {
            inputGithubLink.setError(cannotBeBlank);
        }
        if (!(emailAddress.matches((emailPattern)) || emailAddress.matches(emailPattern2))) {
            inputEmail.setError(mustBeValidEmail);
        }

        //Alert Dialog
        Rect displayRectangle = new Rect();
        Window window = SubmitFormActivity.this.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        final AlertDialog.Builder builder = new AlertDialog.Builder(
                SubmitFormActivity.this, R.style.dialog);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View view = LayoutInflater
                .from(SubmitFormActivity.this)
                .inflate(R.layout.dialog_are_you_sure, viewGroup, false);

        view.setMinimumWidth((int) (displayRectangle.width() * 1f));
        builder.setView(view);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        MaterialButton approveButton = view.findViewById(R.id.confirmProjectSubmissionButton);
        ImageButton cancelDialog = view.findViewById(R.id.cancelSubmissionButton);

        approveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitProject();
                alertDialog.dismiss();
            }
        });
        cancelDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

    }

    private void submitProject() {

    }
}
