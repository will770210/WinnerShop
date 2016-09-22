package winnershop

import grails.transaction.Transactional
import helper.WhoColumnsValueGenerator

@Transactional
class ColorService extends BaseService{

    def createColor(def params, def userId) {

        Color color = new Color()
        color.colorName = params.colorName
        color.colorCode = params.colorCode
        save(color)

        return color;

    }



    def queryColorByColorName(String colorName){
        queryColorByColorName(colorName,null)
    }

    def queryColorByColorName(String colorName,String userId){

        Color color = Color.findByColorName(colorName)
        return color
    }


    def queryColor(def params){
        queryColor(params,null)
    }

    def queryColor(def params,def userId){
        Color color = Color.findById(params.colorId)

        return color
    }

    def deleteColor(color) {
        delete(color)
        return color;

    }

    def updateColor(Color color,def params, def userId){

        if(color){
            color.colorName = params.colorName
            color.colorCode = params.colorCode
            save(color)
        }


        return color;
    }
}
