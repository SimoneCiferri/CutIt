package cutit.utils;

import java.util.List;
import java.util.Map;

public class MyStringBuilder {

    private MyStringBuilder(){}

    public static String getStringFromOpenDaysMap(Map<Integer,Boolean> opendays){
        StringBuilder openTimeString = new StringBuilder();
        for(int i=1;i<opendays.size()+1;i++){
            if(Boolean.TRUE.equals(opendays.get(i))){
                if(i == 1){
                    openTimeString.append("Monday");
                } else if(i == 2){
                    openTimeString.append("Tuesday");
                } else if(i == 3){
                    openTimeString.append("Wednesday");
                } else if(i == 4){
                    openTimeString.append("Thursday");
                } else if(i == 5){
                    openTimeString.append("Friday");
                } else if(i == 6){
                    openTimeString.append("Saturday");
                } else{
                    openTimeString.append("Sunday");
                }
                openTimeString.append("\n");
            }
        }
        return openTimeString.toString();
    }

    public  static String getStringFromServicesStringList(List<String> allServices){
        StringBuilder servicesLabel = new StringBuilder();
        for (String allService : allServices) {
            servicesLabel.append(allService).append("\n");
        }
        return servicesLabel.toString();
    }

}
