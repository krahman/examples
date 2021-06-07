import amf.client.exported.{OASConfiguration, RAMLConfiguration}
import org.junit.Assert._
import org.junit.Test


class ParsingTestScala {

  @Test def parseOas20(): Unit = {
    val client = OASConfiguration.OAS20().createClient()
    val parsingResult = client.parse("file://resources/examples/banking-api.json").get()
    assertTrue(parsingResult.conforms)
  }

  @Test def parseOas20String(): Unit = {
    val client = OASConfiguration.OAS20().createClient()
    val api =
      """{
        |    "swagger": 2.0,
        |    "info": {
        |        "title": "ACME Banking HTTP API",
        |        "version": "1.0"
        |    },
        |    "host": "acme-banking.com"
        |}""".stripMargin
    val parsingResult = client.parseContent(api).get();
    assertTrue(parsingResult.conforms)
  }

  @Test def parseOas30(): Unit = {
    val parsingResult = OASConfiguration
      .OAS30()
      .createClient()
      .parse("file://resources/examples/banking-api-oas30.json")
      .get()
    assertTrue(parsingResult.conforms)
  }

  @Test def parseRaml10(): Unit = {
    val parsingResult = RAMLConfiguration
      .RAML10()
      .createClient()
      .parse("file://resources/examples/banking-api.raml")
      .get()
    assertTrue(parsingResult.conforms)
  }

  @Test def parseRaml10String(): Unit = {
    val api =
      """#%RAML 1.0
        |
        |title: ACME Banking HTTP API
        |version: 1.0""".stripMargin
    val parsingResult = RAMLConfiguration
      .RAML10()
      .createClient()
      .parseContent(api)
      .get()
    assertTrue(parsingResult.conforms)
  }

  @Test def parseRaml08(): Unit = {
    val parsingResult = RAMLConfiguration
      .RAML08()
      .createClient()
      .parse("file://resources/examples/banking-api-08.raml")
      .get()
    assertTrue(parsingResult.conforms)
  }

  @Test def parseRaml08String(): Unit = {
    val api =
      """#%RAML 0.8
        |
        |title: ACME Banking HTTP API
        |version: 1.0""".stripMargin
    val parsingResult = RAMLConfiguration
      .RAML08()
      .createClient()
      .parseContent(api)
      .get()
    assertTrue(parsingResult.conforms)
  }
  // TODO: add graph parse
}
