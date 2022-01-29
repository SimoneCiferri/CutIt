package cutit.log;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogWriter {

    private boolean logIsEnabled = true;
    private static final String LOG = "src/logic/java/cutit/log/LOG.txt";
    private static final String PEPPER = "src/logic/java/cutit/pepper/PEPPER.txt";

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
        if(logIsEnabled){
            try {
                File f = new File(LOG);
                if ((f.exists() || f.createNewFile())) {
                    String oldLog = copy(LOG);
                    paste(oldLog, infoLog, LOG);
                }
            } catch (IOException e) {
                logIsEnabled = false;
            }
        }
    }

    public void pepperAction(String action){
            try {
                File f = new File(PEPPER);
                if ((f.exists() || f.createNewFile())) {
                    String oldLog = copy(PEPPER);
                    paste(oldLog, action, PEPPER);
                }
            } catch (IOException e) {
                logIsEnabled = false;
            }
    }

    private String copy(String path){
        try(BufferedReader rLog = new BufferedReader(new FileReader(path))){
            String s;
            StringBuilder s1 = new StringBuilder();
            while ((s = rLog.readLine()) != null) {
                s1.append(s).append("\n");
            }
            return s1.toString();
        } catch (IOException fnf){
            logIsEnabled = false;
            return "";
        }
    }

    private void paste(String oldLog, String infoLog, String path){
        try(BufferedWriter wLog = new BufferedWriter(new FileWriter(path))){
            wLog.write(oldLog + "\n" + currentDate() + "\n" + infoLog);
        } catch (IOException fnf){
            logIsEnabled = false;
        }
    }

    private String currentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

}
