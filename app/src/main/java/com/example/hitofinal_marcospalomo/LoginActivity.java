package com.example.hitofinal_marcospalomo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

    /**
     * Actividad de login para autenticación de usuario
     * Maneja la validación de entrada y la navegación al juego
     */
    public class LoginActivity extends AppCompatActivity {

        private EditText etUsuario, etContrasena;
        private Button btnLogin;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            inicializarVistas();
            configurarBotonLogin();
        }

        /**
         * Inicializa todos los componentes de la UI
         */
        private void inicializarVistas() {
            etUsuario = findViewById(R.id.et_usuario);
            etContrasena = findViewById(R.id.et_contrasena);
            btnLogin = findViewById(R.id.btn_login);
        }

        /**
         * Configura el botón de login con validación
         */
        private void configurarBotonLogin() {
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String usuario = etUsuario.getText().toString();
                    String contrasena = etContrasena.getText().toString();

                    if (validarEntradas(usuario, contrasena)) {
                        navegarAlJuego(usuario);
                    }
                }
            });
        }

        /**
         * Valida las entradas del usuario
         * @param usuario El nombre de usuario a validar
         * @param contrasena La contraseña a validar
         * @return true si las entradas son válidas, false en caso contrario
         */
        private boolean validarEntradas(String usuario, String contrasena) {
            if (usuario.isEmpty()) {
                mostrarToast("Por favor, introduce un nombre de usuario");
                return false;
            }

            if (contrasena.isEmpty()) {
                mostrarToast("Por favor, introduce una contraseña");
                return false;
            }

            if (usuario.length() < 3) {
                mostrarToast("El nombre de usuario debe tener al menos 3 caracteres");
                return false;
            }

            return true;
        }

        /**
         * Navega a la actividad del juego
         * @param usuario El nombre de usuario para pasar a la actividad del juego
         */
        private void navegarAlJuego(String usuario) {
            Intent intent = new Intent(LoginActivity.this, GameActivity.class);
            intent.putExtra("USUARIO", usuario);
            startActivity(intent);
        }

        /**
         * Muestra un mensaje Toast
         * @param mensaje El mensaje a mostrar
         */
        private void mostrarToast(String mensaje) {
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        }
    }
