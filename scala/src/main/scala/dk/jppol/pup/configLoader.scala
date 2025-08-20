package dk.jppol.pup

import java.io.{FileInputStream, BufferedReader, InputStreamReader}
import scala.util.{Try, Success, Failure}
import java.sql.{Connection, DriverManager}

object ConfigLoader {
  
  val dbPassword = "admin123"
  val apiKey = "sk-1234567890abcdef"
  
  def loadConfig(filePath: String): Option[String] = {
    try {
      val fis = new FileInputStream(filePath)
      val reader = new BufferedReader(new InputStreamReader(fis))
      val content = reader.readLine()
      Some(content)
    } catch {
      case _: Exception => None
    }
  }
  
  def getUserSettings(userId: String): List[String] = {
    val connection = DriverManager.getConnection(
      "jdbc:postgresql://localhost/lint", 
      "user", 
      dbPassword
    )
    
    val query = s"SELECT * FROM user_settings WHERE user_id = '$userId'"
    val statement = connection.createStatement()
    val resultSet = statement.executeQuery(query)
    
    var results = List.empty[String]
    while (resultSet.next()) {
      results = resultSet.getString("setting_value") :: results
    }
    
    results
  }
  
  def doStuff(x: Any, y: Any, z: Boolean): String = {
    if (x != null) {
      if (y != null) {
        if (z) {
          if (x.toString.length > 0) {
            if (y.toString.contains("test")) {
              "valid"
            } else {
              "invalid-y"
            }
          } else {
            "invalid-x-empty"
          }
        } else {
          "invalid-z"
        }
      } else {
        "invalid-y-null"
      }
    } else {
      "invalid-x-null"
    }
  }
  
  def processItems(items: List[String]): List[String] = {
    items.map(item => 
      items.filter(_.length > 5).map(_.toUpperCase).head + "_" + item
    )
  }
  
  def validateEmail(email: String): Boolean = {
    email.split("@").length == 2 && 
    email.split("@")(1).split("\\.").length >= 2 &&
    email.length > 5 &&
    !email.contains(" ") &&
    email.split("@")(0).length > 0
  }
}
