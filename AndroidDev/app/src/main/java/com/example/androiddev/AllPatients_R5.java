package com.example.androiddev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AllPatients_R5 extends AppCompatActivity {
    private String myIP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allpatients_r5);
        Intent intent = getIntent();
        myIP = intent.getStringExtra("Ip");
        String vatRegNum = intent.getStringExtra("vat_reg_num");

        List<Patient> patientList;

        // Switch from AllPatients to Today's Appointments
        TextView myTextView = findViewById(R.id.appointmentsTextView);
        myTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the new activity
                Intent intent = new Intent(AllPatients_R5.this, TodaysPatientsR5.class);
                intent.putExtra("Ip", myIP);
                intent.putExtra("vat_reg_num", vatRegNum);
                startActivity(intent);
            }
        });

        // Initialize ScrollView and linearLayout to display patient's info
        ScrollView scrollView = findViewById(R.id.patientsScrollView);
        LinearLayout linearLayout = scrollView.findViewById(R.id.patientLinearLayout);

        // Create an instance of OkHttpHandler and call populateScrollView to fetch the patients data
        OkHttpHandler okHttpHandler = new OkHttpHandler();
        try {
            patientList = okHttpHandler.populateScrollView("http://" + myIP + "/flexFitDBServices/get_patients.php?vatRegNum=" + vatRegNum);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        // Inflating Patient Cards with patients' data
        for (Patient patient : patientList) {
            inflatePatientCards(linearLayout, patient);
        }

        // Initializing the SearchView
        SearchView searchView = findViewById(R.id.patientsSearchView);
        List<Patient> filteredPatientList = new ArrayList<>();
        final List<Patient> finalPatientList = patientList;

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterPatients(newText, filteredPatientList, finalPatientList, linearLayout);
                return true;
            }
        });

    }

    private void inflatePatientCards(LinearLayout linearLayout, Patient patient) {
        // Inflate the patient card layout
        View cardView = getLayoutInflater().inflate(R.layout.activity_patient_card, linearLayout, false);
        // Set the patient's information in the card
        TextView nameTextView = cardView.findViewById(R.id.patientName);
        TextView appointmentTextView = cardView.findViewById(R.id.nextAppointment);
        TextView caseTextView = cardView.findViewById(R.id.patientCase);

        nameTextView.setText(patient.getName());
        appointmentTextView.setText("Next appointment: " + patient.getNextAppointment() + " " + patient.getNextAppointmentTime());
        caseTextView.setText("Case: " + patient.getCase());

        // Set the patient's image in the card
        ImageView imageView = cardView.findViewById(R.id.patientImage);
        imageView.setImageResource(patient.getImageResource());

        // Set an OnClickListener to show the patient details (move to R5_3)
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the PatientDetailsActivity
                Intent intent = new Intent(AllPatients_R5.this, PatientContactR5.class);

                // Pass the patient object to the intent
                intent.putExtra("Ip", myIP);
                intent.putExtra("patient", patient);

                // Start the activity
                startActivity(intent);
            }
        });

        linearLayout.addView(cardView);

    }

    private void filterPatients(String query, List<Patient> filtered, List<Patient> patients, LinearLayout linearLayout) {
        filtered.clear();
        for (Patient patient : patients) {
            if (patient.getName().toLowerCase().contains(query.toLowerCase())) {
                filtered.add(patient);
            }
        }
        updatePatientCards(linearLayout, filtered);
    }

    private void updatePatientCards(LinearLayout linearLayout, List<Patient> filtered) {
        linearLayout.removeAllViews();
        for (Patient patient : filtered) {
            inflatePatientCards(linearLayout, patient);
        }
    }
}