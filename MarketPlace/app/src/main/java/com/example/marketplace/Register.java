package com.example.marketplace;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class Register extends MainActivity {
    CheckBox Policy;
    LinearLayout Contenedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setSupportActionBar(findViewById(R.id.toolbar));

        Name = findViewById(R.id.nombre);
        Lastname = findViewById(R.id.apellido);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        Telephone = findViewById(R.id.telefono);
        Policy = findViewById(R.id.policy);
        Contenedor = findViewById(R.id.contenedor);
        Contenedor.setVisibility(View.INVISIBLE);
    }


    public void submit(View view) {
        if (validate("name", Name)) {
            if (validate("lastname", Lastname)) {
                if (validate("email", Email)) {
                    if (validate("password", Password)) {
                        if (validate("telephone", Telephone)) {
                            if (Policy.isChecked()) {
                                User newUser = new User(Name.getText().toString(), Lastname.getText().toString(), Email.getText().toString(), Password.getText().toString(), Telephone.getText().toString());
                                boolean userExists = saveAccount(newUser);
                                if (userExists) {
                                    showToast(getString(R.string.userExists));
                                } else {
                                    Intent MarketView = new Intent(view.getContext(), Market.class);
                                    MarketView.putExtra("user", newUser.getFullname());
                                    startActivity(MarketView);
                                    showToast(getString(R.string.createProfile));
                                }

                            } else {
                                showToast(getString(R.string.checkPolicy));
                            }
                        }
                    }
                }
            }
        }
    }


    // policy

    public void openPolicy(View view) {
        Contenedor.setVisibility(View.VISIBLE);
        Policy.setChecked(true);
    }

    public void closePolicy(View view) {
        Contenedor.setVisibility(View.INVISIBLE);
    }

}