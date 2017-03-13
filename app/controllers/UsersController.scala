package controllers

import javax.inject.{Inject, Singleton}

import play.api.mvc.{Action, Controller}
import repository.Users

/**
 * Created by benevolent0505 on 17/03/10.
 */
@Singleton
class UsersController @Inject() extends Controller {

  def index = ???

  def show(id: Long) = Action {
    Users.findById(id).map { user =>
      Ok(views.html.users.show(user))
    }.getOrElse(NotFound)
  }

  // new
  def signup() = Action {
    Ok(views.html.users.signup())
  }

  def edit(id: Int) = ???

  def create() = ???

  def update = ???

  def destroy = ???
}
