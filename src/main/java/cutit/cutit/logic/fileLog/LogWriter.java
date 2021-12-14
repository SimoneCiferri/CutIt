package cutit.cutit.logic.fileLog;


import java.io.*;

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

public Boolean writeInLog(String infoLog){

        try{
        BufferedReader rLog = new BufferedReader(new FileReader("cutit/cutit/logic/fileLog/LOG"));
        BufferedWriter wLog = new BufferedWriter(new FileWriter("cutit/cutit/logic/fileLog/LOG"));
        String s = "";
        String s1 = "";
        while ((s = rLog.readLine()) != null){

            s1 += s + "\n";
        }
        rLog.close();
        wLog.write(s1 + "\n" + infoLog);
        wLog.close();

        return  true;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            File LOG = new File("cutit/cutit/logic/fileLog/");

        } catch (IOException e) {
            e.printStackTrace();
        }


    return false;
}

}
