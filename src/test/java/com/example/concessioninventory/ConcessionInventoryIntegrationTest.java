package com.example.concessioninventory;

import com.example.concessioninventory.snack.Snack;
import com.example.concessioninventory.snack.SnackCategory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ConcessionInventoryIntegrationTest {

    @LocalServerPort
    private int portNumber;
    @Autowired
    private TestRestTemplate restTemplate;
    private Snack testSnack1;
    private Snack testSnack2;
    private Snack testSnack3;
    private Snack testSnack1Altered;
    private Snack testSnack2Altered;
    private Snack testSnack3Altered;
    private String testSnack1Json;
    private String testSnack2Json;
    private String testSnack3Json;
    private String testSnack1AlteredJson;
    private String testSnack2AlteredJson;
    private String testSnack3AlteredJson;
    private String url;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() throws JsonProcessingException {
        url = "http://localhost:"+ portNumber + "/concession-inventory-api/v1";
        testSnack1 = new Snack(1L, "Snickers", 1.99, SnackCategory.Sweet,
                true, true, false, 50);
        testSnack2 = new Snack(2L, "Lays", 0.99, SnackCategory.Salty,
                false, true, false, 30);
        testSnack3 = new Snack(3L, "Strawberry Milkshake", 5.99, SnackCategory.Drink,
                false, false, true, 10);
        testSnack1Altered = new Snack(1L, "Snickers", 2.99, SnackCategory.Sweet,
                true, true, false, 50);
        testSnack2Altered = new Snack(2L, "Lays", 0.99, SnackCategory.Salty,
                false, true, false, 15);
        testSnack3Altered = new Snack(3L, "Strawberry Milkshake", 6.99, SnackCategory.Drink,
                false, false, true, 20);

        objectMapper = new ObjectMapper();
        testSnack1Json = objectMapper.writeValueAsString(testSnack1);
        testSnack2Json = objectMapper.writeValueAsString(testSnack2);
        testSnack3Json = objectMapper.writeValueAsString(testSnack3);
        testSnack1AlteredJson = objectMapper.writeValueAsString(testSnack1Altered);
        testSnack2AlteredJson = objectMapper.writeValueAsString(testSnack2Altered);
        testSnack3AlteredJson = objectMapper.writeValueAsString(testSnack3Altered);
    }

    @Test
    @Order(value = 1)
    public void testPostSnackOne() {
        ResponseEntity<String> postRespEntity = restTemplate.postForEntity(url, testSnack1, String.class);
        assertThat(postRespEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        ResponseEntity<String> getRespEntity = restTemplate.getForEntity(url, String.class);
        assertThat(getRespEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getRespEntity.getBody()).isEqualTo("[" + testSnack1Json + "]");
    }

    @Test
    @Order(value = 2)
    public void testPostSnackTwo() {
        ResponseEntity<String> postRespEntity = restTemplate.postForEntity(url, testSnack2, String.class);
        assertThat(postRespEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        ResponseEntity<String> getRespEntity = restTemplate.getForEntity(url, String.class);
        assertThat(getRespEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getRespEntity.getBody()).isEqualTo("[" + testSnack1Json + "," + testSnack2Json + "]");
    }

    @Test
    @Order(value = 3)
    public void testPostSnackThree() {
        ResponseEntity<String> postRespEntity = restTemplate.postForEntity(url, testSnack3, String.class);
        assertThat(postRespEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        ResponseEntity<String> getRespEntity = restTemplate.getForEntity(url, String.class);
        assertThat(getRespEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getRespEntity.getBody()).isEqualTo("[" + testSnack1Json + "," +
                testSnack2Json + ","+ testSnack3Json + "]");
    }

    @Test
    @Order(value = 4)
    public void testPostSnackAgain() {
        ResponseEntity<String> postRespEntity = restTemplate.postForEntity(url, testSnack3, String.class);
        assertThat(postRespEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    @Order(value = 5)
    public void testPutSnackOneOnlyPrice() {
        restTemplate.put(url + "/1?price=2.99", testSnack1, String.class);

        ResponseEntity<String> getRespEntity = restTemplate.getForEntity(url, String.class);
        assertThat(getRespEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getRespEntity.getBody()).isEqualTo("[" + testSnack2Json +
                ","+ testSnack3Json + "," + testSnack1AlteredJson + "]");
    }

    @Test
    @Order(value = 6)
    public void testPutSnackTwoOnlyStock() {
        restTemplate.put(url + "/2?stock=15", testSnack2, String.class);

        ResponseEntity<String> getRespEntity = restTemplate.getForEntity(url, String.class);
        assertThat(getRespEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getRespEntity.getBody()).isEqualTo("[" + testSnack3Json + "," + testSnack1AlteredJson +
                ","+ testSnack2AlteredJson + "]");
    }

    @Test
    @Order(value = 7)
    public void testPutSnackThreeBothPriceAndStock() {
        restTemplate.put(url + "/3?price=6.99&stock=20", testSnack3, String.class);

        ResponseEntity<String> getRespEntity = restTemplate.getForEntity(url, String.class);
        assertThat(getRespEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getRespEntity.getBody()).isEqualTo("[" + testSnack1AlteredJson +
                ","+ testSnack2AlteredJson + "," + testSnack3AlteredJson + "]");
    }

    @Test
    @Order(value = 8)
    public void testGetSnacksFilterHasNuts() {
        ResponseEntity<String> getRespEntity = restTemplate.getForEntity(url + "?filter=hasNuts", String.class);
        assertThat(getRespEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getRespEntity.getBody()).isEqualTo("[" + testSnack1AlteredJson + "]");
    }

    @Test
    @Order(value = 9)
    public void testGetSnacksFilterHasGluten() {
        ResponseEntity<String> getRespEntity = restTemplate.getForEntity(url + "?filter=hasGluten", String.class);
        assertThat(getRespEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getRespEntity.getBody()).isEqualTo("[" + testSnack1AlteredJson +
                "," + testSnack2AlteredJson + "]");
    }

    @Test
    @Order(value = 9)
    public void testGetSnacksFilterHasLactose() {
        ResponseEntity<String> getRespEntity = restTemplate.getForEntity(url + "?filter=hasLactose", String.class);
        assertThat(getRespEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getRespEntity.getBody()).isEqualTo("[" + testSnack3AlteredJson + "]");
    }

    @Test
    @Order(value = 10)
    public void testGetSnacksFilterHasAllergens() {
        ResponseEntity<String> getRespEntity = restTemplate.getForEntity(url + "?filter=hasAllergens", String.class);
        assertThat(getRespEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getRespEntity.getBody()).isEqualTo("[" + testSnack1AlteredJson +
                "," + testSnack2AlteredJson + "," + testSnack3AlteredJson + "]");
    }

    @Test
    @Order(value = 11)
    public void testGetSnacksFilterIsSweet() {
        ResponseEntity<String> getRespEntity = restTemplate.getForEntity(url + "?filter=isSweet", String.class);
        assertThat(getRespEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getRespEntity.getBody()).isEqualTo("[" + testSnack1AlteredJson + "]");
    }

    @Test
    @Order(value = 12)
    public void testGetSnacksFilterIsSalty() {
        ResponseEntity<String> getRespEntity = restTemplate.getForEntity(url + "?filter=isSalty", String.class);
        assertThat(getRespEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getRespEntity.getBody()).isEqualTo("[" + testSnack2AlteredJson + "]");
    }

    @Test
    @Order(value = 13)
    public void testGetSnacksFilterIsDrink() {
        ResponseEntity<String> getRespEntity = restTemplate.getForEntity(url + "?filter=isDrink", String.class);
        assertThat(getRespEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getRespEntity.getBody()).isEqualTo("[" + testSnack3AlteredJson + "]");
    }

    @Test
    @Order(value = 14)
    public void testGetSnacksSortNameAsc() {
        ResponseEntity<String> getRespEntity = restTemplate.getForEntity(url + "?sort=nameAsc", String.class);
        assertThat(getRespEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getRespEntity.getBody()).isEqualTo("[" + testSnack2AlteredJson +
                "," + testSnack1AlteredJson + "," + testSnack3AlteredJson + "]");
    }

    @Test
    @Order(value = 15)
    public void testGetSnacksSortNameDesc() {
        ResponseEntity<String> getRespEntity = restTemplate.getForEntity(url + "?sort=nameDesc", String.class);
        assertThat(getRespEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getRespEntity.getBody()).isEqualTo("[" + testSnack3AlteredJson +
                "," + testSnack1AlteredJson + "," + testSnack2AlteredJson + "]");
    }

    @Test
    @Order(value = 16)
    public void testGetSnacksSortPriceAsc() {
        ResponseEntity<String> getRespEntity = restTemplate.getForEntity(url + "?sort=priceAsc", String.class);
        assertThat(getRespEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getRespEntity.getBody()).isEqualTo("[" + testSnack2AlteredJson +
                "," + testSnack1AlteredJson + "," + testSnack3AlteredJson + "]");
    }

    @Test
    @Order(value = 17)
    public void testGetSnacksSortPriceDesc() {
        ResponseEntity<String> getRespEntity = restTemplate.getForEntity(url + "?sort=priceDesc", String.class);
        assertThat(getRespEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getRespEntity.getBody()).isEqualTo("[" + testSnack3AlteredJson +
                "," + testSnack1AlteredJson + "," + testSnack2AlteredJson + "]");
    }

    @Test
    @Order(value = 18)
    public void testGetSnacksSortStockAsc() {
        ResponseEntity<String> getRespEntity = restTemplate.getForEntity(url + "?sort=stockAsc", String.class);
        assertThat(getRespEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getRespEntity.getBody()).isEqualTo("[" + testSnack2AlteredJson +
                "," + testSnack3AlteredJson + "," + testSnack1AlteredJson + "]");
    }

    @Test
    @Order(value = 19)
    public void testGetSnacksSortStockDesc() {
        ResponseEntity<String> getRespEntity = restTemplate.getForEntity(url + "?sort=stockDesc", String.class);
        assertThat(getRespEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getRespEntity.getBody()).isEqualTo("[" + testSnack1AlteredJson +
                "," + testSnack3AlteredJson + "," + testSnack2AlteredJson + "]");
    }

    @Test
    @Order(value = 20)
    public void testDeleteSnack() {
        restTemplate.delete(url + "/2");

        ResponseEntity<String> getRespEntity = restTemplate.getForEntity(url, String.class);
        assertThat(getRespEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getRespEntity.getBody()).isEqualTo("[" + testSnack1AlteredJson +
                "," + testSnack3AlteredJson + "]");
    }
}

