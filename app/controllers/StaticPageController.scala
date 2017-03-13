package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc._
/**
 * Created by benevolent0505 on 17/03/11.
 */
@Singleton
class StaticPageController @Inject() extends Controller {

  def home = Action {
    Ok(views.html.staticpages.home())
  }

  def help = Action {
    Ok(views.html.staticpages.help())
  }

  def about = Action {
    Ok(views.html.staticpages.about())
  }

  def contact = Action {
    Ok(views.html.staticpages.contact())
  }
}
