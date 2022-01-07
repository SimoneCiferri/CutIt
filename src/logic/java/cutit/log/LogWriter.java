package cutit.log;

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
    BufferedReader rLog = null;
    BufferedWriter wLog = null;
    try {
        File f = new File("src/main/java/cutit/cutit/logic/log/LOG.txt");
        if((f.exists() || f.createNewFile()) /*&& f.canRead() && f.canWrite()*/ ){
            rLog = new BufferedReader(new FileReader("src/main/java/cutit/cutit/logic/log/LOG.txt"));
            String s;
            StringBuilder s1 = new StringBuilder();
            while ((s = rLog.readLine()) != null) {
                s1.append(s).append("\n");
            }

            wLog = new BufferedWriter(new FileWriter("src/main/java/cutit/cutit/logic/log/LOG.txt"));
            wLog.write( s1 + "\n" + currentDate() + "\n" + infoLog);

        }
    } catch (IOException e) {
        e.printStackTrace();
        //in qualche modo avviso che non posso scrivere il log dei file.
    } finally{
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

    private String currentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

}
