package shhashi.practice.i20250126.config

import org.jetbrains.exposed.sql.Database

class ExposedSettings {
    companion object {
        // TODO 設定ファイルから参照できるようにしたい
        fun initDb() {
            Database.connect(
                "jdbc:postgresql://localhost:5432/task_management",
                driver = "org.postgresql.Driver",
                user = "service",
                password = "abcD123"
            )
        }
    }
}
