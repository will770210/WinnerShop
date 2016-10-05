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

}
