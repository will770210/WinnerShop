package winnershop

import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.JSONObject

class ColorController extends BaseController {

    def colorService

    def query = {

        def result = [:]

        try {
            if (request.JSON) {
                JSONObject paramaJSONObject = request.JSON

                if (paramaJSONObject instanceof JSONObject) {
                    result["message"] = "Query cateogry all."
                    List<Color> colorList = Color.findAll();


                    result["status"] = 1
                    result["colorList"] = convertFormatService.convertListToMapList(colorList);

                } else {

                    result["status"] = -1
                    result["message"] = "Params format is incorrect."
                    result["colorList"] = []
                }
            } else {
                result["status"] = -1
                result["message"] = "Params is nulls."
                result["colorList"] = []
            }


        } catch (e) {
            result["status"] = -1
            result["message"] = "Query color exception:${e.getMessage()}."
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
                    params["colorName"] = paramaJSONObject.getString("colorName");
                    params["colorCode"] = paramaJSONObject.getString("colorCode");
                    params["colorId"] = paramaJSONObject.getInt("colorId");

                    if (params["colorName"] && params["colorCode"] && params["colorId"]) {
                        Color color = colorService.queryColor(params)
                        if (color) {
                            color = colorService.updateColor(color, params, null)
                            result["status"] = 1
                            result["message"] = "Color update success!!"
                            result["color"] = convertFormatService.convertObjectToMap(color);
                        } else {
                            result["status"] = -1
                            result["message"] = "Can't fine color to update!!"
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
            result["message"] = "Update color exception:${e.getMessage()}."
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
                    params["colorName"] = paramaJSONObject.getString("colorName");
                    params["colorCode"] = paramaJSONObject.getString("colorCode")

                    if (params["colorName"] && params["colorCode"]) {
                        Color color = colorService.queryColorByColorName(params["colorName"], null)
                        if (color) {
                            result["status"] = -1
                            result["message"] = "Color had exist."
                        } else {

                            color = colorService.createColor(params, params["userId"])
                            result["status"] = 1
                            result["message"] = "Color create success."
                            result["color"] = convertFormatService.convertObjectToMap(color);

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
            result["message"] = "Create color exception:${e.getMessage()}."
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
                    params["colorId"] = paramaJSONObject.getString("colorId");

                    if (params["colorId"]) {
                        Color color = colorService.queryColor(params)
                        if (color) {
                            colorService.deleteColor(color)
                            result["status"] = 1
                            result["message"] = "Delete color success."
                            result["color"] = convertFormatService.convertObjectToMap(color);
                        } else {
                            result["status"] = -1
                            result["message"] = "Can't find color to delete."
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
            result["message"] = "Delete color exception:${e.getMessage()}."
            e.printStackTrace()
        } finally {

            render result as JSON;
        }


    }
}
