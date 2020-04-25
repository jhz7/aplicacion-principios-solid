package com.ceiba.solid.dominio.servicio;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ServicioFecha {

    public static Calendar obtener() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);

        return c;
    }

    public static int calcularDiferencia(Date fechaInicial, Date fechaFinal) {
        Calendar a = obtener(fechaInicial);
        Calendar b = obtener(fechaFinal);
        int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
        if (a.get(Calendar.DAY_OF_YEAR) > b.get(Calendar.DAY_OF_YEAR)) {
            diff--;
        }
        return diff;
    }

    private static Calendar obtener(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }
}
