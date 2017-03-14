package utils

import scalikejdbc._

/**
  * Created by mikio on 2017/03/13.
  */
object DBinitializer {

  def run(name: Symbol): Unit = {
    NamedDB(name) localTx { implicit session =>
      try {
        sql"""select 1 from Users limit 1""".map(_.long(1)).single().apply()
      } catch {
        case e: java.sql.SQLException =>
          NamedDB(name) localTx { implicit session =>
            sql"""
                create table Users (
                    id serial not null primary key,
                    name varchar(64) not null,
                    email varchar(64) not null unique,
                    password_digest varchar(64) not null,
                    created_at timestamp not null,
                    updated_at timestamp not null
                )
              """.execute.apply()
          }
      }
    }
  }
}
