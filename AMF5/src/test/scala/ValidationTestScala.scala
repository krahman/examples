import amf.apicontract.client.scala.RAMLConfiguration
import amf.core.client.common.validation.ProfileName
import org.scalatest.flatspec._
import org.scalatest.matchers._

class ValidationTestScala extends AsyncFlatSpec with should.Matchers {

  "Raml Validation" should "not conform when the api has a validation error" in {
    val client = RAMLConfiguration.RAML().createClient()
    client.parse("file://resources/examples/banking-api-error.raml") flatMap { result =>
      result.conforms shouldBe true
      client.validate(result.bu) map { validationResult =>
        validationResult.conforms shouldBe false
      }
    }
  }
}
