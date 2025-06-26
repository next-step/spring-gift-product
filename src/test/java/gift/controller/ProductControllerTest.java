package gift.controller;

import gift.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    private final String baseUrl = "/api/products";

    private final String[] name = new String[]{"아메리카노", "카페라떼", "모카"};
    private final String[] price = new String[]{"3000", "4000", "5000"};
    private final String[] imageUrl = new String[]{"http://americano", "http://cafelatte", "http://moka"};
    private final String[] location = new String[3];

    @Test
    void createTest() throws Exception {
        location[0] = sendPost(name[0], price[0], imageUrl[0]);

        mockMvc.perform(get(location[0]))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name[0]))
                .andExpect(jsonPath("$.price").value(price[0]))
                .andExpect(jsonPath("$.imageUrl").value(imageUrl[0]));
    }

    @Test
    void getTest() throws Exception {
        prepareTestData();

        for (int i = 0; i < 3; i++) {
            sendGet(location[i], name[i], price[i], imageUrl[i]);
        }

        mockMvc.perform(get(base()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3)) // 여기서 에러
                .andExpect(jsonPath("$[0].name").value("아메리카노"))
                .andExpect(jsonPath("$[1].name").value("카페라떼"))
                .andExpect(jsonPath("$[2].name").value("모카"));
    }

    @Test
    void updateTest() throws Exception {
        prepareTestData();

        mockMvc.perform(put(location[0])
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(name[0], price[1], imageUrl[2])))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name[0]))
                .andExpect(jsonPath("$.price").value(price[1]))
                .andExpect(jsonPath("$.imageUrl").value(imageUrl[2]));

        sendGet(location[0], name[0], price[1], imageUrl[2]);
    }

    @Test
    void deleteTest() throws Exception {
        prepareTestData();

        mockMvc.perform(delete(location[0]))
                .andExpect(status().isNoContent());

        mockMvc.perform(get(location[0]))
                .andExpect(status().isNotFound());
    }

    private String sendPost(String name, String price, String imageUrl) throws Exception {
        return mockMvc.perform(post(base())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json(name, price, imageUrl)))
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andReturn()
                .getResponse()
                .getHeader(HttpHeaders.LOCATION);
    }

    private void sendGet(String location, String name, String price, String imageUrl) throws Exception {
        mockMvc.perform(get(location))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.price").value(price))
                .andExpect(jsonPath("$.imageUrl").value(imageUrl));
    }

    private void prepareTestData() throws Exception {
        productRepository.clear();
        for (int i = 0; i < 3; i++) {
            location[i] = sendPost(name[i], price[i], imageUrl[i]);
        }
    }

    private String base() {
        return baseUrl;
    }

    private String entity(Long id) {
        return baseUrl + "/" + id;
    }

    private String json(String name, String price, String imageUrl) {
        name = name == null ? "" : name;
        price = price == null ? "" : price;
        imageUrl = imageUrl == null ? "" : imageUrl;
        return String.format("{\"name\":\"%s\",\"price\":\"%s\",\"imageUrl\":\"%s\"}", name, price, imageUrl);
    }
}
