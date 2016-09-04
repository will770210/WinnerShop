package helper

import org.grails.datastore.mapping.query.Query

/**
 * Created by huangweihao on 2016/9/3.
 */
class WhoColumnsValueGenerator {

    static generateForCreation(Integer userId) {
        def now = new Date();
        return [
                createdUser : userId,
                createdDate : now,
                modifiedUser: userId,
                modifiedDate: now
        ]
    }

    static generateForUpdate(Integer userId) {

        def now = new Date();
        return [
                modifiedUser: userId,
                modifiedDate: now
        ]

    }
}
