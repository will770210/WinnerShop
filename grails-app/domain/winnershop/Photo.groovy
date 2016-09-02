package winnershop

import domain.BaseEntity

class Photo extends BaseEntity{

    String photoName
    String photoUrl
    Long photoSize
    String fileType


    static constraints = {
    }
}
