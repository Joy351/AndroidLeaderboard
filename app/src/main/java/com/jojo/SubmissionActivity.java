package com.zikozee;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.zikozee.retrofit.RetrofitService;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class SubmissionActivity extends AppCompatActivity {
    EditText firstName, lastName, email, projectLink;
    Button submit;

    public static final String TAG = "RETROFIT";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        ImageView imageView = findViewById(R.id.back_button);

        imageView.setOnClickListener(view -> {
            if(view.getId() == R.id.back_button){
                startActivity(new Intent(SubmissionActivity.this, LeaderBoardActivity.class));
            }
        });

        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        projectLink = findViewById(R.id.project_link);
        submit = findViewById(R.id.submit_project);

        submit.setOnClickListener(view -> {
            confirmation();
        });

    }

    private void successDialog(){
        new SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                .setTitleText("Submission Successful")
                .setCustomImage(R.drawable.success)
                .hideConfirmButton()
                .show();
    }

    private void errorDialog(){
        new SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                .setTitleText("Submission not successful")
                .setCustomImage(R.drawable.error)
                .hideConfirmButton()
                .show();
    }

    private void confirmation(){
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Are you sure ?")
                .setConfirmButtonBackgroundColor(Color.rgb(250, 162, 43))
                .setConfirmClickListener(sweetAlertDialog -> {
                    click();
                    sweetAlertDialog.dismissWithAnimation();
                }).show();
    }

    private void click() {

        String firstNameValue = firstName.getText().toString().trim();
        String lastNameValue = lastName.getText().toString().trim();
        String projectLinkValue = projectLink.getText().toString().trim();
        String emailValue = email.getText().toString().trim();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.baseURI)
//                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<ResponseBody> call=  retrofitService
                .postToGoogleForm(firstNameValue, lastNameValue, emailValue, projectLinkValue);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful() || response.code() >= 200){
                    successDialog();
                }else{
                    errorDialog();
                    Log.d(TAG, response.message() + response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                errorDialog();
                Log.d(TAG, t.getLocalizedMessage());
            }
        });
    }



}