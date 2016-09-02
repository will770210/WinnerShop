package winnershop

import grails.converters.JSON


class ItemController {
    def itemService
    def index={
        render 'item';
    }

//    def add={
//        def result = [:]
//        if(params.name && params.price && params.photo && params.categoryName){
//            Item item = itemService.saveItem(params)
//            result["status"] = 1
//            result["message"] = "Add item success by ${params.toString()}"
//            result["item"] = item
//        }else{
//            result["status"] = -1
//            result["message"] = "Params is error."
//        }
//        render result as JSON;
//    }

    def delete={

    }

    def update={

    }

//    def query={
//        def result = [:]
//
//        def itemList = Item.findAll();
//
//        result["status"] = 1
//        result["message"] = "Query item by ${params.toString()}"
//        result["itemList"] = itemList
//
//        render result as JSON;
//    }
}
