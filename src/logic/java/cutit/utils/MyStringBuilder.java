package cutit.utils;

import java.util.List;
import java.util.Map;

public class MyStringBuilder {

    private MyStringBuilder(){}

    public static String getStringFromOpenDaysMap(Map<Integer,Boolean> opendays){
        StringBuilder opentTimeString = new StringBuilder();
        for(int i=1;i<opendays.size()+1;i++){
            if(Boolean.TRUE.equals(opendays.get(i))){
                switch (i) {
                    case 1 -> {
                        opentTimeString.append("Monday").append("\n");
                    }
                    case 2 -> {
                        opentTimeString.append("Tuesday").append("\n");
                    }
                    case 3 -> {
                        opentTimeString.append("Wednesday").append("\n");
                    }
                    case 4 -> {
                        opentTimeString.append("Thursday").append("\n");
                    }
                    case 5 -> {
                        opentTimeString.append("Friday").append("\n");
                    }
                    case 6 -> {
                        opentTimeString.append("Saturday").append("\n");
                    }
                    case 7 -> {
                        opentTimeString.append("Sunday").append("\n");
                    }
                }
            }
        }
        return opentTimeString.toString();
    }

    public  static String getStringFromServicesStringList(List<String> allServices){
        StringBuilder servicesLabel = new StringBuilder();
        for (String allService : allServices) {
            servicesLabel.append(allService).append("\n");
        }
        return servicesLabel.toString();
    }

}
