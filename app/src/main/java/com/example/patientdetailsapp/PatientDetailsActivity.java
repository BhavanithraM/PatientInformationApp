package com.example.patientdetailsapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class PatientDetailsActivity extends AppCompatActivity {

    String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("23ITR012 Patient Details");
        setContentView(R.layout.activity_details);

        TextView tvDetails = findViewById(R.id.tvDetails);
        Button btnCall = findViewById(R.id.btnCall);
        Button btnSMS = findViewById(R.id.btnSMS);
        Button btnEmail = findViewById(R.id.btnEmail);

        Intent i = getIntent();

        phoneNumber = i.getStringExtra("phone");

        tvDetails.setText(
                "Name: " + i.getStringExtra("name") +
                        "\nAge: " + i.getStringExtra("age") +
                        "\nGender: " + i.getStringExtra("gender") +
                        "\nIllness: " + i.getStringExtra("illness") +
                        "\nAppointment Date: " + i.getStringExtra("date")
        );

        btnCall.setOnClickListener(v -> showDialog("call"));
        btnSMS.setOnClickListener(v -> showDialog("sms"));

        btnEmail.setOnClickListener(v -> {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:doctor@gmail.com"));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Patient Appointment");
            startActivity(emailIntent);
        });
    }

    private void showDialog(String type) {

        new AlertDialog.Builder(this)
                .setTitle("Confirmation")
                .setMessage("Are you sure you want to proceed?")
                .setPositiveButton("Yes", (dialog, which) -> {

                    if (type.equals("call")) {
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:" + phoneNumber));
                        startActivity(callIntent);

                    } else if (type.equals("sms")) {
                        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                        smsIntent.setData(Uri.parse("sms:" + phoneNumber));
                        startActivity(smsIntent);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}