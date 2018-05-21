package com.ceszke.security.rsa.prime;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import java.math.BigInteger;

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

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${prime.certainty:15}")
    private Integer certainty;

    @Value("${prime.maximumPrimeLength:1024}")
    private Integer maximumPrimeLength;

    @Value("${prime.defaultPrimeLength:64}")
    private Integer defaultPrimeLength;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldReturnRandomPrimeNumberWithDefaultLength() throws Exception {
        String primeString = mockMvc.perform(get("/prime"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        BigInteger prime = objectMapper.readValue(primeString, BigInteger.class);

        Assert.assertTrue(prime.isProbablePrime(certainty));
        Assert.assertEquals(defaultPrimeLength.intValue(), prime.bitLength());
    }

    @Test
    public void shouldReturnRandomPrimeNumberWithMaximumAllowedLength() throws Exception {
        String primeString = mockMvc.perform(get("/prime").param("length", "" + maximumPrimeLength))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        BigInteger prime = objectMapper.readValue(primeString, BigInteger.class);

        Assert.assertTrue(prime.isProbablePrime(certainty));
        Assert.assertEquals(maximumPrimeLength.intValue(), prime.bitLength());
    }

    @Test(expected = NestedServletException.class)
    public void shouldNotReturnRandomPrimeNumberWithInvalidLength1() throws Exception {
        mockMvc.perform(get("/prime").param("length", "1"));
    }

    @Test(expected = NestedServletException.class)
    public void shouldNotReturnRandomPrimeNumberWithInvalidLength2() throws Exception {
        mockMvc.perform(get("/prime").param("length", ""+(maximumPrimeLength +1)));
    }

}