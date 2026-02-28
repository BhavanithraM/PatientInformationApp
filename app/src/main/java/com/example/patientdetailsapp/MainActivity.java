package com.example.patientdetailsapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etName, etAge, etPhone;
    RadioGroup radioGroup;
    Spinner spIllness;
    DatePicker datePicker;
    Button btnSubmit;

    String[] illnessList = {"Fever", "Cold", "Diabetes", "BP"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("23ITR012 Patient App");
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etPhone = findViewById(R.id.etPhone);
        radioGroup = findViewById(R.id.radioGroup);
        spIllness = findViewById(R.id.spIllness);
        datePicker = findViewById(R.id.datePicker);
        btnSubmit = findViewById(R.id.btnSubmit);

        // Spinner Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                illnessList
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spIllness.setAdapter(adapter);

        btnSubmit.setOnClickListener(v -> {

            String name = etName.getText().toString();
            String age = etAge.getText().toString();
            String phone = etPhone.getText().toString();
            String illness = spIllness.getSelectedItem().toString();

            int selectedId = radioGroup.getCheckedRadioButtonId();
            RadioButton rb = findViewById(selectedId);
            String gender = rb.getText().toString();

            String date = datePicker.getDayOfMonth() + "/"
                    + (datePicker.getMonth() + 1) + "/"
                    + datePicker.getYear();

            // ✅ Confirmation Dialog
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Appointment Confirmed")
                    .setMessage("Appointment Fixed on " + date)
                    .setPositiveButton("OK", (dialog, which) -> {

                        Intent intent = new Intent(MainActivity.this,
                                PatientDetailsActivity.class);

                        intent.putExtra("name", name);
                        intent.putExtra("age", age);
                        intent.putExtra("phone", phone);
                        intent.putExtra("gender", gender);
                        intent.putExtra("illness", illness);
                        intent.putExtra("date", date);

                        startActivity(intent);
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });
    }
}