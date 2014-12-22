package desarrollandoandroid.notificaciones;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by pablo on 20/12/14.
 */
public class TimePickerActivity extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
// Usamos la hora actual como valores por defecto para seleccionar por el usuario
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
// Creamos una instancia del TimePickerDialog y la devolvemos
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
// Creo un StringBuffer para almacenar en el la hora seleccionada por el usuario.
        StringBuffer mHora = new StringBuffer();
        mHora.append(hourOfDay).append(":").append(minute);
        Toast.makeText(getActivity(), mHora, Toast.LENGTH_LONG).show();
    }
}
