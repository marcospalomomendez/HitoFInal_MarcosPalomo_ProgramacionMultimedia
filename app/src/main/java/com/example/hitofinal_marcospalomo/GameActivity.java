package com.example.hitofinal_marcospalomo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Actividad principal del juego con sistema de niveles y mejoras
 */
public class GameActivity extends AppCompatActivity {

    private Button btnTap; ;
    private Button btnMejorar;
    private Button btnSalir;
    private TextView textPuntos;
    private TextView textNivel;
    private TextView textMultiplicador;
    private TextView textProgreso;
    private TextView textTapsTotal;
    private int puntos = 0;
    private int nivel = 1;
    private int multiplicador = 1;
    private int taps = 0;
    private int tapsTotales = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        btnTap = findViewById(R.id.btn_tapar);
        btnMejorar = findViewById(R.id.btn_mejorar);
        btnSalir = findViewById(R.id.btn_salir);
        textPuntos = findViewById(R.id.tv_puntos);
        textNivel = findViewById(R.id.tv_nivel);
        textMultiplicador = findViewById(R.id.tv_multiplicador);
        textProgreso = findViewById(R.id.tv_progreso);
        textTapsTotal = findViewById(R.id.tv_taps_total);
        configurarBotones();
        actualizarInterfaz();
    }

    private void configurarBotones() {
        btnTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tap();
            }
        });

        btnMejorar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mejorar();
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void tap() {
        puntos += multiplicador;
        taps++;
        tapsTotales++;

        int tapsParaSiguienteNivel = nivel * 40;
        if (taps >= tapsParaSiguienteNivel) {
            nivel++;
            taps = 0;
            mostrarMensaje("¡Nivel " + nivel + " alcanzado!");

        }
        actualizarInterfaz();
    }

    private void mejorar() {
        int costo = 20 * multiplicador;

        if (puntos >= costo) {
            puntos -= costo;
            multiplicador++;
            actualizarInterfaz();
            mostrarMensaje("¡Multiplicador x" + multiplicador + "!");
        } else {
            mostrarMensaje("Necesitas " + costo + " puntos");
        }
    }

    private void actualizarInterfaz() {
        textPuntos.setText("Puntos: " + puntos);
        textNivel.setText("Nivel: " + nivel);
        textMultiplicador.setText("Multiplicador: x" + multiplicador);
        textTapsTotal.setText("Taps totales: " + tapsTotales);

        int tapsParaSiguienteNivel = nivel * 40;
        textProgreso.setText(taps + "/" + tapsParaSiguienteNivel + " taps");

        btnMejorar.setText("Mejorar (" + (20 * multiplicador) + " pts)");
    }

    private void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, android.widget.Toast.LENGTH_SHORT).show();
    }

}