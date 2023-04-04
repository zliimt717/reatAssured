import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RestAssuredTest {
    @Test
    public void case_1_getEmployees(){
        // Specify the base URL to the RESTful web service
        RestAssured.baseURI="http://localhost:8080/employees";
        // Get the RequestSpecification of the request to be sent to the server
        RequestSpecification httpRequest=RestAssured.given();
        // specify the method type (GET) and the parameters if any
       // Response response1=httpRequest.request(Method.GET,"");
        Response response=httpRequest.get("");

        System.out.println("Status received =>"+response.getStatusCode());
        System.out.println("Response =>"+response.prettyPrint());
    }

    @Test
    public void case_2_createEmployee(){
        RestAssured.baseURI="http://localhost:8080/employees";
        RequestSpecification request=RestAssured.given();
        JSONObject requestParams=new JSONObject();
        requestParams.put("firstname","Jane");
        requestParams.put("lastname","Emma");
        request.header("Content-Type","application/json");
        request.body(requestParams.toString());
        Response response=request.post("/post/employee");
        ResponseBody body=response.getBody();

        System.out.println(response.getStatusLine());
        System.out.println(body.prettyPrint());

    }

    @Test
    public void case_3_getEmployeesById(){
        // Specify the base URL to the RESTful web service
        RestAssured.baseURI="http://localhost:8080/employees";
        // Get the RequestSpecification of the request to be sent to the server
        RequestSpecification httpRequest=RestAssured.given();
        // specify the method type (GET) and the parameters is the id
        Response response=httpRequest.get("1");

        System.out.println("Status received =>"+response.getStatusCode());
        System.out.println("Response =>"+response.prettyPrint());
    }

    @Test
    public void case_4_updateEmployee(){
        RestAssured.baseURI="http://localhost:8080/employees";
        RequestSpecification request=RestAssured.given();

        JSONObject requestParams=new JSONObject();
        requestParams.put("firstname","Anne");
        requestParams.put("lastname","Emma");
        request.header("Content-Type","application/json");
        request.body(requestParams.toString());
        Response response=request.put("/put/1");
        ResponseBody body=response.getBody();

        System.out.println(response.getStatusLine());
        System.out.println(body.asString());
    }

    @Test
    public void case_5_deleteEmployee(){
        RestAssured.baseURI="http://localhost:8080/employees";
        RequestSpecification request=RestAssured.given();
        request.header("Content-Type","application/json");

        Response response= request.delete("/delete/1");

        System.out.println(response.getStatusLine());

    }





}
