package com.example.assignment23;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText, idEditText, emailEditText, phoneEditText, dobEditText;
    private RadioGroup genderRadioGroup;
    private CheckBox newsletterCheckBox;
    private RatingBar extracurricularRatingBar;
    private SeekBar creditsSeekBar;
    private Switch switch1;
    private TextView resultsTextView;


    private final String NAME_REGEX = "^([A-Z][a-z]*\\.?)*(\\s[A-Z][a-z]+)+$";
    private final String EMAIL_REGEX = "^[\\w.-]+@lus\\.ac\\.bd$";
    private final String PHONE_REGEX = "^01[3-9]\\d{8}$";
    private final String DOB_REGEX = "(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(19|20)\\d{2}$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nameEditText = findViewById(R.id.nameEditText);
        idEditText = findViewById(R.id.idEditText);
        emailEditText = findViewById(R.id.emailEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        dobEditText = findViewById(R.id.dobEditText);
        genderRadioGroup = findViewById(R.id.genderRadioGroup);
        newsletterCheckBox = findViewById(R.id.newsletterCheckBox);
        extracurricularRatingBar = findViewById(R.id.extracurricularRatingBar);
        creditsSeekBar = findViewById(R.id.creditsSeekBar);
        switch1 = findViewById(R.id.switch1);
        resultsTextView = findViewById(R.id.resultsTextView);
        Button submitButton = findViewById(R.id.submitButton);


        submitButton.setOnClickListener(v -> submitForm());
    }

    private void submitForm() {

        String name = nameEditText.getText().toString().trim();
        String id = idEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();
        String dob = dobEditText.getText().toString().trim();


        if (!validateName(name)) {
            showToast("Invalid name. Please enter a valid name.");
            return;
        }

        if (!validateEmail(email)) {
            showToast("Invalid email. Please enter a valid university email." +
                    "\nExample:cse_0182210012101099@lus.ac.bd");
            return;
        }

        if (!validatePhone(phone)) {
            showToast("Invalid phone number. Please enter a valid phone number.");
            return;
        }

        if (!validateDob(dob)) {
            showToast("Invalid date of birth. Please enter a valid date in DD/MM/YYYY format.");
            return;
        }


        int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();
        RadioButton selectedGenderButton = findViewById(selectedGenderId);
        String gender = selectedGenderButton != null ? selectedGenderButton.getText().toString() : "Not selected";


        String newsletter = newsletterCheckBox.isChecked() ? "Subscribed" : "Not Subscribed";


        float extracurricularRating = extracurricularRatingBar.getRating();


        int credits = creditsSeekBar.getProgress();


        String additionalGuidance = switch1.isChecked() ? "Yes" : "No";


        String result = "Name: " + name + "\n" +
                "ID: " + id + "\n" +
                "Email: " + email + "\n" +
                "Phone: " + phone + "\n" +
                "DOB: " + dob + "\n" +
                "Gender: " + gender + "\n" +
                "Newsletter: " + newsletter + "\n" +
                "Extracurricular Interest: " + extracurricularRating + " stars\n" +
                "Credits Enrolled: " + credits + "\n" +
                "Additional Guidance: " + additionalGuidance;

        resultsTextView.setText(result);
    }


    private boolean validateName(String name) {
        return Pattern.matches(NAME_REGEX, name);
    }

    private boolean validateEmail(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }

    private boolean validatePhone(String phone) {
        return Pattern.matches(PHONE_REGEX, phone);
    }

    private boolean validateDob(String dob) {
        return Pattern.matches(DOB_REGEX, dob);
    }


    private void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}

