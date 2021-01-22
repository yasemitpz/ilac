package randevu;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

    private static final String pattern = "dd-MM-yyyy";
    private static final String patternHour = "dd-MM-yyyy HH:mm";

    public static String toString(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String formattedString = date.format(formatter);
        return formattedString;
    }

    public static String toString(Timestamp date){
        return toString(date.toLocalDateTime().toLocalDate());
    }

    public static int getSaat(Timestamp date ){
        return date.getHours();
    }

    public static Timestamp toTimestamp(String zaman){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(patternHour);
            Date parsedDate = dateFormat.parse(zaman);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            return timestamp;
        } catch(Exception e) {
        }
        return null;
    }
}
