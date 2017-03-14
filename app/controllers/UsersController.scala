package controllers

import javax.inject.{Inject, Singleton}

import forms.CreateUserForm
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc.{Action, Controller}
import repository.Users

/**
 * Created by benevolent0505 on 17/03/10.
 */
@Singleton
class UsersController @Inject() (val messagesApi: MessagesApi)
  extends Controller with I18nSupport {

  def index = ???

  def show(id: Long) = Action {
    Users.findById(id).map { user =>
      Ok(views.html.users.show(user))
    }.getOrElse(NotFound)
  }

  // new
  def signup() = Action {
    Ok(views.html.users.signup(CreateUserForm.userForm))
  }

  def edit(id: Int) = ???

  // TODO: Protesting CSRF
  def create() = Action { implicit request =>
    CreateUserForm.userForm.bindFromRequest.fold(
      errorForm => {
        Ok(views.html.users.signup(errorForm))
      },
      user => {
        val created = Users.create(user.name, user.email, user.password)
        Redirect(routes.UsersController.show(created.id))
      }
    )
  }

  def update = ???

  def destroy = ???
}
