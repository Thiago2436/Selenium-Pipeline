/**
 * Info about this package doing something for package-info.java file.
 */
package com.admin.login.pages;

import com.sol.qa.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
* ApiTesting is the program to hit the loginAPI.
* Allows the buyer to login successfully
* @author  YamunaS
*/
public class ApiTesting extends TestBase {

  @Test
public void successfulRegister() {
    RestAssured.baseURI = "https://point.krinati.co/admin-management-micro/login-management/users/";
    final RequestSpecification httpRequest = RestAssured.given();
    final String successStatus = "200";
    HashMap<String, Object> requestParams = new HashMap<String, Object>();
    System.out.println("BuyerName");
    System.out.println("BuyerEmail");
    requestParams.put("name", getProp().getProperty("buyerName"));
    requestParams.put("email", getProp().getProperty("buyerEmail"));
    httpRequest.header("Content-Type", "application/json").header("x-sub-domain", "test.buyer1.krinati.co");
    httpRequest.body(requestParams.toString());
    Response response = httpRequest.request(Method.POST);
    Assert.assertEquals(successStatus, response.getStatusCode());

    }
  }
