package com.example.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Button button_convert;
    TextView output_text;
    EditText input_text;
    Spinner input_spinner,output_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });

        // Setup Spinners
        input_spinner = findViewById(R.id.input_spinner);
        output_spinner = findViewById(R.id.output_spinner);
        ArrayAdapter<CharSequence> adapter =  ArrayAdapter.createFromResource(this,R.array.units_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input_spinner.setAdapter(adapter);
        output_spinner.setAdapter(adapter);

        //Define Variables
        button_convert = findViewById(R.id.button_convert);
        output_text = findViewById(R.id.output_text);
        input_text = findViewById(R.id.input_text);

        button_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DecimalFormat f = new DecimalFormat("#.###");
                try{
                    output_text.setText(f.format(conversion(input_text,input_spinner,output_spinner)));
                }
                catch (Error e) {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    output_text.setText("Error");
                }
            }
        });
    }

    public float conversion(@NonNull EditText input, @NonNull Spinner input_unit, @NonNull Spinner output_unit) {
        float inputNum = Float.parseFloat(input.getText().toString());
        // If spinners are equal.
        if (input_unit.getSelectedItemPosition() == output_unit.getSelectedItemPosition()) {
            return inputNum;
        }
        // Error Handling
        if (input_unit.getSelectedItemPosition() <= 6 &&  output_unit.getSelectedItemPosition() > 6) {
                throw new Error("Non-Convertible Units");
        }
        if (input_unit.getSelectedItemPosition() > 6 &&  input_unit.getSelectedItemPosition() <= 11 && (output_unit.getSelectedItemPosition() <= 6 ||  output_unit.getSelectedItemPosition() > 11)) {
            throw new Error("Non-Convertible Units");
        }
        if (input_unit.getSelectedItemPosition() > 11 && output_unit.getSelectedItemPosition() <= 11 ) {
            throw new Error("Non-Convertible Units");
        }
        // Distance Measurements
        // Inches -> Feet
        if (input_unit.getSelectedItemPosition() == 0 && output_unit.getSelectedItemPosition() == 1)  {
            return (float) (inputNum / 12);
        }
        // Inches -> Yards
        if (input_unit.getSelectedItemPosition() == 0 && output_unit.getSelectedItemPosition() == 2)  {
            return (float) (inputNum / 36);
        }
        // Inches -> Miles
        if (input_unit.getSelectedItemPosition() == 0 && output_unit.getSelectedItemPosition() == 3)  {
            return (float) (inputNum / 63360);
        }
        // Inches -> cm
        if (input_unit.getSelectedItemPosition() == 0 && output_unit.getSelectedItemPosition() == 4)  {
            return (float) (inputNum * 2.54);
        }
        // Inches -> m
        if (input_unit.getSelectedItemPosition() == 0 && output_unit.getSelectedItemPosition() == 5)  {
            return (float) (inputNum * 0.0254);
        }
        // Inches -> km
        if (input_unit.getSelectedItemPosition() == 0 && output_unit.getSelectedItemPosition() == 6)  {
            return (float) (inputNum * 0.0000254);
        }
        // Feet -> Inches
        if (input_unit.getSelectedItemPosition() == 1 && output_unit.getSelectedItemPosition() == 0)  {
            return (float) (inputNum * 12);
        }
        // Feet -> Yards
        if (input_unit.getSelectedItemPosition() == 1 && output_unit.getSelectedItemPosition() == 2)  {
            return (float) (inputNum / 3);
        }
        // Feet -> Miles
        if (input_unit.getSelectedItemPosition() == 1 && output_unit.getSelectedItemPosition() == 3)  {
            return (float) (inputNum / 5280);
        }
        // Feet -> cm
        if (input_unit.getSelectedItemPosition() == 1 && output_unit.getSelectedItemPosition() == 4)  {
            return (float) (inputNum * 30.48);
        }
        // Feet -> m
        if (input_unit.getSelectedItemPosition() == 1 && output_unit.getSelectedItemPosition() == 5)  {
            return (float) (inputNum / 3.281);
        }
        // Feet -> km
        if (input_unit.getSelectedItemPosition() == 1 && output_unit.getSelectedItemPosition() == 6)  {
            return (float) (inputNum / 3281);
        }
        // yards -> inches
        if (input_unit.getSelectedItemPosition() == 2 && output_unit.getSelectedItemPosition() == 0)  {
            return (float) (inputNum * 36);
        }
        // yards -> feet
        if (input_unit.getSelectedItemPosition() == 2 && output_unit.getSelectedItemPosition() == 1)  {
            return (float) (inputNum * 3);
        }
        // yards -> miles
        if (input_unit.getSelectedItemPosition() == 2 && output_unit.getSelectedItemPosition() == 3)  {
            return (float) (inputNum / 1760);
        }
        // yards -> cm
        if (input_unit.getSelectedItemPosition() == 2 && output_unit.getSelectedItemPosition() == 4)  {
            return (float) (inputNum * 91.44);
        }
        // yards -> m
        if (input_unit.getSelectedItemPosition() == 2 && output_unit.getSelectedItemPosition() == 5)  {
            return (float) (inputNum / 1.094);
        }
        // yards -> km
        if (input_unit.getSelectedItemPosition() == 2 && output_unit.getSelectedItemPosition() == 6)  {
            return (float) (inputNum / 1094);
        }
        // miles -> inches
        if (input_unit.getSelectedItemPosition() == 3 && output_unit.getSelectedItemPosition() == 0)  {
            return (float) (inputNum * 63360);
        }
        // miles -> feet
        if (input_unit.getSelectedItemPosition() == 3 && output_unit.getSelectedItemPosition() == 1)  {
            return (float) (inputNum * 5280);
        }
        // miles -> yards
        if (input_unit.getSelectedItemPosition() == 3 && output_unit.getSelectedItemPosition() == 2)  {
            return (float) (inputNum * 1760);
        }
        // miles -> cm
        if (input_unit.getSelectedItemPosition() == 3 && output_unit.getSelectedItemPosition() == 4)  {
            return (float) (inputNum * 160900);
        }
        // miles -> m
        if (input_unit.getSelectedItemPosition() == 3 && output_unit.getSelectedItemPosition() == 5)  {
            return (float) (inputNum * 1609);
        }
        // miles -> km
        if (input_unit.getSelectedItemPosition() == 3 && output_unit.getSelectedItemPosition() == 6)  {
            return (float) (inputNum * 1.609);
        }
        // cm -> inches
        if (input_unit.getSelectedItemPosition() == 4 && output_unit.getSelectedItemPosition() == 0)  {
            return (float) (inputNum / 2.54);
        }
        // cm -> feet
        if (input_unit.getSelectedItemPosition() == 4 && output_unit.getSelectedItemPosition() == 1)  {
            return (float) (inputNum / 30.48);
        }
        // cm -> yards
        if (input_unit.getSelectedItemPosition() == 4 && output_unit.getSelectedItemPosition() == 2)  {
            return (float) (inputNum / 91.44);
        }
        // cm -> miles
        if (input_unit.getSelectedItemPosition() == 4 && output_unit.getSelectedItemPosition() == 3)  {
            return (float) (inputNum / 160900);
        }
        // cm -> m
        if (input_unit.getSelectedItemPosition() == 4 && output_unit.getSelectedItemPosition() == 5)  {
            return (float) (inputNum / 100);
        }
        // cm -> km
        if (input_unit.getSelectedItemPosition() == 4 && output_unit.getSelectedItemPosition() == 6)  {
            return (float) (inputNum / 100000);
        }
        // m -> inches
        if (input_unit.getSelectedItemPosition() == 5 && output_unit.getSelectedItemPosition() == 0)  {
            return (float) (inputNum * 39.37);
        }
        // m -> feet
        if (input_unit.getSelectedItemPosition() == 5 && output_unit.getSelectedItemPosition() == 1)  {
            return (float) (inputNum * 3.281);
        }
        // m -> yards
        if (input_unit.getSelectedItemPosition() == 5 && output_unit.getSelectedItemPosition() == 2)  {
            return (float) (inputNum * 1.094);
        }
        // m -> miles
        if (input_unit.getSelectedItemPosition() == 5 && output_unit.getSelectedItemPosition() == 3)  {
            return (float) (inputNum / 1609);
        }
        // m -> cm
        if (input_unit.getSelectedItemPosition() == 5 && output_unit.getSelectedItemPosition() == 4)  {
            return (float) (inputNum * 100);
        }
        // m -> km
        if (input_unit.getSelectedItemPosition() == 5 && output_unit.getSelectedItemPosition() == 6)  {
            return (float) (inputNum / 1000);
        }
        // km -> inches
        if (input_unit.getSelectedItemPosition() == 6 && output_unit.getSelectedItemPosition() == 0)  {
            return (float) (inputNum * 39370);
        }
        // km -> feet
        if (input_unit.getSelectedItemPosition() == 6 && output_unit.getSelectedItemPosition() == 1)  {
            return (float) (inputNum * 3281);
        }
        // km -> yards
        if (input_unit.getSelectedItemPosition() == 6 && output_unit.getSelectedItemPosition() == 2)  {
            return (float) (inputNum * 1094);
        }
        // km -> miles
        if (input_unit.getSelectedItemPosition() == 6 && output_unit.getSelectedItemPosition() == 3)  {
            return (float) (inputNum / 1.609);
        }
        // km -> cm
        if (input_unit.getSelectedItemPosition() == 6 && output_unit.getSelectedItemPosition() == 4)  {
            return (float) (inputNum * 100000);
        }
        // km -> m
        if (input_unit.getSelectedItemPosition() == 6 && output_unit.getSelectedItemPosition() == 5)  {
            return (float) (inputNum * 1000);
        }

        //Weight Conversions
        // ounce -> pounds
        if (input_unit.getSelectedItemPosition() == 7 && output_unit.getSelectedItemPosition() == 8)  {
            return (float) (inputNum / 16);
        }
        // ounce -> tons
        if (input_unit.getSelectedItemPosition() == 7 && output_unit.getSelectedItemPosition() == 9)  {
            return (float) (inputNum / 32000);
        }
        // ounce -> g
        if (input_unit.getSelectedItemPosition() == 7 && output_unit.getSelectedItemPosition() == 10)  {
            return (float) (inputNum * 28.3495);
        }
        // ounce -> kg
        if (input_unit.getSelectedItemPosition() == 7 && output_unit.getSelectedItemPosition() == 11)  {
            return (float) (inputNum / 35.274);
        }
        // pounds -> ounces
        if (input_unit.getSelectedItemPosition() == 8 && output_unit.getSelectedItemPosition() == 7)  {
            return (float) (inputNum * 16);
        }
        // pounds -> tons
        if (input_unit.getSelectedItemPosition() == 8 && output_unit.getSelectedItemPosition() == 9)  {
            return (float) (inputNum / 2000);
        }
        // pounds -> g
        if (input_unit.getSelectedItemPosition() == 8 && output_unit.getSelectedItemPosition() == 10)  {
            return (float) (inputNum * 453.592);
        }
        // pounds -> kg
        if (input_unit.getSelectedItemPosition() == 8 && output_unit.getSelectedItemPosition() == 11)  {
            return (float) (inputNum * 0.453592);
        }
        // Ton -> ounces
        if (input_unit.getSelectedItemPosition() == 9 && output_unit.getSelectedItemPosition() == 7)  {
            return (float) (inputNum * 32000);
        }
        // Ton -> pound
        if (input_unit.getSelectedItemPosition() == 9 && output_unit.getSelectedItemPosition() == 8)  {
            return (float) (inputNum * 2000);
        }
        // Ton -> g
        if (input_unit.getSelectedItemPosition() == 9 && output_unit.getSelectedItemPosition() == 10)  {
            return (float) (inputNum * 907200);
        }
        // Ton -> kg
        if (input_unit.getSelectedItemPosition() == 9 && output_unit.getSelectedItemPosition() == 11)  {
            return (float) (inputNum * 907.2);
        }
        // g -> ounces
        if (input_unit.getSelectedItemPosition() == 10 && output_unit.getSelectedItemPosition() == 7)  {
            return (float) (inputNum / 28.35);
        }
        // g -> pound
        if (input_unit.getSelectedItemPosition() == 10 && output_unit.getSelectedItemPosition() == 8)  {
            return (float) (inputNum / 453.6);
        }
        // g -> ton
        if (input_unit.getSelectedItemPosition() == 10 && output_unit.getSelectedItemPosition() == 9)  {
            return (float) (inputNum / 907200);
        }
        // g -> kg
        if (input_unit.getSelectedItemPosition() == 10 && output_unit.getSelectedItemPosition() == 11)  {
            return (float) (inputNum / 1000);
        }
        // kg -> ounces
        if (input_unit.getSelectedItemPosition() == 11 && output_unit.getSelectedItemPosition() == 7)  {
            return (float) (inputNum * 35.274);
        }
        // kg -> pound
        if (input_unit.getSelectedItemPosition() == 11 && output_unit.getSelectedItemPosition() == 8)  {
            return (float) (inputNum / 0.453592);
        }
        // kg -> ton
        if (input_unit.getSelectedItemPosition() == 11 && output_unit.getSelectedItemPosition() == 9)  {
            return (float) (inputNum / 907.2);
        }
        // kg -> g
        if (input_unit.getSelectedItemPosition() == 11 && output_unit.getSelectedItemPosition() == 10)  {
            return (float) (inputNum * 1000);
        }
        //Temperature Conversions
        // C -> F
        if (input_unit.getSelectedItemPosition() == 12 && output_unit.getSelectedItemPosition() == 13)  {
            return (float) ((inputNum * 1.8) + 32);
        }
        // C -> K
        if (input_unit.getSelectedItemPosition() == 12 && output_unit.getSelectedItemPosition() == 14)  {
            return (float) (inputNum + 273.15);
        }
        // F -> C
        if (input_unit.getSelectedItemPosition() == 13 && output_unit.getSelectedItemPosition() == 12)  {
            return (float) ((inputNum- 32) / 1.8);
        }
        // F-> K
        if (input_unit.getSelectedItemPosition() == 13 && output_unit.getSelectedItemPosition() == 14)  {
            return (float) (((inputNum- 32) / 1.8) + 273.15);
        }
        // K -> C
        if (input_unit.getSelectedItemPosition() == 14 && output_unit.getSelectedItemPosition() == 12)  {
            return (float) (inputNum - 273.15);
        }
        // K -> F
        if (input_unit.getSelectedItemPosition() == 14 && output_unit.getSelectedItemPosition() == 13)  {
            return (float) (((inputNum - 273.15) * 1.8) + 32 );
        }
        return 0;
    }
}