package dk.jppol.pup

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ConfigLoaderSpec extends AnyFlatSpec with Matchers {
  
  "ConfigLoader" should "load config" in {
    val result = ConfigLoader.loadConfig("test.conf")
  }
  
  "getUserSettings" should "return user settings" in {
    val settings = ConfigLoader.getUserSettings("user123")
    settings should not be empty
  }
  
  "doStuff" should "work correctly" in {
    val result = ConfigLoader.doStuff("hello", "test", true)
    result shouldBe "valid"
  }
}