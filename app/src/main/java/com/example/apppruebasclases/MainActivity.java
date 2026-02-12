package com.example.apppruebasclases;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText Numeros1, Numeros2;
    Button BtnSumar, BtnResta, BtnDivison, BtnMulti;

    TextView txtResultado;

    public static class OperacionesMatematicas {
        private double  num1;
        private double num2;

        // Constructor que servirá para pasar los datos
        public OperacionesMatematicas(double n1, double n2) {
            this.num1 = n1;
            this.num2 = n2;
        }

        public double sumar() { return num1 + num2; }
        public double restar() { return num1 - num2; }
        public double multiplicar() { return num1 * num2; }

        public String dividir() {
            if (num2 == 0) return "Error: División por 0";
            return String.valueOf(num1 / num2);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Numeros1 = (EditText) findViewById(R.id.numeros1);
        Numeros2 = (EditText) findViewById(R.id.numeros2);
        BtnSumar = (Button) findViewById(R.id.btnSuma);
        BtnResta = (Button) findViewById(R.id.btnResta);
        BtnDivison = (Button) findViewById(R.id.btnDivision);
        BtnMulti = (Button) findViewById(R.id.btnmulti);
        txtResultado = findViewById(R.id.txtResultado);

        BtnSumar.setOnClickListener(v -> calcular("suma"));
        BtnResta.setOnClickListener(v -> calcular("resta"));
        BtnMulti.setOnClickListener(v -> calcular("multi"));
        BtnDivison.setOnClickListener(v -> calcular("divi"));
    }
    // Método para procesar la lógica
    @SuppressLint("SetTextI18n")
    private void calcular(String operacion) {
        String n1String = Numeros1.getText().toString();
        String n2String = Numeros2.getText().toString();

        // VALIDACIÓN: ¿Están vacíos?
        if (n1String.isEmpty() || n2String.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese ambos números", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double n1 = Double.parseDouble(n1String);
            double n2 = Double.parseDouble(n2String);

            OperacionesMatematicas op = new OperacionesMatematicas(n1, n2);
            String resultado = "";

            switch (operacion) {
                case "suma": resultado = String.valueOf(op.sumar()); break;
                case "resta": resultado = String.valueOf(op.restar()); break;
                case "multi": resultado = String.valueOf(op.multiplicar()); break;
                case "divi": resultado = op.dividir(); break;
            }

            txtResultado.setText("Resultado: " + resultado);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Ingrese valores numéricos válidos", Toast.LENGTH_SHORT).show();
        }
    }

}

