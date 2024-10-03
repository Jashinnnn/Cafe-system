package com.example.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class addProduct extends AppCompatActivity {

    EditText popName, popQuantity, popStocks, popUtd;
    Button btnSave, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        // Initialize Firebase
        FirebaseApp.initializeApp(this);

        popName = findViewById(R.id.popName);
        popQuantity = findViewById(R.id.popQuantity);
        popStocks = findViewById(R.id.popStocks);
        popUtd = findViewById(R.id.popUtd);

        btnSave = findViewById(R.id.btnSave);
        btnBack = findViewById(R.id.btnBack);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    insertData();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(addProduct.this, products.class);
                startActivity(intent1);
                finish();
            }
        });
    }

    private boolean validateInputs() {
        if (popName.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter product name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (popQuantity.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter quantity", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (popStocks.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter stocks", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (popUtd.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter UTD (Updated Time)", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void insertData() {
        Map<String, Object> map = new HashMap<>();
        map.put("Name", popName.getText().toString());
        map.put("Quantity", popQuantity.getText().toString());
        map.put("Stocks", popStocks.getText().toString());
        map.put("Utd", popUtd.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("product").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(addProduct.this, "Data inserted Successfully", Toast.LENGTH_SHORT).show();
                        clearAll();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(addProduct.this, "Insert Data Unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void clearAll() {
        popName.setText("");
        popQuantity.setText("");
        popStocks.setText("");
        popUtd.setText("");
    }
}
