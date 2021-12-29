package cutit.cutit.logic.controller.managepromotions;

import cutit.cutit.logic.bean.HairdresserBean;
import cutit.cutit.logic.bean.ManagePromotionBean;
import cutit.cutit.logic.database.dao.PromotionDAO;
import cutit.cutit.logic.database.dao.ServiceDAO;
import cutit.cutit.logic.model.Promotion;
import cutit.cutit.logic.model.Service;
import cutit.cutit.logic.model.Shop;

import java.time.LocalDateTime;
import java.util.List;

public class ManagePromotionController {

    public Boolean removePromotion(ManagePromotionBean managePromotionBean){
        //dovrò passare la bean, in modo che questa si possa registrare come osservatore del model (e forse anche per prendere i dati in ingresso, oopure li metto da qui ma sempre usando la bean)
        System.out.println("CONTROLLER APPLICATIVO -> Deleting Promotion (data from ManagePromotionBean passed by my viewController)");
        return true;
    }

    public Boolean addPromotion(ManagePromotionBean managePromotionBean) throws Exception {
        /*
        Service service = ServiceDAO.getInstance().getService(managePromotionBean);
        Promotion promotion = new Promotion(managePromotionBean.getPromotionCode(), managePromotionBean.getPromOffValue(), LocalDateTime.now(),service);
        PromotionDAO.insertPromotion(promotion, service);
        System.out.println("CONTROLLER APPLICATIVO -> Adding Promotion (data from ManagePromotionBean passed by my viewController)");

         */
        return true;
    }

    public ManagePromotionBean getAllPromotions(HairdresserBean hairdresserBean) throws Exception {
        Shop shop = new Shop(hairdresserBean.getShopName(), hairdresserBean.getpIVA());
        List<Promotion> promotionsList = PromotionDAO.getAllPromotion(shop);
        ManagePromotionBean managePromotionBean = new ManagePromotionBean();
        managePromotionBean.setPromotionsList(promotionsList);
        return managePromotionBean;
    }

    public ManagePromotionBean getAllServices(HairdresserBean hairdresserBean) throws Exception {
        Shop shop = new Shop(hairdresserBean.getShopName(), hairdresserBean.getpIVA());
        List<Service> serviceList = ServiceDAO.getALlServices(shop);
        ManagePromotionBean managePromotionBean = new ManagePromotionBean();
        managePromotionBean.setServiceList(serviceList);
        return managePromotionBean;
    }

}
