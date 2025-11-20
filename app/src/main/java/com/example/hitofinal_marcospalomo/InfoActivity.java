package com.example.hitofinal_marcospalomo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Actividad para mostrar la información transferida desde LoginActivity
 */
public class InfoActivity extends AppCompatActivity {

    private TextView textoMandado;
    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        textoMandado = findViewById(R.id.tv_mensaje_recibido);
        mostrarInformacionTransferida();
    }

    /**
     * Muestra la información transferida desde LoginActivity
     */
    private void mostrarInformacionTransferida() {
        // Obtener el Intent que inició esta actividad
        Intent intent = getIntent();

        if (intent != null) {
            // Obtener el mensaje usando getStringExtra
            String mensaje = intent.getStringExtra("MENSAJE_TRANSFERIDO");
            String usuario = intent.getStringExtra("USUARIO");

            if (mensaje != null && !mensaje.isEmpty()) {
                String textoCompleto = "Mensaje: " + mensaje;
                textoMandado.setText(textoCompleto);
            } else {
                textoMandado.setText("No se recibió ningún mensaje");
            }
        }
    }

}