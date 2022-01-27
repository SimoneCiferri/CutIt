package cutit.log;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogWriter {

    private static boolean logIsEnabled = true;
    private static final String LOG = "src/logic/java/cutit/log/LOG.txt";

    private static LogWriter instance = null;

    private LogWriter(){

    }

    public static synchronized LogWriter getInstance(){
        if(LogWriter.instance == null){
            LogWriter.instance = new LogWriter();
        }
        return LogWriter.instance;
    }

    public void writeInLog(String infoLog) {
        BufferedReader rLog = null;
        BufferedWriter wLog = null;
        try {
            File f = new File(LOG);
            if ((f.exists() || f.createNewFile())) {
                rLog = new BufferedReader(new FileReader(LOG));
                String s;
                StringBuilder s1 = new StringBuilder();
                while ((s = rLog.readLine()) != null) {
                    s1.append(s).append("\n");
                }

                wLog = new BufferedWriter(new FileWriter(LOG));
                wLog.write(s1 + "\n" + currentDate() + "\n" + infoLog);

            }
        } catch (IOException e) {
            e.printStackTrace();
            //in qualche modo avviso che non posso scrivere il log dei file.
        } finally {
            try {
                assert rLog != null;
                rLog.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                assert wLog != null;
                wLog.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void writeInLogTWR(String infoLog) {
        if(logIsEnabled){
            try {
                File f = new File(LOG);
                if ((f.exists() || f.createNewFile())) {
                    try(BufferedReader rLog = new BufferedReader(new FileReader(LOG));
                        BufferedWriter wLog = new BufferedWriter(new FileWriter(LOG))){
                        String s;
                        StringBuilder s1 = new StringBuilder();
                        while ((s = rLog.readLine()) != null) {
                            s1.append(s).append("\n");
                        }
                        wLog.write(s1 + "\n" + currentDate() + "\n" + infoLog);
                    } catch (IOException fnf){
                        logIsEnabled = false;
                    }
                }
            } catch (IOException e) {
                logIsEnabled = false;
            }
        }
    }

    private String currentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

}
