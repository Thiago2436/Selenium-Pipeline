package com.supplier.login.pages;

import java.util.HashMap;
import org.json.simple.JSONArray;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.sol.qa.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class SupplierApiTesting extends TestBase {
  final int successCode = 200;

  /**
   * This method hits the API and returns the accesstoken.
   */
  public String accessToken() {
    RestAssured.baseURI = "https://point.krinati.co/admin-management-micro/login-management/login/?device_id=5043216e9d2f969f05e292df9f176d55";
    RequestSpecification httpRequest = RestAssured.given();
    HashMap<String, Object> requestParams = new HashMap<String, Object>();
    requestParams.put("email", "faisal@selekt.in");
    requestParams.put("password", "12345");
    httpRequest.header("Content-Type", "application/json").header("x-sub-domain", "test.buyer1.krinati.co");
    httpRequest.body(requestParams.toString());
    Response response = httpRequest.request(Method.POST);
    String responseString = response.asString();
    System.out.println(responseString);
    JsonPath js = new JsonPath(responseString);
    String str = js.get("data.access_token");
    System.out.println(str);

    return str;
    }

  @Test
  public void registrationSuccessful() {
    System.out.println("company_name" + getProp().getProperty("SupplierName"));

    RestAssured.baseURI = "https://point.krinati.co/supplier-management-micro/adminportal/supplier/";

    RequestSpecification httpRequest = RestAssured.given();

    String accessToken = accessToken();
    final int categoryCode = 10100001;
    HashMap<String, Object> contact = new HashMap<String, Object>();
    contact.put("name", getProp().getProperty("SupplierName"));
    contact.put("email", getProp().getProperty("supplierEmail"));
    contact.put("mobile_number", "987654320");
    contact.put("key_contact", true);
    contact.put("id", 0);

    JSONArray chArray = new JSONArray();
    chArray.add(contact);

    HashMap<String, Object> category = new HashMap<String, Object>();
    category.put("category_name", "Live Animals Not Food");
    category.put("category_code", categoryCode);
    category.put("category_tree",
        "Live Plant & Animal Material & Accessories & Supplies, Live Animals, Live Animals Not Food");

    JSONArray chArr1 = new JSONArray();
    chArr1.add(category);

    HashMap<String, Object> requestParams = new HashMap<String, Object>();
    requestParams.put("company_name", getProp().getProperty("SupplierName"));
    requestParams.put("pan_no", getProp().getProperty("pan_No"));
    requestParams.put("state", "TamilNadu");
    requestParams.put("country", "India");
    requestParams.put("telephone_number", "");
    requestParams.put("pincode", "");
    requestParams.put("tax_reg", "");
    requestParams.put("address", "");
    requestParams.put("city", "");
    requestParams.put("home_page", "");
    requestParams.put("documents", "");

    requestParams.put("contacts", chArray);

    requestParams.put("categories", chArr1);


    httpRequest.header("Content-Type", "application/json").header("Authorization", "Bearer " + accessToken)
    .header("x-sub-domain", "test.buyer1.krinati.co");

    httpRequest.body(requestParams.toString());

    Response response = httpRequest.request(Method.POST);

    int statusCode = response.getStatusCode();
    System.out.println("The status code recieved: " + statusCode);
    ResponseBody body = response.getBody();
    String bodyString = body.asString();
    System.out.println("Resonse Message" + bodyString);
    Assert.assertEquals(bodyString.contains("Details Added Successfully"), true, "Supplier Added Successfully");
    // System.out.println("The status code recieved: " + statusCode);

    Assert.assertEquals(successCode, response.getStatusCode());

    }

  }
