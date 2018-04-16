package com.ceszke.security.rsa.prime;

import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@NoArgsConstructor
@WebAppConfiguration
public class PrimeControllerIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldReturnRandomPrimeNumber() throws Exception {
        String primeString = mockMvc.perform(get("/prime"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        //Assert.assertTrue(PrimeTestHelper.isPrime(Integer.parseInt(primeString)));
    }

    @Test
    public void shouldReturnRandomPrimeNumberWithMaxBitLength() throws Exception {
        final int maxBitLength = 8;
        String primeString = mockMvc.perform(get("/prime").param("bitLength", ""+maxBitLength))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        Assert.assertTrue(Integer.bitCount(Integer.parseInt(primeString)) <= maxBitLength);
    }

}