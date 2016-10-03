package winnershop

class Cart {

    int itemQuantity
    Long createdUser
    Date createdDate
    static belongsTo = [item:Item,discount:Discount]
    static constraints = {

    }
}
