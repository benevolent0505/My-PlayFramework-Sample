package models

import org.joda.time.LocalDateTime

/**
 * Created by benevolent0505 on 17/03/10.
 */
case class Micropost (
  id: Int,
  context: String,
  user_id: Int,
  createdAt: LocalDateTime
)
