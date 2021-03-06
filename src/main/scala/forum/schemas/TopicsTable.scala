package forum.schemas
import forum.models.{Protocols, _}
import java.sql.Timestamp

import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import pl.iterators.kebs._

object XD extends Kebs with KebsColumnExtensionMethods with Protocols

class TopicsTable(tag: Tag) extends Table[Topic](tag, "topics") {
  import XD._
  def id: Rep[Id] = column[Id]("id", O.PrimaryKey)

  def nickname: Rep[Nickname] = column[Nickname]("nickname")

  def mail: Rep[Mail] = column[Mail]("mail")

  def topic: Rep[TopicName] = column[TopicName]("topic")

  def content: Rep[Content] = column[Content]("content")

  def lastActivity: Rep[Timestamp] = column[Timestamp]("last_activity")

  def secret: Rep[Secret] = column[Secret]("secret")

  def * : ProvenShape[Topic] = (id, nickname, mail, topic, content, lastActivity, secret) <> ((Topic.apply _).tupled, Topic.unapply)

}