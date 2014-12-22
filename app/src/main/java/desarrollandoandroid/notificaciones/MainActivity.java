package desarrollandoandroid.notificaciones;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import java.util.Calendar;

/**
 * Creado por Pablo Bascuñana el 20/12/14.
 */
public class MainActivity extends FragmentActivity {

    Button mBtToast, mBtStatus, mBtAlert, mBtProgress, mBtDatePicker, mBtTimePicker;

    private static final int NOTIFICATION_ID = 1;

    AlertDialog.Builder mAlertDialog;
    AlertDialog mAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtToast = (Button) findViewById(R.id.buttonToast);
        mBtStatus = (Button) findViewById(R.id.buttonStatusBar);
        mBtAlert = (Button) findViewById(R.id.buttonAlert);
        mBtProgress = (Button) findViewById(R.id.buttonProgress);
        mBtDatePicker = (Button) findViewById(R.id.buttonTimePicker);
        mBtTimePicker = (Button) findViewById(R.id.buttonDatePicker);
// NotificationManager
        final NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// AlertDialog
        mAlertDialog = new AlertDialog.Builder(this); // Construimos el cuadro del diálogo
        mAlert = mAlertDialog.create(); // Creamos la alerta
        mAlertDialog.setTitle(getResources().getString(R.string.tituloAlertDialog)); // Establecemos el título
        mAlertDialog.setMessage(getResources().getString(R.string.alertDialogMessage)); // Establecemos el mensaje
        mAlertDialog.setCancelable(false); // El diálogo no desaparece pulsado la tecla de retroceso
// DatePicker
        Calendar mCalendar = Calendar.getInstance();
        int year = mCalendar.get(Calendar.YEAR);
        int month = mCalendar.get(Calendar.MONTH);
        int day = mCalendar.get(Calendar.DAY_OF_MONTH);

        mBtToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater mInflater = getLayoutInflater();
                View mLayout = mInflater.inflate(R.layout.toast_layout,
                        (ViewGroup) findViewById(R.id.toastLayout));

                Toast mToast = new Toast(getApplicationContext());
                mToast.setGravity(Gravity.RELATIVE_LAYOUT_DIRECTION, 0, 0); // Establecemos la posición del Toast en pantalla
                mToast.setView(mLayout); // Establecemos la vista del Toast
                mToast.setDuration(mToast.LENGTH_LONG); // Establecemos la duración del Toast
                mToast.show(); // Se muestra el Toast
            }
        });

        mBtStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // Icono y texto de que se muestra en la StatusBar en el instante en que la notificación es recibida
                Notification mNotification = new Notification(R.drawable.ic_launcher,
                        getResources().getString(R.string.notificacion), System.currentTimeMillis());
            // Cogemos el contexto de nuestra actividad y creamos el título y el texto que se muestra en la notificación
                Context mContext = getApplicationContext();
                String mTitulo = getResources().getString(R.string.titulo);
                String mTexto = getResources().getString(R.string.texto);
            // Agregamos Sonido y Vibración
                mNotification.defaults |= Notification.DEFAULT_SOUND;
                mNotification.defaults |= Notification.DEFAULT_VIBRATE;
            // Creamos el Intent que va a lanzar la notificación
                Intent mNotificationIntent = new Intent (MainActivity.this, MainActivity.class);
            // Creamos un PendingIntent que se ejecutará cuando el usuario realice una acción con el NotificationManager
                PendingIntent mPendingIntent = PendingIntent.getActivity(mContext, 0, mNotificationIntent, 0);
            // Establecemos los parámetros de la notificación
                mNotification.setLatestEventInfo(mContext, mTitulo, mTexto, mPendingIntent);
            // Lanzamos la notificación
                mNotificationManager.notify(NOTIFICATION_ID, mNotification);
            }
        });

        mBtAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Botón Si
                mAlertDialog.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                    }
                });
                // Botón No
                mAlertDialog.setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mAlert.dismiss();
                    }
                });
                mAlertDialog.show();
            }
        });

        mBtProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog.show(MainActivity.this, getResources().getString(R.string.tituloProgressDialog),
                        getResources().getString(R.string.cargaProgress), true, true);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
/** DatePicker */
    public void showDatePickerDialog(View v) {
        android.support.v4.app.DialogFragment newFragment = new DatePickerActivity();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
/** TimePicker */
    public void showTimePickerDialog(View v) {
        android.support.v4.app.DialogFragment newFragment = new TimePickerActivity();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
}
