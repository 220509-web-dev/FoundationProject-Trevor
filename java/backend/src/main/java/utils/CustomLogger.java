package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomLogger {

    public static void writeToLog(String msg) {

        LocalDate today = LocalDate.now();
        String formattedDate = today.format(DateTimeFormatter.ofPattern("dd-MM-yy"));
        String filename = "src/main/java/utils/logger/" + "logger_" + formattedDate + ".txt";
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
            LocalDateTime currentDateTime = LocalDateTime.now();
            String format = currentDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yy HH:mm:ss"));

            writer.append(format +" : ");
            writer.append(msg +"\n");
            writer.close();
        }catch (IOException e) {
                e.printStackTrace();
                System.out.println("Exception writing logs " + e.getMessage());
        }
    }

}
