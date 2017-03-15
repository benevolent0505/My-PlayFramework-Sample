package forms

import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints._

/**
  * Created by mikio on 2017/03/13.
  */
case class CreateUserData(
  name: String,
  email: String,
  password: String,
  passwordConfirmation: String
)

object CreateUserForm {
  val userForm: Form[CreateUserData] = Form {
    mapping(
      "name" -> text.verifying(nonEmpty, maxLength(50)),
      "email" -> email.verifying(maxLength(255)),
      "password" -> text.verifying(minLength(6)),
      "passwordConfirmation" -> text.verifying(minLength(6))
    )(CreateUserData.apply)(CreateUserData.unapply)
      .verifying("Password does not match", data => data.password == data.passwordConfirmation)
  }
}
