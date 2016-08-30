package winnershop


import grails.transaction.Transactional

@Transactional
class BaseService {

    def save(Object obj){

        if(obj.validate()){
            obj.save();
        }else {

        }

    }
}
