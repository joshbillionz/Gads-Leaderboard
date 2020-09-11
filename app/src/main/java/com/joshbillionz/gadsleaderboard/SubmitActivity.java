package com.joshbillionz.gadsleaderboard;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ActionBar;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.telecom.Conference;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.joshbillionz.gadsleaderboard.adapters.HoursAdapter;
import com.joshbillionz.gadsleaderboard.models.HoursLeader;
import com.joshbillionz.gadsleaderboard.services.LeadersService;
import com.joshbillionz.gadsleaderboard.services.ServiceBuilder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SubmitActivity extends AppCompatActivity {

    private static final String TAG_SUCCESS= "SUCCESS";
    private static final String TAG_FAILURE="FAILURE";
    private static final String TAG_CONFIRM ="CONFIRM";
    private TextInputEditText mFirstNameET;
    private TextInputEditText mLastNameET;
    private TextInputEditText mEmailET;
    private TextInputEditText mGithubET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        mFirstNameET = findViewById(R.id.firstName);
        mLastNameET = findViewById(R.id.lastName);
        mEmailET = findViewById(R.id.email);
        mGithubET = findViewById(R.id.githubLink);
        MaterialButton button = findViewById(R.id.submitButton);

        button.setOnClickListener(v -> {
            TextInputEditText fields[] = {mFirstNameET, mLastNameET,  mEmailET,mGithubET};
            if(fieldsAreFilled(fields))
            showDialog(TAG_CONFIRM);

        });


    }

    private void showDialog(String tag) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        switch(tag){
            case TAG_CONFIRM:  dialog.setContentView(R.layout.dialog_confirm);
            dialog.findViewById(R.id.yes).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    submit(mFirstNameET.getText().toString(),
                            mLastNameET.getText().toString(),
                            mEmailET.getText().toString(),
                            mGithubET.getText().toString());

                    dialog.dismiss();
                }

            });
            break;
            case TAG_SUCCESS:  dialog.setContentView(R.layout.dialog_success);
                break;
            case TAG_FAILURE:  dialog.setContentView(R.layout.dialog_failure);
                break;
        }
        dialog.show();
        Window window =dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }


    private void submit( String firstName,String lastName, String email, String link) {

        LeadersService service = ServiceBuilder.buildService(LeadersService.class);
        Call<Void> call = service.submitForm(ServiceBuilder.FORM_URL,email,firstName,lastName,link);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(SubmitActivity.this, "Success", Toast.LENGTH_SHORT).show();
                showDialog(TAG_SUCCESS);
            }

            @Override
            public void onFailure(@NotNull Call<Void> call, Throwable t) {
                Toast.makeText(SubmitActivity.this, "Success", Toast.LENGTH_SHORT).show();
                showDialog(TAG_FAILURE);
            }
        });
    }

    private boolean fieldsAreFilled(TextInputEditText[] fields){
        for (TextInputEditText field : fields){

            if (field.getText().toString().length()<=0){
                field.setError("field must be filled");
                return false;
            }
            if(!isValidURL(mGithubET.getText().toString().trim())){
                mGithubET.setError("Invalid URL");
                return false;
            }
            if(!isValidEmail(mEmailET.getText().toString().trim())){
               mEmailET.setError("Invalid Email");
                return false;
            }
        }
        return true;
    }

    private boolean isValidEmail(CharSequence target){
        if (target== null) return false;
        return Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private boolean isValidURL(CharSequence target){
        if (target== null) return false;
        return  Patterns.WEB_URL.matcher(target).matches();
    }




}