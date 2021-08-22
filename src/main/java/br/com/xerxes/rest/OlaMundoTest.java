package br.com.xerxes.rest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class OlaMundoTest {

	@Test
	public void testOlaMundo() {
		Response response = RestAssured.request(Method.GET, "http://restapi.wcaquino.me/ola");
		Assert.assertTrue(response.getBody().asString().equals("Ola Mundo!"));
		Assert.assertTrue(response.statusCode() == 200);
		Assert.assertTrue("O status code deveria ser 200",response.statusCode() == 200);
		Assert.assertEquals(200, response.statusCode());
		
		ValidatableResponse validacao = response.then();
		validacao.statusCode(200);
	}
	
	@Test
	public void devoConhecerOutrasFormasRestAssured() {
		//Response response = RestAssured.request(Method.GET, "http://restapi.wcaquino.me/ola");
		
		//ValidatableResponse validacao = response.then();
		//validacao.statusCode(200);
		
		//get("http://restapi.wcaquino.me/ola").then()
		//.statusCode(200);
		
		given() // pré condições
		.when() // ação
			.get("http://restapi.wcaquino.me/ola")
		.then() // assertivos
			.statusCode(200);
		
	}
	
	@Test
	public void devoConhecerMatcherHamcrest() {
		MatcherAssert.assertThat("Maria", startsWith("Mar"));
		MatcherAssert.assertThat(128, Matchers.is(128));
		MatcherAssert.assertThat(128, Matchers.isA(Integer.class));
		MatcherAssert.assertThat(128, Matchers.greaterThan(120));
	}
	
	@Test
	public void validarBody() {
		given() // pré condições
		.when() // ação
			.get("http://restapi.wcaquino.me/ola")
		.then() // assertivos
			.statusCode(200)
			.body(is("Ola Mundo!"))
			.body(containsString("Mundo"))
			.body(is(not(nullValue())));
	}
	
}
