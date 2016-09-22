package winnershop

import grails.transaction.Transactional
import helper.WhoColumnsValueGenerator

@Transactional
class PhotoService extends BaseService {

    def createPhoto(def params, def userId) {

        Photo photo = new Photo()
        photo.photoName = params.photoName
        photo.photoUrl = params.photoUrl
        photo.photoSize = Long.parseLong(params.photoSize)
        photo.fileType = params.fileType
        photo.properties = WhoColumnsValueGenerator.generateForCreation(Integer.parseInt(userId))
        save(photo)

        return photo;

    }

    def queryPhotoByPhotoName(String photoName) {
        queryPhotoByPhotoName(photoName, null)
    }

    def queryPhotoByPhotoName(String photoName, String userId) {
        Photo photo
        if (userId) {
            photo = Photo.findByPhotoNameAndCreatedUser(photoName, userId)
        } else {
            photo = Photo.findByPhotoName(photoName)
        }
        return photo
    }

    def queryPhoto(def params) {
        queryPhoto(params, null)
    }


    def queryPhoto(def params, def userId) {
        Photo photo
        if (userId) {
            photo = Photo.findByIdAndCreatedUser(params.photoId, userId)
        } else {
            photo = Photo.findById(params.photoId)
        }
        return photo
    }

    def deletePhoto(photo) {
        delete(photo)
        return photo;

    }

    def updatePhoto(Photo photo, def params, def userId) {
        if (photo) {
            photo.photoName = params.photoName
            photo.properties = WhoColumnsValueGenerator.generateForUpdate(Integer.parseInt(userId))
            save(photo)
        }

        return photo;
    }



}
