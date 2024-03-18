import org.junit.Test;

import config.TestConfig;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.io.InputStream;

import io.restassured.module.jsv.JsonSchemaValidator;
/**
 * Unit test for simple App.
 */
public class AppTest extends TestConfig {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void getAllProducts() {
        given()
            .contentType(ContentType.JSON)
            .basePath("/products")
        .when()
            .get()
        .then()
            .assertThat()
            .statusCode(200)
            .log().all();

    }
    @Test
    public void addNewProduct(){

        String productJSON= "{\n \"title\": \"New product\" \n }";
        given()
            .body(productJSON)
            .pathParam("item","add")
        .when()
            .post("products/{item}")
        .then()
            .assertThat()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .log().all();
    }

    @Test
    public void deleteProduct(){
        given()
            .pathParam("item","1")
        .when()
            .delete("products/{item}")
        .then()
            .log().all()
            .statusCode(200)
            .contentType(ContentType.JSON);
            //.assertThat("isDeleted", equalTo(true));  
    }
    
    @Test
    public void deleteProductSchemaValidation(){
        
    	InputStream deleteProductSchema= getClass().getClassLoader().getResourceAsStream("deleteProductSchema.json");
        
    	given()
            .pathParam("item","1")
        .when()
            .delete("products/{item}")
        .then()
            .body(JsonSchemaValidator.matchesJsonSchema(deleteProductSchema));
    }
}
