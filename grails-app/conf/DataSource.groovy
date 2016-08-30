dataSource {
    pooled = true
    jmxExport = true

}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
//    cache.region.factory_class = 'org.hibernate.cache.SingletonEhCacheRegionFactory' // Hibernate 3
    cache.region.factory_class = 'org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory' // Hibernate 4
    singleSession = true // configure OSIV singleSession mode
    flush.mode = 'manual' // OSIV session flush mode outside of transactional context
}

// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "update"
            driverClassName = "org.postgresql.Driver"
            dialect = org.hibernate.dialect.PostgreSQLDialect

            uri = new URI(System.env.DATABASE_URL?:"postgres://kxcftwopnnowkj:AQLoVge9Ig8PIQv2eZgs0qEXQS@ec2-54-83-202-115.compute-1.amazonaws.com:5432/d49sub6tqid8d6")

            url = "jdbc:postgresql://" + uri.host + ":" + uri.port + uri.path + "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory"
            username = uri.userInfo.split(":")[0]
            password = uri.userInfo.split(":")[1]
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            driverClassName = "org.postgresql.Driver"
            dialect = org.hibernate.dialect.PostgreSQLDialect

            uri = new URI(System.env.DATABASE_URL?:"postgres://kxcftwopnnowkj:AQLoVge9Ig8PIQv2eZgs0qEXQS@ec2-54-83-202-115.compute-1.amazonaws.com:5432/d49sub6tqid8d6")

            url = "jdbc:postgresql://" + uri.host + ":" + uri.port + uri.path + "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory"
            username = uri.userInfo.split(":")[0]
            password = uri.userInfo.split(":")[1]
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            driverClassName = "org.postgresql.Driver"
            dialect = org.hibernate.dialect.PostgreSQLDialect

            uri = new URI(System.env.DATABASE_URL?:"postgres://kxcftwopnnowkj:AQLoVge9Ig8PIQv2eZgs0qEXQS@ec2-54-83-202-115.compute-1.amazonaws.com:5432/d49sub6tqid8d6")

            url = "jdbc:postgresql://" + uri.host + ":" + uri.port + uri.path + "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory"
            username = uri.userInfo.split(":")[0]
            password = uri.userInfo.split(":")[1]
        }
    }
}
