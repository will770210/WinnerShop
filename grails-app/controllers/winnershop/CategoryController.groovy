package winnershop


import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.JSONObject

class CategoryController extends BaseController{
    def categoryService

    def query = {

        def result = [:]

        try {
            if (request.JSON) {
                JSONObject paramaJSONObject = request.JSON

                if (paramaJSONObject instanceof JSONObject) {
                    def params = [:]
                    params["userId"] = paramaJSONObject.getString("userId")
                    params["queryType"] = paramaJSONObject.getString("queryType")
                    List<Category> categoryList
                    if(params["queryType"].toString().equalsIgnoreCase("user")){
                        result["message"] = "Query cateogry by params:${params}."
                        categoryList = Category.findAllByCreatedUser(params["userId"]);

                    }else {
                        result["message"] = "Query cateogry all."
                        categoryList = Category.findAll();
                    }

                    result["status"] = 1
                    result["categoryList"] = convertFormatService.convertListToMapList(categoryList);

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
            result["message"] = "Query category exception:${e.getMessage()}."
            e.printStackTrace()
        } finally {

            render result as JSON;
        }

    }


    def update={
        def result = [:]
        try {
            if (request.JSON) {
                JSONObject paramaJSONObject = request.JSON

                if (paramaJSONObject instanceof JSONObject) {
                    def params = [:]
                    params["categoryName"] = paramaJSONObject.getString("categoryName");
                    params["categoryId"] = paramaJSONObject.getString("categoryId")
                    params["userId"] = paramaJSONObject.getString("userId")

                    if (params["categoryName"] && params["userId"] && params["categoryId"]) {
                        Category category = categoryService.queryCategory(params)
                        if(category){
                            category = categoryService.updateCategory(category,params,params["userId"])
                            result["status"] = 1
                            result["message"] = "Category update success!!"
                            result["category"] = convertFormatService.convertObjectToMap(category);
                        }else{
                            result["status"] = -1
                            result["message"] = "Can't fine category to update!!"
                        }

                    } else {
                        result["status"] = -1
                        result["message"] = "Params is miss."
                    }
                } else {
                    result["status"] = -1
                    result["message"] = "Params format is incorrect."

                }
            } else {
                result["status"] = -1
                result["message"] = "Params is null."
            }


        } catch (e) {
            result["status"] = -1
            result["message"] = "Update category exception:${e.getMessage()}."
            e.printStackTrace()
        } finally {

            render result as JSON;
        }
    }


    def create = {
        def result = [:]

        try {
            if (request.JSON) {
                JSONObject paramaJSONObject = request.JSON

                if (paramaJSONObject instanceof JSONObject) {
                    def params = [:]
                    params["categoryName"] = paramaJSONObject.getString("categoryName");
                    params["userId"] = paramaJSONObject.getString("userId")

                    if (params["categoryName"] && params["userId"]) {
                        Category category = categoryService.queryCategoryByCategoryName(params["categoryName"],params["userId"])
                        if(category){
                            result["status"] = -1
                            result["message"] = "Category had exist."
                        }else{

                            category = categoryService.createCategory(params,params["userId"])
                            result["status"] = 1
                            result["message"] = "Category create success."
                            result["category"] = convertFormatService.convertObjectToMap(category);

                        }

                    } else {
                        result["status"] = -1
                        result["message"] = "Params is miss."
                    }
                } else {
                    result["status"] = -1
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

            render result as JSON;
        }


    }

    def delete = {
        def result = [:]

        try {
            if (request.JSON) {
                JSONObject paramaJSONObject = request.JSON

                if (paramaJSONObject instanceof JSONObject) {
                    def params = [:]
                    params["categoryId"] = paramaJSONObject.getString("categoryId");
                    params["userId"] = paramaJSONObject.getString("userId")

                    if (params["categoryId"] && params["userId"]) {
                        Category category = categoryService.queryCategory(params)
                        if(category){
                            categoryService.deleteCategory(category)
                            result["status"] = 1
                            result["message"] = "Delete category success."
                            result["category"] = convertFormatService.convertObjectToMap(category);
                        }else{
                            result["status"] = -1
                            result["message"] = "Can't find category to delete."
                        }

                    } else {
                        result["status"] = -1
                        result["message"] = "Params is miss."
                    }
                } else {
                    result["status"] = -1
                    result["message"] = "Params format is incorrect."

                }
            } else {
                result["status"] = -1
                result["message"] = "Params is null."
            }


        } catch (e) {
            result["status"] = -1
            result["message"] = "Delete category exception:${e.getMessage()}."
            e.printStackTrace()
        } finally {

            render result as JSON;
        }


    }
}
