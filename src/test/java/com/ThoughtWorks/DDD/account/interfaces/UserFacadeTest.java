package com.ThoughtWorks.DDD.account.interfaces;

import com.ThoughtWorks.DDD.account.APIBaseTest;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder.okForJson;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserFacadeTest extends APIBaseTest {
    @Rule
    public WireMockRule accountServiceMock = new WireMockRule(9116);

    @Test
    public void should_create_bookings_without_authorization() throws Exception {
        this.mockMvc.perform(get("/api/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public final void shouldGetTheCustomerAfterJustCreated() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(withJson("user.json")))
                .andExpect(status().isCreated())
                .andReturn();

        String location = mvcResult.getResponse().getHeader("location");
        this.mockMvc.perform(get(location)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{" +
                        "    'data': {" +
                        "        'attributes': {" +
                        "            'firstName': 'Anne'," +
                        "            'lastName': 'Queen'," +
                        "            'emailAddress': 'james@gmail.com'," +
                        "            'phoneNumber': '13200001111'" +
                        "        }" +
                        "    }" +
                        "}"));
    }

    @Test
    public final void shouldGetTheStringByApiCall() throws Exception {
        stubFor(get(urlEqualTo("/api/users"))
                .willReturn(ok("demo")));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/users/sample").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("demo"));
    }

    @Test
    public void should_change_customer_contacts_after_created() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(withJson("user.json")))
                .andExpect(status().isCreated())
                .andReturn();

        String location = mvcResult.getResponse().getHeader("location");
        this.mockMvc.perform(patch(location)
                .contentType(MediaType.APPLICATION_JSON)
                .content(withJson("modified-contacts.json")))
                .andExpect(status().isNoContent());

        this.mockMvc.perform(get(location)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{" +
                        "    'data': {" +
                        "        'id': 1," +
                        "        'attributes': {" +
                        "            'firstName': 'Anne'," +
                        "            'lastName': 'Queen'," +
                        "            'emailAddress': 'james.merson@gmail.com'," +
                        "            'phoneNumber': '15800001111'" +
                        "        }" +
                        "    }" +
                        "}"));
    }
}
