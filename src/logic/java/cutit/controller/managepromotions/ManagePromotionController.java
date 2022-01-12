package cutit.controller.managepromotions;

import cutit.bean.ManagePromotionBean;
import cutit.bean.firstui.ManagePromotionBeanUQ;
import cutit.bean.ShopBean;
import cutit.database.dao.PromotionDAO;
import cutit.database.dao.ServiceDAO;
import cutit.exception.DuplicatedRecordException;
import cutit.log.LogWriter;
import cutit.model.Promotion;
import cutit.model.Service;
import cutit.model.Shop;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ManagePromotionController {

    public void removePromotion(ManagePromotionBean managePromotionBean) throws Exception {
        try{
            Promotion promotion = new Promotion(managePromotionBean.getPromotionCode(), managePromotionBean.getPromOffValue(), managePromotionBean.getPromExpireDate());
            PromotionDAO.deletePromotion(promotion);
            System.out.println("CONTROLLER APPLICATIVO -> Deleting Promotion (data from ManagePromotionBean passed by my viewController)");
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }
    }

    public void addPromotion(ManagePromotionBean managePromotionBean) throws Exception {
        try{
            Promotion promotion = new Promotion(managePromotionBean.getPromotionCode(), managePromotionBean.getPromOffValue(), managePromotionBean.getPromExpireDate());
            PromotionDAO.insertPromotion(promotion, managePromotionBean.getPromServiceName(), managePromotionBean.getPromShopName());
            System.out.println("CONTROLLER APPLICATIVO -> Adding Promotion (data from ManagePromotionBean passed by my viewController)");
        } catch (DuplicatedRecordException de){
            throw de;
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }
    }

    public void getAllPromotions(ManagePromotionBean managePromotionBean, ShopBean shopBean) throws Exception {
        try {
            Shop shop = new Shop(shopBean.getShopName(), shopBean.getShopPIVA());
            List<Promotion> promotionsList = PromotionDAO.getAllPromotion(shop);
            managePromotionBean.setPromotionsBeanList(promBeanListFromPromList(promotionsList));
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }

    }


    public ManagePromotionBeanUQ getAllServices(ShopBean shopBean) throws Exception {
        try {
            Shop shop = new Shop(shopBean.getShopName(), shopBean.getShopPIVA());
            List<Service> serviceList = ServiceDAO.getALlServices(shop);
            ManagePromotionBeanUQ managePromotionBeanUQ = new ManagePromotionBeanUQ();
            managePromotionBeanUQ.setServiceList(stringListFromServList(serviceList));
            return managePromotionBeanUQ;
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }

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

    private List<ManagePromotionBean> promBeanListFromPromList(List<Promotion> promotionsList) {
        List<ManagePromotionBean> promList = new ArrayList<>();
        if(!promotionsList.isEmpty()){
            for(int i = 0; i<promotionsList.size(); i++){
                String promCOde = promotionsList.get(i).getCode();
                Integer offVal = promotionsList.get(i).getOffValue();
                String promService = promotionsList.get(i).getService().getServiceName();
                LocalDate expire = promotionsList.get(i).getExpireDate();
                ManagePromotionBeanUQ promotionBean = new ManagePromotionBeanUQ();
                promotionBean.setPromotionCode(promCOde);
                promotionBean.setPromOffValue(offVal);
                promotionBean.setPromServiceName(promService);
                promotionBean.setPromExpireDate(expire);
                promList.add(promotionBean);
            }
        }
        return promList;
    }


}
