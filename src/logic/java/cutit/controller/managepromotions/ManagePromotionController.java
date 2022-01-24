package cutit.controller.managepromotions;

import cutit.bean.ManagePromotionBeanInterface;
import cutit.bean.ManagePromotionBean;
import cutit.bean.ShopBeanInterface;
import cutit.database.dao.PromotionDAO;
import cutit.database.dao.ServiceDAO;
import cutit.exception.DuplicatedRecordException;
import cutit.exception.WrongInputDataException;
import cutit.log.LogWriter;
import cutit.model.Promotion;
import cutit.model.Service;
import cutit.model.Shop;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ManagePromotionController {

    public void removePromotion(ManagePromotionBeanInterface managePromotionBean) throws Exception {
        try{
            Promotion promotion = new Promotion(managePromotionBean.getPromotionCode(), managePromotionBean.getPromOffValue(), managePromotionBean.getPromExpireDate());
            PromotionDAO.deletePromotion(promotion);
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }
    }

    public void addPromotion(ManagePromotionBeanInterface managePromotionBean) throws Exception {
        try{
            if(managePromotionBean.getPromExpireDate().isAfter(LocalDate.now())){
                Promotion promotion = new Promotion(managePromotionBean.getPromotionCode(), managePromotionBean.getPromOffValue(), managePromotionBean.getPromExpireDate());
                PromotionDAO.insertPromotion(promotion, managePromotionBean.getPromServiceName(), managePromotionBean.getPromShopName());
            }else{
                throw new WrongInputDataException("Expiry date can't be a past day.");
            }
        } catch (DuplicatedRecordException | WrongInputDataException exception){
            throw exception;
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }
    }

    public void getAllPromotions(ManagePromotionBeanInterface managePromotionBean, ShopBeanInterface shopBean) throws Exception {
        try {
            Shop shop = new Shop(shopBean.getShopName(), shopBean.getShopPIVA());
            List<Promotion> promotionsList = PromotionDAO.getAllPromotion(shop.getShopName());
            managePromotionBean.setPromotionsBeanList(promBeanListFromPromList(promotionsList));
        } catch (Exception e){
            LogWriter.getInstance().writeInLog(this.getClass().toString() + "\n " + e.getMessage());
            throw e;
        }

    }


    public ManagePromotionBean getAllServices(ShopBeanInterface shopBean) throws Exception {
        try {
            Shop shop = new Shop(shopBean.getShopName(), shopBean.getShopPIVA());
            List<Service> serviceList = ServiceDAO.getAllServices(shop.getShopName());
            ManagePromotionBean managePromotionBean = new ManagePromotionBean();
            managePromotionBean.setServiceList(stringListFromServList(serviceList));
            return managePromotionBean;
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

    private List<ManagePromotionBeanInterface> promBeanListFromPromList(List<Promotion> promotionsList) {
        List<ManagePromotionBeanInterface> promList = new ArrayList<>();
        if(!promotionsList.isEmpty()){
            for(int i = 0; i<promotionsList.size(); i++){
                String promCOde = promotionsList.get(i).getCode();
                Integer offVal = promotionsList.get(i).getOffValue();
                String promService = promotionsList.get(i).getService().getServiceName();
                LocalDate expire = promotionsList.get(i).getExpireDate();
                ManagePromotionBean promotionBean = new ManagePromotionBean();
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
