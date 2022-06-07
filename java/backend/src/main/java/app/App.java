package app;


import static utils.CustomLogger.writeToLog;

public class App {
    public static void main(String[] args) {
        writeToLog("started........");
        try {
            for (int i=0;i<5;i++){
                int d = 10/i;
            }
        }catch (Exception e) {
            e.printStackTrace();
            writeToLog(e.toString());
        }

    }
}
