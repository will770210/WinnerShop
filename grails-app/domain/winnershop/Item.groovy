package winnershop

import domain.BaseEntity

class Item extends BaseEntity{

    String itemName;
    Double itemPrice;

    static belongsTo = [category:Category,photo:Photo,color:Color]
    static constraints = {
        itemName unique: true
    }
}
