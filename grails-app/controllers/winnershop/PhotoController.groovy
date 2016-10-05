package winnershop

import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.JSONObject

import java.text.SimpleDateFormat

class PhotoController extends BaseController{
    def photoService

    def query = {

        def result = [:]

        try {
            if (request.JSON) {
                JSONObject paramaJSONObject = request.JSON

                if (paramaJSONObject instanceof JSONObject) {

                    def params = [:]
                    params["userId"] = paramaJSONObject.getString("userId")
                    params["queryType"] = paramaJSONObject.getString("queryType")
                    List<Photo> photoList
                    if (params["queryType"].toString().equalsIgnoreCase("user")) {
                        result["message"] = "Query photo by params:${params}."
                        photoList = Photo.findAllByCreatedUser(params["userId"]);

                    } else {
                        result["message"] = "Query all photo."
                        photoList = Photo.findAll();
                    }

                    result["status"] = 1
                    result["photoList"] = convertFormatService.convertListToMapList(photoList);

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
            result["message"] = "Query photo exception:${e.getMessage()}."
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
                    params["photoName"] = paramaJSONObject.getString("photoName");
                    params["photoUrl"] = paramaJSONObject.getString("photoUrl");
                    params["photoSize"] = paramaJSONObject.getString("photoSize");
                    params["fileType"] = paramaJSONObject.getString("fileType");
                    params["userId"] = paramaJSONObject.getString("userId");
                    params["photoId"] = paramaJSONObject.getString("photoId");


                    if (params["photoName"] && params["photoUrl"] && params["photoSize"] && params["fileType"]&&params["photoId"]) {
                        Photo photo = photoService.queryPhoto(params)
                        if (photo) {
                            photo = photoService.updatePhoto(photo, params, params["userId"])
                            result["status"] = 1
                            result["message"] = "photo update success!!"
                            result["photo"] = convertFormatService.convertObjectToMap(photo);
                        } else {
                            result["status"] = -1
                            result["message"] = "Can't fine photo to update!!"
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
            result["message"] = "Update photo exception:${e.getMessage()}."
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
                    params["photoName"] = paramaJSONObject.getString("photoName");
                    params["photoUrl"] = paramaJSONObject.getString("photoUrl");
                    params["photoSize"] = paramaJSONObject.getString("photoSize");
                    params["fileType"] = paramaJSONObject.getString("fileType");
                    params["userId"] = paramaJSONObject.getString("userId");


                    if (params["photoName"] && params["userId"] && params["photoUrl"] && params["photoSize"] && params["fileType"] && params["userId"]) {

                        Photo photo = photoService.queryPhotoByPhotoName(params["photoName"],params["userId"])
                        if (photo) {
                            result["status"] = -1
                            result["message"] = "Photo name is exist!"
                        } else {
                            photo = photoService.createPhoto(params, params["userId"])
                            result["status"] = 1
                            result["message"] = "Photo created success."
                            result["photo"] = convertFormatService.convertObjectToMap(photo);

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
            result["message"] = "Create photo exception:${e.getMessage()}."
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
                    params["photoId"] = paramaJSONObject.getString("photoId");
                    params["userId"] = paramaJSONObject.getString("userId");

                    if (params["photoId"] && params["userId"]) {
                        Photo photo = photoService.queryPhoto(params)
                        if (photo) {
                            photoService.deletePhoto(photo)
                            result["photo"] = convertFormatService.convertObjectToMap(photo);
                            result["status"] = 1
                            result["message"] = "Delete photo success."
                        } else {
                            result["status"] = -1
                            result["message"] = "Can't find photo to delete."
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
            result["message"] = "Delete photo exception:${e.getMessage()}."
            e.printStackTrace()
        } finally {

            render result as JSON;
        }


    }


}
