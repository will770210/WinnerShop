package helper

/**
 * Created by huangweihao on 2016/9/3.
 */
class WhoColumnsValueGenerator {

    static generateForCreation(int userId) {
        def now = new Date();
        return [
                createdUser : userId,
                createdDate : now,
                modifiedUser: userId,
                modifiedDate: now
        ]
    }

    static generateForUpdate(int userId) {

        def now = new Date();
        return [
                modifiedUser: userId,
                modifiedDate: now
        ]

    }
}
