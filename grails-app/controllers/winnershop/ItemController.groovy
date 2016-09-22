package winnershop

import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.JSONObject


class ItemController extends BaseController{
    def itemService

    def query = {

        def result = [:]

        try {
            if (request.JSON) {
                JSONObject paramaJSONObject = request.JSON

                if (paramaJSONObject instanceof JSONObject) {

                    def params = [:]
                    params["userId"] = paramaJSONObject.getString("userId")
                    params["queryType"] = paramaJSONObject.getString("queryType")
                    List<Item> itemList
                    if (params["queryType"].toString().equalsIgnoreCase("user")) {
                        result["message"] = "Query item by params:${params}."
                        itemList = Item.findAllByCreatedUser(params["userId"]);

                    } else {
                        result["message"] = "Query all item."
                        itemList = Item.findAll();
                    }

                    result["status"] = 1
                    result["itemList"] = convertListToMapList(itemList);

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
            result["message"] = "Create item exception:${e.getMessage()}."
            e.printStackTrace()
        } finally {

            render result as JSON;
        }

    }


    def update = {
        def result = [:]

        try {
            if (request.JSON) {
                JSONObject paramaJSONObject = request.JSON

                if (paramaJSONObject instanceof JSONObject) {
                    def params = [:]
                    params["itemName"] = paramaJSONObject.getString("itemName");
                    params["itemPrice"] = paramaJSONObject.getDouble("itemPrice");
                    params["photoId"] = paramaJSONObject.getInt("photoId");
                    params["colorId"] = paramaJSONObject.getInt("colorId");
                    params["categoryId"] = paramaJSONObject.getInt("categoryId");
                    params["itemId"] = paramaJSONObject.getString("itemId");
                    params["userId"] = paramaJSONObject.getString("userId");


                    if (params["itemName"] && params["itemPrice"] && params["photoId"] && params["colorId"]&&params["categoryId"]&&params["itemId"]&&params["userId"]) {
                        Item item = itemService.queryItem(params)
                        if (item) {
                            item = itemService.updateItem(item, params, params["userId"])
                            result["status"] = 1
                            result["message"] = "item update success!!"
                            result["item"] = convertObjectToMap(item);
                        } else {
                            result["status"] = -1
                            result["message"] = "Can't fine item to update!!"
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
            result["message"] = "Create item exception:${e.getMessage()}."
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
                    params["itemName"] = paramaJSONObject.getString("itemName");
                    params["itemPrice"] = paramaJSONObject.getDouble("itemPrice");
                    params["photoId"] = paramaJSONObject.getInt("photoId");
                    params["colorId"] = paramaJSONObject.getInt("colorId");
                    params["categoryId"] = paramaJSONObject.getInt("categoryId");
                    params["userId"] = paramaJSONObject.getString("userId");


                    if (params["itemName"] && params["itemPrice"] && params["photoId"] && params["colorId"] && params["categoryId"] && params["userId"]) {

                        Item item = itemService.queryItemByItemName(params["itemName"],params["userId"])
                        if (item) {
                            result["status"] = -1
                            result["message"] = "Item name is exist!"
                        } else {
                            item = itemService.createItem(params, params["userId"])
                            result["status"] = 1
                            result["message"] = "Item created success."
                            result["item"] = convertObjectToMap(item);

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
            result["message"] = "Create item exception:${e.getMessage()}."
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
                    params["itemId"] = paramaJSONObject.getString("itemId");
                    params["userId"] = paramaJSONObject.getString("userId");

                    if (params["itemId"] && params["userId"]) {
                        Item item = itemService.queryItem(params)
                        if (item) {
                            itemService.deleteItem(item)
                            result["item"] = convertObjectToMap(item);
                            result["status"] = 1
                            result["message"] = "Delete item success."
                        } else {
                            result["status"] = -1
                            result["message"] = "Can't find item to delete."
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
            result["message"] = "Create item exception:${e.getMessage()}."
            e.printStackTrace()
        } finally {

            render result as JSON;
        }


    }
}
