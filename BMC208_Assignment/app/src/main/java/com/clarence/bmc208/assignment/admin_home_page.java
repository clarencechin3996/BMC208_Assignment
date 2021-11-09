package com.clarence.bmc208.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class admin_home_page extends AppCompatActivity {

    Button recordVaccineBatch;
    Button viewVaccineBatchInfoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);
        viewVaccineBatchInfoBtn = findViewById(R.id.button_view_vaccine_batch_info);

        viewVaccineBatchInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(admin_home_page.this, vaccineBatchList.class));
            }
        });
    }
}