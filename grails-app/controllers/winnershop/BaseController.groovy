package winnershop

import java.text.SimpleDateFormat

class BaseController {

    SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    def convertObjectToMap(def object) {
        def objectMap = [:]
        log.info(object.properties.toString())
        object.properties.each { p ->
            if(isForeignKey(p.key)){

            } else if (isInstanceOfDomainClass(p.value)) {
                objectMap[p.key] = convertObjectToMap(p.value)
            } else if (p.value instanceof Date) {
                objectMap[p.key] = sdFormat.format(p.value)
            } else {
                objectMap[p.key] = p.value
            }
        }
        objectMap["id"]=object.id
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

    def isForeignKey(def name){
        if (name in ["categoryId","colorId","discountId","photoId"]){
            return true
        }else{
            return false
        }
    }
}
