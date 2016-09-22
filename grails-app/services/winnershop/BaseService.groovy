package winnershop


import grails.transaction.Transactional

import java.text.SimpleDateFormat

@Transactional
class BaseService {


    def save(Object obj){

        if(obj.validate()){
            obj.save();
        }else {

        }

    }

    def delete(Object obj){
        obj.delete();
    }

    def convertObjectToMap(Object object){
        def objectMap = [:]
        object.properties.each {p->
            if(p.value instanceof Date){
                objectMap[p.key] = sdFormat.format(p.value)
            }else{
                objectMap[p.key] = p.value
            }
        }
        return objectMap
    }
}
