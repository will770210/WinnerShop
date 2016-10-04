package winnershop

class TestController {

    def index={

        def categoryList = Item.findAll();
        log.info(categoryList.size())
        categoryList.each{
            render it.properties
        }

        //render log.info(it.properties)

    }
}
