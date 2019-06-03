package forum

import slick.jdbc.H2Profile.api.Database
import slick.dbio.NoStream
import slick.lifted.TableQuery
import slick.sql.{FixedSqlStreamingAction, SqlAction}
import scala.language.implicitConversions
import scala.concurrent.Future

trait DbBase extends DbScheme {
  val db: Database = Database.forConfig("postgres")
  protected implicit def executeFromDb[A](action: SqlAction[A, NoStream, _ <: slick.dbio.Effect]): Future[A] = db.run(action)
  protected implicit def executeReadStreamFromDb[A](action: FixedSqlStreamingAction[Seq[A], A, _ <: slick.dbio.Effect]): Future[Seq[A]] = db.run(action)
}