package cutit.controller.managepromotions;

import cutit.bean.ManagePromotionBean;
import cutit.bean.ShopBean;
import cutit.database.dao.PromotionDAO;
import cutit.database.dao.ServiceDAO;
import cutit.model.Promotion;
import cutit.model.Service;
import cutit.model.Shop;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ManagePromotionController {

    public Boolean removePromotion(ManagePromotionBean managePromotionBean) throws Exception {
        Promotion promotion = new Promotion(managePromotionBean.getPromotionCode(), managePromotionBean.getPromOffValue(), managePromotionBean.getPromExpireDate());
        PromotionDAO.deletePromotion(promotion);
        System.out.println("CONTROLLER APPLICATIVO -> Deleting Promotion (data from ManagePromotionBean passed by my viewController)");
        return true;
    }

    public Boolean addPromotion(ManagePromotionBean managePromotionBean) throws Exception {
        Promotion promotion = new Promotion(managePromotionBean.getPromotionCode(), managePromotionBean.getPromOffValue(), managePromotionBean.getPromExpireDate());
        PromotionDAO.insertPromotion(promotion, managePromotionBean.getPromServiceName(), managePromotionBean.getPromShopName());
        System.out.println("CONTROLLER APPLICATIVO -> Adding Promotion (data from ManagePromotionBean passed by my viewController)");
        return true;
    }

    public ManagePromotionBean getAllPromotions(ShopBean shopBean) throws Exception {
        Shop shop = new Shop(shopBean.getShopName(), shopBean.getShopPIVA());
        List<Promotion> promotionsList = PromotionDAO.getAllPromotion(shop);
        ManagePromotionBean managePromotionBean = new ManagePromotionBean();
        managePromotionBean.setPromotionsList(promDataListFromPromList(promotionsList));
        return managePromotionBean;
    }

    private List<ManagePromotionBean.PromData> promDataListFromPromList(List<Promotion> promotionsList) {
        List<ManagePromotionBean.PromData> promList = new ArrayList<>();
        if(!promotionsList.isEmpty()){
            for(int i = 0; i<promotionsList.size(); i++){
                String promCOde = promotionsList.get(i).getCode();
                Integer offVal = promotionsList.get(i).getOffValue();
                String promService = promotionsList.get(i).getService().getServiceName();
                LocalDate expire = promotionsList.get(i).getExpireDate();
                ManagePromotionBean.PromData promData = new ManagePromotionBean.PromData();
                promData.setServiceCode(promCOde);
                promData.setOffV(offVal);
                promData.setServiceName(promService);
                promData.setExpire(expire);
                promList.add(promData);
            }
        }
        return promList;
    }

    public ManagePromotionBean getAllServices(ShopBean shopBean) throws Exception {
        Shop shop = new Shop(shopBean.getShopName(), shopBean.getShopPIVA());
        List<Service> serviceList = ServiceDAO.getALlServices(shop);
        ManagePromotionBean managePromotionBean = new ManagePromotionBean();
        managePromotionBean.setServiceList(stringListFromServList(serviceList));
        return managePromotionBean;
    }

    private List<String> stringListFromServList(List<Service> services) {
        List<String> servList = new ArrayList<>();
        if(!services.isEmpty()){
            for(int i = 0; i<services.size(); i++){
                String p = services.get(i).getServiceName();
                servList.add(p);
            }
        }
        return servList;
    }

}
