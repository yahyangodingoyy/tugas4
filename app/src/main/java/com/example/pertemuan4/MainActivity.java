package com.example.pertemuan4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText phoneNumber;
    private EditText websiteUri;
    private EditText locationUri;
    private EditText textShare;

    Button buttonWebsite;
    Button buttonLocation;
    Button buttonText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNumber = findViewById(R.id.edtPhoneNumber);
        websiteUri = findViewById(R.id.edtWebsiteUri);
        locationUri = findViewById(R.id.edtLocationUri);
        textShare = findViewById(R.id.edtShareText);

        buttonWebsite = findViewById(R.id.btnWebsiteUri);
        buttonWebsite.setOnClickListener(this);

        buttonLocation = findViewById(R.id.btnLocationUri);
        buttonLocation.setOnClickListener(this);

        buttonText = findViewById(R.id.btnShareText);
        buttonText.setOnClickListener(this);
    }
    public void openDialPhone (View view) {
        Intent dialPhone = new Intent(Intent.ACTION_DIAL,
                Uri.parse("tel:" + phoneNumber.getText().toString()));
        if (phoneNumber.length() == 0)
        {
            phoneNumber.setError("MASUKKAN PHONE NUMBER!");
        } else {
            startActivity(dialPhone);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnWebsiteUri:
                Intent openWebsite = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(websiteUri.getText().toString()));
                if (websiteUri.length() == 0)
                {
                    websiteUri.setError("MASUKKAN WEBSITE!");
                } else {
                    startActivity(openWebsite);
                }
                break;
            case R.id.btnLocationUri:
                Intent openLocation = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("geo:0,0?q=" +
                                locationUri.getText().toString()));
                if (locationUri.length() == 0)
                {
                    locationUri.setError("MASUKKAN LOCATION!");
                } else {
                    startActivity(openLocation);
                }
                break;
            case R.id.btnShareText:
                if (textShare.length() == 0)
                {
                    textShare.setError("MASUKKAN SHARE TEXT!");
                } else {
                    ShareCompat.IntentBuilder
                            .from(this)
                            .setType("text/plan")
                            .setChooserTitle("Share this text with : ")
                            .setText(textShare.getText().toString())
                            .startChooser();
                }
                break;
        }
    }

}