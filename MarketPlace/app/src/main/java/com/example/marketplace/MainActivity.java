package com.example.marketplace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    EditText Name, Lastname, Email, Password, Telephone;
    String userFound = "", Repository = "marketAccounts";
    Hashtable<String,String[]> patterns = new Hashtable<String,String[]>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar));

        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);

        patterns.put("name", new String[] {"[ a-zA-Z\\x80-\\xFC]+"});
        patterns.put("lastname", patterns.get("name"));
        patterns.put("email", new String[] {".+\\@.+\\..+"});
        patterns.put("password", new String[] {".{8,}", ".*\\d.*", ".*[A-Z].*", ".*[a-z].*", ".*[^ \\w].*"});
        patterns.put("telephone", new String[] {"\\d{7,16}"});
    }


    public void authenticate(View view) {
        if (validate("email", Email)) {
            if (getAccount(Email.getText().toString(), Password.getText().toString())) {
                Intent MarketView = new Intent(view.getContext(), Market.class);
                if (userFound != "") MarketView.putExtra("user", userFound);
                startActivity(MarketView);
            } else {
                showToast(getString(R.string.checkCredentials));
            }
        }
    }

    public void openRegister(View view) {
        Intent Register = new Intent(view.getContext(), Register.class);
        startActivity(Register);
    }


    // shared functions

    public boolean validate(String field, EditText edit) {
        boolean status = true, matchFound;
        int langId = getResources().getIdentifier(field, "string", getPackageName());
        String langText = getString(langId);
        String console = " \n [" + langText + " Validation: ";
        for (String pattern: patterns.get(field)) {
            matchFound = Pattern.matches(pattern, edit.getText().toString());

            //TODO: almacenar console como array
            console += "/" + pattern + "/ = " + matchFound + ", ";

            if (!matchFound) {

                //TODO: identificar genero y plurales
                showToast(getString(R.string.enInvalid) + langText + getString(R.string.esInvalid));

                status = false;
                break;
            }
        }
        System.out.println(console + "]");
        return status;
    }

    public boolean saveAccount(User newUser) {
        boolean userExists = false;
        try {
            // confirmar si el email ya está registrado
            checkRepository();
            FileInputStream FI = openFileInput(Repository);
            InputStreamReader stream = new InputStreamReader(FI);
            BufferedReader buffer = new BufferedReader(stream);
            String line = null;
            while ((line = buffer.readLine()) != null) {
                if (line != "") {
                    User singleUser = new ObjectMapper().readValue(line, User.class);
                    if (compare(singleUser.getEmail(), newUser.getEmail())) {
                        userFound = singleUser.getFullname();
                        System.out.println("[Account Already Exists: " + new ObjectMapper().writeValueAsString(singleUser) + "]");
                        return true;
                    }
                }
            }
            FI.close();
            stream.close();

            // crear nuevo usuario
            String account = new ObjectMapper().writeValueAsString(newUser);
            FileOutputStream FO = openFileOutput(Repository, Context.MODE_APPEND);
            FO.write((account + "\n").getBytes());
            FO.close();
            System.out.println("[Account Created: " + account + "]");
        } catch (IOException e) {
            e.printStackTrace();
            userExists = true;
        }
        return userExists;
    }

    public boolean getAccount(String email, String password) {
        boolean status = false;
        try {
            checkRepository();
            FileInputStream FI = openFileInput(Repository);
            InputStreamReader stream = new InputStreamReader(FI);
            BufferedReader buffer = new BufferedReader(stream);
            String allUsers = "[Mapped Users:";
            String line = null;
            while ((line = buffer.readLine()) != null) {
                allUsers += " \n " + line;
                if (line != "") {
                    User singleUser = new ObjectMapper().readValue(line, User.class);
                    if (compare(singleUser.getEmail(), email) && compare(singleUser.getPassword(), password)) {
                        userFound = singleUser.getFullname();
                        System.out.println("[Account Authenticated: " + new ObjectMapper().writeValueAsString(singleUser) + "]");
                        status = true;
                        break;
                    }
                }
            }
            FI.close();
            stream.close();
            System.out.println(allUsers + "]");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return status;
    }

    public void checkRepository() throws IOException {
        // confirmar si el repositorio ya está creado
        try {
            FileInputStream FI = openFileInput(Repository);
            FI.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("<Repository doesn't exists>");
            FileOutputStream FO = openFileOutput(Repository, Context.MODE_PRIVATE);
            FO.close();
            System.out.println("[Repository " + Repository + " has been created]");
        }
    }

    public boolean compare(String data1, String data2){
        boolean test = data1.replace(data2, "")  == "";
        System.out.println(" \n Matching Data (" + test + "): [" + data2 + "] [" + data1 + "]");
        return test;
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}

// 46546@1231.131 dfsdf4646SDFSDF/*/