package desarrollandoandroid.notificaciones;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.Toast;
import java.util.Calendar;

/**
 * Creado por Pablo Bascuñana el 20/12/14.
 */
public class DatePickerActivity extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
// Usamos la fecha actual como valores por defecto para seleccionar por el usuario
        final Calendar mCalendar = Calendar.getInstance();
        int year = mCalendar.get(Calendar.YEAR);
        int month = mCalendar.get(Calendar.MONTH);
        int day = mCalendar.get(Calendar.DAY_OF_MONTH);
// Creamos una instancia del TimePickerDialog y la devolvemos
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
// Creo un StringBuffer para almacenar en el la fecha seleccionada por el usuario.
        StringBuffer mFecha = new StringBuffer();
        mFecha.append(dayOfMonth).append("/").append(monthOfYear).append("/").append(year);
        Toast.makeText(getActivity(), mFecha, Toast.LENGTH_LONG).show();
    }
}



