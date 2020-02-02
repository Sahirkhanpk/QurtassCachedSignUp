package com.example.myqurtassassignment.View;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Service;
import android.view.inputmethod.InputMethodManager;

import com.example.myqurtassassignment.DatabaseHelper;
import com.example.myqurtassassignment.Model.signUpModel;
import com.example.myqurtassassignment.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hbb20.CountryCodePicker;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
   private Button SignUp;
 private EditText fName,lName,phone,country,password,EmailAdress,phonecode,userType;
 CountryCodePicker ccp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intailize();

    }

    public void intailize(){
        fName = findViewById(R.id.etFName);
        lName = findViewById(R.id.etLName);
        phone = findViewById(R.id.etPhone);
        password = findViewById(R.id.etPassword);
        EmailAdress = findViewById(R.id.etEmail);
        country = findViewById(R.id.etCountry);
       userType = findViewById(R.id.etUtype);

        ccp = (CountryCodePicker) findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(phone);
       SignUp = findViewById(R.id.btnSignUp);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = fName.getText().toString();
                String name2 = lName.getText().toString();
                String email1 = EmailAdress.getText().toString();
              /*  String phonnum = phone.getText().toString();*/
                String phonnum = ccp.getFullNumber();
                String passw = password.getText().toString();
                String contry = country.getText().toString();
                String usertype = userType.getText().toString();

                if (name1.isEmpty() && email1.isEmpty() && phonnum.isEmpty() && name2.isEmpty() && passw.isEmpty() && contry.isEmpty()&& usertype.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please Enter ALL Fields correctly", Toast.LENGTH_LONG).show();
                } else if (!name1.matches("^[a-zA-Z ]+$")) {
                    fName.setError("User Name Must Contain Only Alphabets");
                   fName.requestFocus();
                }
                else if (!name2.matches("^[a-zA-Z ]+$")) {
                    lName.setError("User Name Must Contain Only Alphabets");
                    lName.requestFocus();
                }else if (email1.equals("") || !email1.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
                )) {
                    EmailAdress.setError("Enter A Valid Email Address");
                } else if (phonnum.length() < 10 ) {
                   phone.setError("Please enter valid number ");
                    phone.requestFocus();
                }  else if (passw.length()<6) {
                    password.setError("password must contain minimum 6 characters");
                    password.requestFocus();
                }  else {


                          signupProcess(name1,name2,email1,phonnum,contry,passw,usertype);

                }

            }
        });
    }


    public void signupProcess(String name1, String name2, String email1, final String phonnum, String Country, String passw,String usertype) {
        List<signUpModel> Accounts = new ArrayList<>();
        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
        Gson gson = new Gson();


        List<String> Accountstings = databaseHelper.getAllAccount();

        Type listType = new TypeToken<signUpModel>() {
        }.getType();


        for (int i = 0; i < Accountstings.size(); i++) {

            signUpModel signUpModel1 = gson.fromJson(Accountstings.get(i), listType);
            Accounts.add(signUpModel1);
        }
        boolean accountAllreadyEsists = false;


        for (int i = 0; i < Accounts.size(); i++) {

            String VEmail = Accounts.get(i).getEmail();
            if (VEmail.equals(email1)) {
                accountAllreadyEsists = true;
            }
        }


        if (accountAllreadyEsists == false) {
            signUpModel signUp = new signUpModel();
            signUp.setFristName(name1);
            signUp.setLatName(name2);
            signUp.setEmail(email1);
            String countryCode = phonnum.substring(0, 3);
            String phoneNumber = phonnum.substring(3, phonnum.length());
            signUp.setPhoneCode(Integer.parseInt(countryCode));
            signUp.setMobileNumber(phoneNumber);
            signUp.setCountry(Integer.parseInt(Country));
            signUp.setPassword(passw);
            signUp.setUserType(usertype);

            String bb = gson.toJson(signUp);
            long id = databaseHelper.insertAccount(bb);
            fName.setText("");
            lName.setText("");
            phone.setText("");
            password.setText("");
            EmailAdress.setText("");
            userType.setText("");
            country.setText("");
            Toast.makeText(MainActivity.this, "Sign Up Successful" , Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(MainActivity.this, "Account Already Exits Email Address Taken Please login" , Toast.LENGTH_LONG).show();
        }
    }

    public void hidekeyboard (View view)
    {
        InputMethodManager imm = (InputMethodManager)this.getSystemService(Service.INPUT_METHOD_SERVICE);
        if(imm != null){
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

        }

    }
}
