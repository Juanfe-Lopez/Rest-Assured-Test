package config;

import org.junit.BeforeClass;

import io.restassured.RestAssured;

public class TestConfig {
  @BeforeClass
  public static void setup() {
    RestAssured.baseURI = "https://dummyjson.com/"; // This is the base URI
    
    // RestAssured.basePath = "v2/"; // This is the path (i.e. whats after the base
        // URI). For this fake api it won't be necessary

    // RestAssured.port = 443; // For this example it won't be necessary, but just
        // so you know you can set the port
  }
}
