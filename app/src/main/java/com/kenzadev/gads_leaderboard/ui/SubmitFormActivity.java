package com.kenzadev.gads_leaderboard.ui;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.kenzadev.gads_leaderboard.API.RetrofitApiCalls;
import com.kenzadev.gads_leaderboard.API.RetrofitClient;
import com.kenzadev.gads_leaderboard.R;

import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    private boolean isInputEmpty() {
        String cannotBeBlank = "Input cannot be blank";

        firstName = Objects.requireNonNull(inputFirstName.getText()).toString();
        lastName = Objects.requireNonNull(inputLastName.getText()).toString();
        emailAddress = Objects.requireNonNull(inputEmail.getText()).toString();
        githubLink = Objects.requireNonNull(inputGithubLink.getText()).toString();
        if (firstName.isEmpty()) {
            inputFirstName.setError(cannotBeBlank);
            return true;
        }
        if (lastName.isEmpty()) {
            inputLastName.setError(cannotBeBlank);
            return true;
        }
        if (emailAddress.isEmpty()) {
            inputEmail.setError(cannotBeBlank);
            return true ;
        }
        if (githubLink.isEmpty()) {
            inputGithubLink.setError(cannotBeBlank);
            return true;
        }
        return false;
    }

    private void inputValidations() {
        if (isInputEmpty())
        {
            Toast.makeText(SubmitFormActivity.this,"Please fill all inputs", Toast.LENGTH_LONG).show();
        }
        else {
            //Are you sure? Dialog
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

            MaterialButton approveButton = view.findViewById(R.id.approveButton);
            ImageButton cancelDialog = view.findViewById(R.id.cancelSubmissionButton);

            //if the user confirms
            approveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    submitProject();
                    alertDialog.dismiss();
                }
            });
            //if the user cancels
            cancelDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                }
            });
        }
    }

    private void submitProject() {
        RetrofitApiCalls google_service = RetrofitClient.getGoogleFormsRetrofitInstance().create(RetrofitApiCalls.class);
        Call<ResponseBody> call = google_service.submitGoogleForm(emailAddress, firstName, lastName, githubLink);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    //Successful
                    showSuccessDialog();
                } else {
                    showFailDialog();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //Not successful
                showFailDialog();
            }
        });
    }

    public void showSuccessDialog() {
        Rect displayRectangle = new Rect();
        Window window = SubmitFormActivity.this.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        final AlertDialog.Builder builder = new AlertDialog.Builder(
                SubmitFormActivity.this, R.style.dialog);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View view = LayoutInflater.from(SubmitFormActivity.this)
                .inflate(R.layout.dialog_success, viewGroup,
                        false);
        view.setMinimumWidth((int) (displayRectangle.width() * 1f));
        builder.setView(view);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void showFailDialog() {
        Rect displayRectangle = new Rect();
        Window window = SubmitFormActivity.this.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        final AlertDialog.Builder builder = new AlertDialog.Builder(
                SubmitFormActivity.this, R.style.dialog);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View view = LayoutInflater
                .from(SubmitFormActivity.this)
                .inflate(R.layout.dialog_not_successful, viewGroup, false);
        view.setMinimumWidth((int) (displayRectangle.width() * 1f));
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
