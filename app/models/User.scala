package models

import org.joda.time.LocalDateTime
import org.mindrot.jbcrypt.BCrypt

/**
 * Created by benevolent0505 on 17/03/10.
 */
class User private (
  val id: Long,
  val name: String,
  val email: String,
  val passwordDigest: String,
  val createdAt: LocalDateTime,
  val updatedAt: LocalDateTime
) {

  def verifyPassword(password: String): Boolean = {
    BCrypt.checkpw(password, passwordDigest)
  }
}

object User {
  def apply(
    id: Long,
    name: String,
    email: String,
    passwordDigest: String,
    createdAt: LocalDateTime = LocalDateTime.now,
    updatedAt: LocalDateTime = LocalDateTime.now
  ): User = {
    val validName = name match {
      case n if n.length > 50 => throw new Exception
      case _ => name
    }
    val validEmail = email match {
      case e if e.length > 255 => throw new Exception
      case e if !e.matches("""\A[\w+\-.]+@[a-z\d\-.]+\.[a-z]+\z""") => throw new Exception
      case _ => email
    }
    val validPass = passwordDigest match {
      case pass if pass.length < 6 => throw new Exception
      case _ => passwordDigest
    }

    new User(id = id, name = validName, email = validEmail, passwordDigest = validPass, createdAt = createdAt, updatedAt = updatedAt)
  }
}