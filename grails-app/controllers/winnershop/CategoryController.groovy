package winnershop


import grails.converters.JSON
import grails.rest.RestfulController
import org.codehaus.groovy.grails.web.json.JSONObject

class CategoryController{
    def categoryService
    static responseFormats = ['json']


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


    def update={
        def result = [:]
        Category category
        try {
            if (request.JSON) {
                JSONObject paramaJSONObject = request.JSON

                if (paramaJSONObject instanceof JSONObject) {
                    def params = [:]
                    params["categoryName"] = paramaJSONObject.getString("categoryName");
                    params["categoryId"] = paramaJSONObject.getString("categoryId")
                    params["userId"] = paramaJSONObject.getString("userId")

                    if (params["categoryName"] && params["userId"] && params["categoryId"]) {
                        category = categoryService.queryCategory(params)
                        if(category){
                            category = categoryService.updateCategory(category,params,params["userId"])
                            result["status"] = 1
                            result["message"] = "Category update success!!"
                        }else{
                            result["status"] = -1
                            result["message"] = "Can't fine category to update!!"
                        }

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
            result["category"] = category;
            render result as JSON;
        }
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
                        category = categoryService.queryCategoryByCategoryName(params["categoryName"],params["userId"])
                        if(category){
                            result["status"] = -1
                            result["message"] = "Ctegory had exist."
                        }else{
                            result["status"] = 1
                            result["message"] = "Ctegory create success."
                            category = categoryService.createCategory(params,params["userId"])

                        }

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
            result["category"] = category;
            render result as JSON;
        }


    }

    def delete = {
        def result = [:]
        Category category
        try {
            if (request.JSON) {
                JSONObject paramaJSONObject = request.JSON

                if (paramaJSONObject instanceof JSONObject) {
                    def params = [:]
                    params["categoryId"] = paramaJSONObject.getString("categoryId");
                    params["userId"] = paramaJSONObject.getString("userId")

                    if (params["categoryId"] && params["userId"]) {
                        category = categoryService.queryCategory(params)
                        if(category){
                            categoryService.deleteCategory(category)
                            result["status"] = 1
                            result["message"] = "Delete category success."
                        }else{
                            result["status"] = -1
                            result["message"] = "Can't find category to delete."
                        }

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
            result["category"] = category;
            render result as JSON;
        }


    }
}
