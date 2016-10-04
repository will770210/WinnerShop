package winnershop

import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.JSONObject

class DiscountController extends BaseController{

    def discountService


    def query = {

        def result = [:]

        try {
            if (request.JSON) {
                JSONObject paramaJSONObject = request.JSON

                if (paramaJSONObject instanceof JSONObject) {
                    def params = [:]
                    params["userId"] = paramaJSONObject.getString("userId")
                    params["queryType"] = paramaJSONObject.getString("queryType")
                    List<Discount> discountList
                    if(params["queryType"].toString().equalsIgnoreCase("user")){
                        result["message"] = "Query discount by params:${params}."
                        discountList = Discount.findAllByCreatedUser(params["userId"]);

                    }else {
                        result["message"] = "Query discount all."
                        discountList = Discount.findAll();
                    }

                    result["status"] = 1
                    result["discountList"] = convertListToMapList(discountList);

                } else {

                    result["status"] = -1
                    result["message"] = "Params format is incorrect."
                }
            } else {
                result["status"] = -1
                result["message"] = "Params is nulls."
            }


        } catch (e) {
            result["status"] = -1
            result["message"] = "Query discount exception:${e.getMessage()}."
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
                    params["discountTitle"] = paramaJSONObject.getString("discountTitle");
                    params["discountType"] = paramaJSONObject.getString("discountType");
                    params["discountValue"] = paramaJSONObject.getString("discountValue");
                    params["discountId"] = paramaJSONObject.getString("discountId")
                    params["userId"] = paramaJSONObject.getString("userId")

                    if (params["discountTitle"] && params["userId"] && params["discountType"] && params["discountValue"] && params["discountId"]) {
                        Discount discount = discountService.queryDiscount(params)
                        if(discount){
                            discount = discountService.updateDiscount(discount,params,params["userId"])
                            result["status"] = 1
                            result["message"] = "Discount update success!!"
                            result["discount"] = convertObjectToMap(discount);
                        }else{
                            result["status"] = -1
                            result["message"] = "Can't fine discount to update!!"
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
            result["message"] = "Update discount exception:${e.getMessage()}."
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
                    params["discountTitle"] = paramaJSONObject.getString("discountTitle");
                    params["discountType"] = paramaJSONObject.getString("discountType");
                    params["discountValue"] = paramaJSONObject.getString("discountValue");
                    params["userId"] = paramaJSONObject.getString("userId");

                    if (params["discountTitle"] && params["discountType"]  && params["discountValue"]  && params["userId"]) {
                        def discount = discountService.createDiscount(params, params["userId"])
                        result["status"] = 1
                        result["message"] = "Discount create success."
                        result["discount"] = convertObjectToMap(discount);
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
            result["message"] = "Create discount exception:${e.getMessage()}."
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
                    params["discountId"] = paramaJSONObject.getString("discountId");
                    params["userId"] = paramaJSONObject.getString("userId")

                    if (params["discountId"] && params["userId"]) {
                        Discount discount = discountService.queryDiscount(params)
                        if(discount){
                            discountService.deleteDiscount(discount)
                            result["status"] = 1
                            result["message"] = "Delete discount success."
                            result["discount"] = convertObjectToMap(discount);
                        }else{
                            result["status"] = -1
                            result["message"] = "Can't find discount to delete."
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
            result["message"] = "Delete discount exception:${e.getMessage()}."
            e.printStackTrace()
        } finally {

            render result as JSON;
        }


    }
}
