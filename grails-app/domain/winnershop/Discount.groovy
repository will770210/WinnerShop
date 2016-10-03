package winnershop

import domain.BaseEntity

class Discount extends BaseEntity{
    String discountTitle
    int discountType
    double discountValue
    static constraints = {
    }
}
