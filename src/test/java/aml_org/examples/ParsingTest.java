package aml_org.examples;

import amf.client.AMF;
import amf.client.model.document.BaseUnit;
import amf.client.model.document.Document;
import amf.client.model.domain.DomainElement;
import amf.client.parse.AmfGraphParser;
import amf.client.parse.Oas20Parser;
import amf.client.parse.Raml08Parser;
import amf.client.parse.Raml10Parser;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertNotNull;


public class ParsingTest {

    @BeforeClass
    public static void setup() throws ExecutionException, InterruptedException {
        AMF.init().get();
    }


    @Test
    public void parseOas20() throws ExecutionException, InterruptedException {
        // Get the parser type corresponding to the source API type
        final Oas20Parser parser = new Oas20Parser();

        // A BaseUnit is the return type of any parsing
        // The actual object can be many different things, depending on the content of the source file
        // https://github.com/aml-org/amf/blob/develop/documentation/model.md#baseunit
        final BaseUnit model = parser.parseFileAsync("file://src/main/resources/examples/banking-api.json").get();
        assertNotNull(model);

        // DomainElement is the base class for any element describing a domain model
        final DomainElement webApi = ((Document) model).encodes();
        assertNotNull(webApi);
    }

    @Test
    public void parseOas20String() throws ExecutionException, InterruptedException {
        final Oas20Parser parser = new Oas20Parser();

        final String api =
                "{\n" +
                        "  \"swagger\": \"2.0\",\n" +
                        "  \"info\": {\n" +
                        "    \"title\": \"ACME Banking HTTP API\",\n" +
                        "    \"version\": \"1.0\"\n" +
                        "  },\n" +
                        "  \"host\": \"acme-banking.com\"" +
                        "}";

        final BaseUnit model = parser.parseStringAsync(api).get();
        assertNotNull(model);

        final DomainElement webApi = ((Document) model).encodes();
        assertNotNull(webApi);
    }

    @Test
    public void parseRaml10() throws ExecutionException, InterruptedException {
        final Raml10Parser parser = new Raml10Parser();

        final BaseUnit model = parser.parseFileAsync("file://src/main/resources/examples/banking-api.raml").get();
        assertNotNull(model);

        final DomainElement webApi = ((Document) model).encodes();
        assertNotNull(webApi);
    }

    @Test
    public void parseRaml10String() throws ExecutionException, InterruptedException {
        final Raml10Parser parser = new Raml10Parser();

        final String api =
                "#%RAML 1.0\n" +
                        "\n" +
                        "title: ACME Banking HTTP API\n" +
                        "version: 1.0";

        final BaseUnit model = parser.parseStringAsync(api).get();
        assertNotNull(model);

        final DomainElement webApi = ((Document) model).encodes();
        assertNotNull(webApi);
    }

    @Test
    public void parseRaml08() throws ExecutionException, InterruptedException {
        final Raml08Parser parser = new Raml08Parser();

        final BaseUnit model = parser.parseFileAsync("file://src/main/resources/examples/banking-api-08.raml").get();
        assertNotNull(model);

        final DomainElement webApi = ((Document) model).encodes();
        assertNotNull(webApi);
    }

    @Test
    public void parseRaml08String() throws ExecutionException, InterruptedException {
        final Raml08Parser parser = new Raml08Parser();

        final String api =
                "#%RAML 0.8\n" +
                        "\n" +
                        "title: ACME Banking HTTP API\n" +
                        "version: 1.0";

        final BaseUnit model = parser.parseStringAsync(api).get();
        assertNotNull(model);

        final DomainElement webApi = ((Document) model).encodes();
        assertNotNull(webApi);
    }

    @Test
    public void parseAMFGraph() throws ExecutionException, InterruptedException {
        final AmfGraphParser parser = new AmfGraphParser();

        final BaseUnit model = parser.parseFileAsync("file://src/main/resources/examples/banking-api.jsonld").get();
        assertNotNull(model);

        final DomainElement webApi = ((Document) model).encodes();
        assertNotNull(webApi);
    }

    @Test
    public void parseAMFGraphString() throws ExecutionException, InterruptedException {
        final AmfGraphParser parser = new AmfGraphParser();

        final String api =
                "[\n" +
                        "  {\n" +
                        "    \"@id\": \"\",\n" +
                        "    \"@type\": [\n" +
                        "      \"http://raml.org/vocabularies/document#Document\",\n" +
                        "      \"http://raml.org/vocabularies/document#Fragment\",\n" +
                        "      \"http://raml.org/vocabularies/document#Module\",\n" +
                        "      \"http://raml.org/vocabularies/document#Unit\"\n" +
                        "    ],\n" +
                        "    \"http://raml.org/vocabularies/document#encodes\": [\n" +
                        "      {\n" +
                        "        \"@id\": \"#/web-api\",\n" +
                        "        \"@type\": [\n" +
                        "          \"http://schema.org/WebAPI\",\n" +
                        "          \"http://raml.org/vocabularies/document#DomainElement\"\n" +
                        "        ],\n" +
                        "        \"http://schema.org/name\": [\n" +
                        "          {\n" +
                        "            \"@value\": \"ACME Banking HTTP API\"\n" +
                        "          }\n" +
                        "        ],\n" +
                        "        \"http://raml.org/vocabularies/http#host\": [\n" +
                        "          {\n" +
                        "            \"@value\": \"acme-banking.com\"\n" +
                        "          }\n" +
                        "        ]" +
                        "}]}]";

        final BaseUnit model = parser.parseStringAsync(api).get();
        assertNotNull(model);

        final DomainElement webApi = ((Document) model).encodes();
        assertNotNull(webApi);
    }
}
