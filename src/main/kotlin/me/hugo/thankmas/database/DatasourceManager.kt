package me.hugo.thankmas.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import java.sql.Connection
import java.util.*

public open class DatasourceManager(
    private val ip: String,
    private val port: String,
    private val schema: String,
    private val user: String,
    private val password: String
) {

    /** Primary data source to use for connections. */
    public val dataSource: HikariDataSource

    init {
        val config = HikariConfig(buildProperties())
        config.maximumPoolSize = 10

        this.dataSource = HikariDataSource(config)
    }

    /**
     * Builds the data source properties.
     */
    private fun buildProperties(): Properties {
        val props = Properties()

        props.setProperty("jdbcUrl", "jdbc:mysql://$ip:$port/$schema")
        props.setProperty("dataSource.serverName", ip)
        props.setProperty("dataSource.portNumber", port)
        props.setProperty("dataSource.user", user)
        props.setProperty("dataSource.password", password)
        props.setProperty("dataSource.databaseName", schema)

        return props
    }

    /** Gets a usable connection from [dataSource]. */
    public fun getConnection(): Connection {
        return this.dataSource.connection
    }

}