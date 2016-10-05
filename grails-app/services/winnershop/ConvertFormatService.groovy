package winnershop

import domain.BaseEntity
import grails.transaction.Transactional

import java.text.SimpleDateFormat

@Transactional
class ConvertFormatService {

    SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    def convertObjectToMap(def object) {
        convertObjectToMap(object,true)
    }

    def convertObjectToMap(def object, def isNeedWhoColumn) {
        def objectMap = [:]
        object.properties.each { p ->
            if (!((isWhoColumn(p.key) && !isNeedWhoColumn) || isForeignKey(p.key))) {
                if (isInstanceOfDomainClass(p.value)) {
                    objectMap[p.key] = convertObjectToMap(p.value, false)
                } else if (p.value instanceof Date) {
                    objectMap[p.key] = sdFormat.format(p.value)
                } else {
                    objectMap[p.key] = p.value
                }
            }
        }

        objectMap["${object.getClass().getSimpleName().toLowerCase()}Id"]=object.id
        return objectMap.sort { a, b -> a.key <=> b.key }
    }


    def convertListToMapList(def objectList) {
        def mapList = []
        objectList.each { object ->
            mapList.add(convertObjectToMap(object))
        }
        return mapList
    }

    def isInstanceOfDomainClass(def object){
        if(object instanceof Category || object instanceof Photo || object instanceof Color){
            return true;
        }
        return false;
    }

    def isForeignKey(def name) {
        if (name in ["categoryId", "colorId", "discountId", "photoId"]) {
            return true
        } else {
            return false
        }
    }


    def isWhoColumn(def name) {
        if (name in ["createdUser", "createdDate", "modifiedUser", "modifiedDate"]) {
            return true
        } else {
            return false
        }
    }
}
