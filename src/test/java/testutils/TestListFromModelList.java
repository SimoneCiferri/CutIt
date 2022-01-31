package testutils;

import cutit.model.Promotion;
import cutit.model.Service;
import cutit.utils.ListFromModelList;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestListFromModelList {

    //Test case Caporro
    @Test
    public void testGetStringListFromServices(){
        List<Service> serviceList = new ArrayList<>();
        Service service1 = new Service("Taglio", (float)15, "Da Matteo");
        serviceList.add(service1);
        Service service2 = new Service("Barba", (float)7, "Da Matteo");
        serviceList.add(service2);
        List<String> output = ListFromModelList.getStringListFromServices(serviceList);

        List<String> correctList = new ArrayList<>();
        correctList.add("Taglio");
        correctList.add("Barba");

        boolean test = output.equals(correctList);
        assertTrue(test);
    }

    //Test case Caporro
    @Test
    public void testGetStringListFromPromotions(){
        List<Promotion> promotionsList = new ArrayList<>();
        Promotion promotion1 = new Promotion("PromotionCode1", 5, LocalDate.now().plusMonths(1));
        promotionsList.add(promotion1);
        Promotion promotion2 = new Promotion("PromotionCode2", 10, LocalDate.now().plusMonths(1));
        promotionsList.add(promotion2);
        List<String> output = ListFromModelList.getStringListFromPromotions(promotionsList);

        List<String> correctList = new ArrayList<>();
        correctList.add("PromotionCode1");
        correctList.add("PromotionCode2");

        boolean test = output.equals(correctList);
        assertTrue(test);
    }



}
