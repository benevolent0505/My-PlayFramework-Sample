package repository

import models.User
import org.joda.time.LocalDateTime
import org.mindrot.jbcrypt.BCrypt
import scalikejdbc._

/**
 * Created by benevolent0505 on 17/03/10.
 */
object Users {

  def * = (rs: WrappedResultSet) =>
    User(rs.long("id"), rs.string("name"), rs.string("email"), rs.string("password_digest"), rs.jodaLocalDateTime("created_at"), rs.jodaLocalDateTime("updated_at"))

  def create(name: String, email: String, password: String,
    createdAt: LocalDateTime = LocalDateTime.now, updatedAt: LocalDateTime = LocalDateTime.now)(implicit s: DBSession = AutoSession): User = {
    val passwordDigest = hashPassword(password)
    val id =
      sql"""
           insert into Users
             (name, email, password_digest, created_at, updated_at)
           values
             (${name}, ${email}, ${passwordDigest}, ${createdAt}, ${updatedAt})
        """
      .updateAndReturnGeneratedKey.apply()

    User(id = id, name = name, email = email, passwordDigest = passwordDigest, createdAt = createdAt, updatedAt = updatedAt)
  }

  def findById(id: Long)(implicit s: DBSession = AutoSession): Option[User] = {
    sql"""select * from Users where id = ${id}"""
      .map(*).single().apply()
  }

  def findByName(name: String)(implicit s: DBSession = AutoSession): Option[User] = {
    sql"""select * from Users where id = ${name}"""
      .map(*).single().apply()
  }

  def update(user: User, name: Option[String], email: Option[String], password: Option[String])(implicit s: DBSession = AutoSession): User = {
    val passwordDigest = password match {
      case Some(pass) => hashPassword(pass)
      case _ => user.passwordDigest
    }
    val updatedAt = LocalDateTime.now
    sql"""
         update Users
           set
             name = ${name.getOrElse(user.name)},
             email = ${email.getOrElse(user.email)},
             password_digest = ${passwordDigest}
             updated_at = ${updatedAt}
           where id = ${user.id}
      """.update().apply()

    User(
      id = user.id,
      name = name.getOrElse(user.name),
      email = email.getOrElse(user.email),
      passwordDigest = passwordDigest,
      createdAt = user.createdAt,
      updatedAt = updatedAt
    )
  }

  def destroy(user: User)(implicit s: DBSession = AutoSession): Unit = {
    sql"""delete from Users where id = ${user.id}""".update().apply()
  }

  def authenticate(username: String, password: String)(implicit s: DBSession = AutoSession): Boolean = {
    findByName(username) match {
      case Some(user) => user.verifyPassword(password)
      case _ => false
    }
  }

  def hashPassword(password: String): String = {
    BCrypt.hashpw(password, BCrypt.gensalt())
  }

  def findAll() = ???
}
