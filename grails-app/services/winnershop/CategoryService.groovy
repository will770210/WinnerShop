package winnershop

import grails.transaction.Transactional
import helper.WhoColumnsValueGenerator as WG
@Transactional
class CategoryService extends BaseService{

    def create(def params) {

        Category category = new Category()
        category.categoryName = params.categoryName
        category.properties = WG.generateForCreation((int)params.userId)
        save(category)

        return category;

    }
}
