package winnershop

import grails.transaction.Transactional
import helper.WhoColumnsValueGenerator as WG
@Transactional
class CategoryService extends BaseService{

    def createCategory(def params, def userId) {

        Category category = new Category()
        category.categoryName = params.categoryName
        category.properties = WG.generateForCreation(Integer.parseInt(userId))
        save(category)

        return category;

    }

    def queryCategory(def params){
        queryCategory(params,null)
    }

    def queryCategoryByCategoryName(String categoryName){
        queryCategoryByCategoryName(categoryName,null)
    }

    def queryCategoryByCategoryName(String categoryName,String userId){
        Category category
        if(userId){
            category = Category.findByCategoryNameAndCreatedUser(categoryName,userId)
        }else{
            category = Category.findByCategoryName(categoryName)
        }
        return category
    }


    def queryCategory(def params,def userId){
        Category category
        if(userId){
            category = Category.findByIdAndCreatedUser(params.categoryId,userId)
        }else{
            category = Category.findById(params.categoryId)
        }
        return category
    }

    def deleteCategory(category) {
        delete(category)
        return category;

    }

    def updateCategory(Category category,def params, def userId){


        if(category){
            category.categoryName = params.categoryName
            category.properties = WG.generateForUpdate(Integer.parseInt(params.userId))
            save(category)
        }


        return category;
    }
}
