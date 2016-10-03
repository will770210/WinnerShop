package winnershop

import grails.transaction.Transactional
import helper.WhoColumnsValueGenerator

@Transactional
class DiscountService extends BaseService{

    def createDiscount(def params, def userId) {

        Discount discount = new Discount()
        discount.discountTitle = params.discountTitle
        discount.discountType = Integer.parseInt(params.discountType)
        discount.discountValue = Double.parseDouble(params.discountValue)
        discount.properties = WhoColumnsValueGenerator.generateForCreation(Integer.parseInt(userId))
        save(discount)

        return discount;

    }

    def queryDiscount(def params){
        queryDiscount(params,null)
    }

    def queryDiscountByDiscountTitle(String discountTitle){
        queryDiscountByDiscountTitle(discountTitle,null)
    }

    def queryDiscountByDiscountTitle(String discountTitle,String userId){
        Discount discount
        if(userId){
            discount = Discount.findByDiscountTitleAndCreatedUser(discountTitle,userId)
        }else{
            discount = Discount.findByDiscountTitle(discountTitle)
        }
        return discount
    }


    def queryDiscount(def params,def userId){
        Discount discount
        if(userId){
            discount = Discount.findByIdAndCreatedUser(params.discountId,userId)
        }else{
            discount = Discount.findById(params.discountId)
        }
        return discount
    }

    def deleteDiscount(discount) {
        delete(discount)
        return discount;

    }

    def updateDiscount(Discount discount,def params, def userId){


        if(discount){
            discount.discountTitle = params.discountTitle
            discount.discountType = Integer.parseInt(params.discountType)
            discount.discountValue = Double.parseDouble(params.discountValue)
            discount.properties = WhoColumnsValueGenerator.generateForUpdate(Integer.parseInt(params.userId))
            save(discount)
        }


        return discount;
    }
}
