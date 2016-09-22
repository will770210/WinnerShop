package winnershop

import grails.transaction.Transactional
import helper.WhoColumnsValueGenerator

@Transactional
class ItemService extends BaseService{

    def createItem(def params, def userId) {

        Item item = new Item()
        item.itemName = params.itemName
        item.itemPrice = params.itemPrice
        item.category = Category.findByIdAndCreatedUser(params.categoryId,params.userId)
        item.color = Color.findById(params.colorId)
        item.photo = Photo.findByIdAndCreatedUser(params.photoId,params.userId)
        item.properties = WhoColumnsValueGenerator.generateForCreation(Integer.parseInt(userId))
        save(item)

        return item;

    }

    def queryItemByItemName(String itemName) {
        queryItemByItemName(itemName, null)
    }

    def queryItemByItemName(String itemName, String userId) {
        Item item
        if (userId) {
            item = Item.findByItemNameAndCreatedUser(itemName, userId)
        } else {
            item = Item.findByItemName(itemName)
        }
        return item
    }

    def queryItem(def params) {
        queryItem(params, null)
    }


    def queryItem(def params, def userId) {
        Item item
        if (userId) {
            item = Item.findByIdAndCreatedUser(params.itemId, userId)
        } else {
            item = Item.findById(params.itemId)
        }
        return item
    }

    def deleteItem(item) {
        delete(item)
        return item;

    }

    def updateItem(Item item, def params, def userId) {
        if (item) {
            item.itemName = params.itemName
            item.itemName = params.itemName
            item.itemPrice = params.itemPrice
            item.category = Category.findByIdAndCreatedUser(params.categoryId,params.userId)
            item.color = Color.findById(params.colorId)
            item.photo = Photo.findByIdAndCreatedUser(params.photoId,params.userId)
            item.properties = WhoColumnsValueGenerator.generateForUpdate(Integer.parseInt(userId))
            save(item)
        }

        return item;
    }
}
