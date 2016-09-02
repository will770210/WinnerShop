package winnershop

import grails.transaction.Transactional

@Transactional
class ItemService extends BaseService{

//    Item saveItem(def params){
//        Item item = new Item();
//        item.name = params.name
//        item.categoryName = params.categoryName
//        item.price = Double.parseDouble(params.price.toString());
//        item.photo = params.photo
//
//        save(item);
//
//        return item;
//
//    }
}
