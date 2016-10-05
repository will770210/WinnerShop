package winnershop

class TestController {

    def index={

        def categoryList = Item.findAll();
        log.info(categoryList.size())
        categoryList.each{
            it.getClass().getDeclaredFields().each{
                render it.getName() + " " + it.get(it)
            }
        }

        //render log.info(it.properties)

    }
}
