package winnershop

import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.JSONObject

class CategoryController {
    def categoryService

    def query = {

        def result = [:]
        List<Category> categoryList
        try {
            if (request.JSON) {
                JSONObject paramaJSONObject = request.JSON

                if (paramaJSONObject instanceof JSONObject) {
                    def params = [:]
                    params["userId"] = paramaJSONObject.getString("userId")
                    params["queryType"] = paramaJSONObject.getString("queryType")

                    if(params["queryType"].toString().equalsIgnoreCase("user")){
                        result["message"] = "Query cateogry by params:${params}."
                        categoryList = Category.findAllByCreatedUser(params["userId"]);

                    }else {
                        result["message"] = "Query cateogry all."
                        categoryList = Category.findAll();
                    }

                    result["status"] = 1




                } else {

                    result["status"] = -1
                    result["message"] = "Params format is incorrect."
                    result["categoryList"] = []
                }
            } else {
                result["status"] = -1
                result["message"] = "Params is nulls."
                result["categoryList"] = []
            }


        } catch (e) {
            result["status"] = -1
            result["message"] = "Create category exception:${e.getMessage()}."
            e.printStackTrace()
        } finally {
            result["categoryList"] = categoryList;
            render result as JSON;
        }





        render result as JSON;

    }

    def create = {
        def result = [:]
        Category category
        try {
            if (request.JSON) {
                JSONObject paramaJSONObject = request.JSON

                if (paramaJSONObject instanceof JSONObject) {
                    def params = [:]
                    params["categoryName"] = paramaJSONObject.getString("categoryName");
                    params["userId"] = paramaJSONObject.getString("userId")

                    if (params["categoryName"] && params["userId"]) {
                        category = categoryService.create(params)
                    }
                } else {
                    result["status"] = 1
                    result["message"] = "Params format is incorrect."

                }
            } else {
                result["status"] = -1
                result["message"] = "Params is null."
            }


        } catch (e) {
            result["status"] = -1
            result["message"] = "Create category exception:${e.getMessage()}."
            e.printStackTrace()
        } finally {
            result["category"] = category;
            render result as JSON;
        }


    }
}
