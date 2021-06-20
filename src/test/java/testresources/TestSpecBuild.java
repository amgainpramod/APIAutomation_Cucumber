package testresources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.*;
import java.util.Properties;

public class TestSpecBuild {
    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;

    public RequestSpecification requestSpecification() throws IOException {
        if (requestSpec == null) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            requestSpec = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
                    .addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON)
                    .build();
            return requestSpec;
        }
        return requestSpec;
    }

    public ResponseSpecification responseSpecification() {
        responseSpec = new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
        return responseSpec;
    }

    public static String getGlobalValue(String key) throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/test/java/testresources/global.properties");
        properties.load(fileInputStream);
        String value = properties.getProperty(key);
        return value;
    }

    public String getJsonPath(Response response, String path){
        String responseString = response.asString();
        JsonPath jsonPath = new JsonPath(responseString);
        return jsonPath.getString(path);
    }

}
