package mx.itson.calculadora;

import android.content.Context;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import static mx.itson.calculadora.R.id;
import static mx.itson.calculadora.R.layout;

/**
 * Clase principal de la aplicación
 *
 * @author Diego Castro
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // Almacena al primer valor ingresado
    private EditText txtPrimerValor;

    // Almacena al segundo valor ingresado
    private EditText txtSegundoValor;

    // Patrones de vibración
    private static final long[] onda = {0, 100, 70, 100, 70, 100};
    private static final long[] pulso = {0, 1000, 0, 0, 0, 0};

    /**
     * Se ejecuta cuando se crea la actividad
     *
     * @param savedInstanceState El estado de la actividad
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        // Obtiene las referencias de los EditText
        txtPrimerValor = findViewById(id.txtPrimerValor);
        txtSegundoValor = findViewById(id.txtSegundoValor);

        // Obtener las referencias de los botones y registra el listener
        Button btnSuma = findViewById(id.btnSumar);
        btnSuma.setOnClickListener(this);

        Button btnResta = findViewById(id.btnRestar);
        btnResta.setOnClickListener(this);

        Button btnMultiplicacion = findViewById(id.btnMultiplicar);
        btnMultiplicacion.setOnClickListener(this);

        Button btnDivision = findViewById(id.btnDividir);
        btnDivision.setOnClickListener(this);

    }

    /**
     * Maneja los eventos de clic de los botones
     *
     * @param v La vista que generó el evento
     */
    @Override
    public void onClick(View v) {

        // Obtener una referencia al servicio de vibración
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Obtener los valores de los EditText como cadenas de texto
        String primerValorStr = txtPrimerValor.getText().toString();
        String segundoValorStr = txtSegundoValor.getText().toString();

        // Comprueba si los valores ingresados son números válidos
        if (!esNumeroValido(primerValorStr) || !esNumeroValido(segundoValorStr)) {
            // Mostrar un mensaje de error si no se ingresaron números válidos
            Toast.makeText(this, "Ingrese dos números válidos", Toast.LENGTH_SHORT).show();
            vibrator.vibrate(VibrationEffect.createWaveform(pulso, -1));
            return;
        }

        // Convierte los valores ingresados a double
        double primerValor = Double.parseDouble(primerValorStr);
        double segundoValor = Double.parseDouble(segundoValorStr);

        // Comprueba el botón que generó el evento
        switch (v.getId()) {
            case id.btnSumar:
                // Realizar la operación de suma y muestra el resultado
                mostrarResultado(primerValor + segundoValor);

                vibrator.vibrate(VibrationEffect.createWaveform(onda, -1));
                break;
            case id.btnRestar:
                // Realizar la operación de resta y muestra el resultado
                mostrarResultado(primerValor - segundoValor);
                vibrator.vibrate(VibrationEffect.createWaveform(onda, -1));

                break;
            case id.btnMultiplicar:
                // Realizar la operación de multiplicación y muestra el resultado
                mostrarResultado(primerValor * segundoValor);
                vibrator.vibrate(VibrationEffect.createWaveform(onda, -1));

                break;
            case id.btnDividir:
                // Comprobar si el segundo valor es cero
                if (segundoValor == 0 || primerValor == 0) {
                    // Mostrar un mensaje de error si se intenta dividir entre cero
                    Toast.makeText(this, "No se puede dividir entre cero", Toast.LENGTH_SHORT).show();
                    vibrator.vibrate(VibrationEffect.createWaveform(pulso, -1));

                    return;
                }
                // Realizar la operación de división y muestra el resultado
                mostrarResultado(primerValor / segundoValor);
                vibrator.vibrate(VibrationEffect.createWaveform(onda, -1));

                break;
        }
    }

    /**
     * Muestra el resultado de la operación en un mensaje Toast
     *
     * @param resultado El resultado de la operación
     */
    private void mostrarResultado(double resultado) {
        // Muestra el resultado en un mensaje Toast
        Toast.makeText(this, "El resultado es: " + resultado, Toast.LENGTH_SHORT).show();
    }

    /**
     * Comprueba si una cadena de texto es un número válido
     *
     * @param numeroStr La cadena de texto a comprobar
     *
     * @return true si la cadena de texto es un número válido, false en caso contrario
     */
    private boolean esNumeroValido(String numeroStr) {
        try {
            // Intenta convertir la cadena de texto a un número
            Double.parseDouble(numeroStr);
            return true;
        } catch (NumberFormatException e) {
            // La cadena de texto no se pudo convertir a un número
            return false;
        }
    }
}
