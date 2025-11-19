package com.example.hitofinal_marcospalomo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
/**
 * Actividad principal del juego con sistema de niveles y mejoras
 */
public class GameActivity extends AppCompatActivity {

    private Button btnTapar; ;
    private Button btnMejorar;
    private Button btnSalir;


    private TextView tvPuntos;
    private TextView tvNivel;
    private TextView tvMultiplicador;
    private TextView tvProgreso;
    private TextView tvTapsTotal;

    private int puntos = 0;
    private int nivel = 1;
    private int multiplicador = 1;
    private int taps = 0;
    private int tapsTotales = 0;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        inicializarVistas();
        cargarDatos();
        configurarBotones();
        actualizarUI();
    }

    private void inicializarVistas() {
        btnTapar = findViewById(R.id.btn_tapar);
        btnMejorar = findViewById(R.id.btn_mejorar);
        btnSalir = findViewById(R.id.btn_salir);
        tvPuntos = findViewById(R.id.tv_puntos);
        tvNivel = findViewById(R.id.tv_nivel);
        tvMultiplicador = findViewById(R.id.tv_multiplicador);
        tvProgreso = findViewById(R.id.tv_progreso);
        tvTapsTotal = findViewById(R.id.tv_taps_total);
    }

    private void cargarDatos() {
        sharedPreferences = getSharedPreferences("TapSoulsPrefs", MODE_PRIVATE);
        puntos = sharedPreferences.getInt("puntos", 0);
        nivel = sharedPreferences.getInt("nivel", 1);
        multiplicador = sharedPreferences.getInt("multiplicador", 1);
        taps = sharedPreferences.getInt("taps", 0);
        tapsTotales = sharedPreferences.getInt("tapsTotales", 0);
    }

    private void configurarBotones() {
        btnTapar.setOnClickListener(new View.OnClickListener() {
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
                resetearDatos(); // Resetear los datos antes de salir
                finish(); // Cierra la actividad y vuelve al login
            }
        });
    }

    private void tap() {
        puntos += multiplicador;
        taps++;
        tapsTotales++;

        // Sistema de niveles infinitos - cada nivel requiere 40 taps más
        int tapsParaSiguienteNivel = nivel * 40;

        if (taps >= tapsParaSiguienteNivel) {
            nivel++;
            taps = 0; // Reiniciamos contador de taps para el nuevo nivel
            mostrarMensaje("¡Nivel " + nivel + " alcanzado!");
        }
        actualizarUI();
    }

    private void mejorar() {
        int costo = 20 * multiplicador;

        if (puntos >= costo) {
            puntos -= costo;
            multiplicador++;
            actualizarUI();
            mostrarMensaje("¡Multiplicador x" + multiplicador + "!");
        } else {
            mostrarMensaje("Necesitas " + costo + " puntos");
        }
    }

    private void actualizarUI() {
        tvPuntos.setText("Puntos: " + puntos);
        tvNivel.setText("Nivel: " + nivel);
        tvMultiplicador.setText("Multiplicador: x" + multiplicador);
        tvTapsTotal.setText("Taps totales: " + tapsTotales);

        int tapsParaSiguienteNivel = nivel * 40;
        tvProgreso.setText(taps + "/" + tapsParaSiguienteNivel + " taps");

        btnMejorar.setText("Mejorar (" + (20 * multiplicador) + " pts)");
    }

    /**
     * Resetea todos los datos del juego a sus valores iniciales
     */
    private void resetearDatos() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("puntos", 0);
        editor.putInt("nivel", 1);
        editor.putInt("multiplicador", 1);
        editor.putInt("taps", 0);
        editor.putInt("tapsTotales", 0);
        editor.apply();

        // También resetea las variables en memoria
        puntos = 0;
        nivel = 1;
        multiplicador = 1;
        taps = 0;
        tapsTotales = 0;
    }

    private void mostrarMensaje(String mensaje) {
        android.widget.Toast.makeText(this, mensaje, android.widget.Toast.LENGTH_SHORT).show();
    }

}