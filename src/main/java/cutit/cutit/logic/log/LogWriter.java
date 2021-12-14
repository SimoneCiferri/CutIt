package cutit.cutit.logic.log;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogWriter {

    private static LogWriter instance = null;

    private LogWriter(){

    }

    public static synchronized LogWriter getInstance(){
        if(LogWriter.instance == null){
            LogWriter.instance = new LogWriter();
        }
        return LogWriter.instance;
    }

public void writeInLog(String infoLog){
    try {
        File f = new File("LOG.txt");
        if((f.exists() || f.createNewFile()) && f.canRead() && f.canWrite() ){
            BufferedReader rLog = new BufferedReader(new FileReader("LOG.txt"));
            String s, s1 = "";
            while ((s = rLog.readLine()) != null) {
                s1 += s + "\n";
            }
            rLog.close();
            BufferedWriter wLog = new BufferedWriter(new FileWriter("LOG.txt"));
            wLog.write( s1 + "\n" + currentDate() + "\n" + infoLog);
            wLog.close();
        }
    } catch (IOException e) {
        e.printStackTrace();
        //in qualche modo avviso che non posso scrivere il log dei file.
    }
}

    private String currentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

}
