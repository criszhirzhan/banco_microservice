package com.banco.banco_clientes.application.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

    private static TimeZone timeZone = TimeZone.getTimeZone("GTM");
    private static final ZoneId ZONE_ID_ECUADOR = ZoneId.of("America/Guayaquil");

    public static Timestamp getCurrentTimestamp() {
        Instant now = Instant.now().atZone(ZONE_ID_ECUADOR).toInstant();
        return Timestamp.from(now);
    }

    public static Date convertirAFechaInicio(String fechaInicioString) {
        LocalDate fechaInicioConverted = LocalDate.parse(fechaInicioString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return Date.from(fechaInicioConverted.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date convertirAFechaFin(String fechaFinString) {
        LocalDate fechaFinConverted = LocalDate.parse(fechaFinString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDateTime finalDateTime = fechaFinConverted.atTime(23, 59, 59, 999999999);
        return Date.from(finalDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String convertirFechaAFormato(Date fecha) {
        if (fecha == null) {
            return null;
        }
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(fecha);
    }
}
