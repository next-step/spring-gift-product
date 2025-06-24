package gift;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductApiIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void integrationTest() throws Exception {
        // create
        String json = """
                { "name":"아메리카노", "price":"3000" }""";
        assertCreateSuccess(json, 1L, "아메리카노", 3000L);

        json = """
                { "name":"카페라떼", "price":"4500" }""";
        assertCreateSuccess(json, 2L, "카페라떼", 4500L);

        json = """
                { "price":"1000"  }""";
        assertCreateFail(json);

        json = """
                { "name":"아메리카노", "price":"-1000"  }""";
        assertCreateFail(json);

        // get
        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("아메리카노"))
                .andExpect(jsonPath("$.price").value("3000"));

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("아메리카노"))
                .andExpect(jsonPath("$[1].name").value("카페라떼"));

        mockMvc.perform(get("/products/3"))
                .andExpect(status().isBadRequest());

        // update
        json = """
                { "name":"아이스 아메리카노" }
                """;
        assertUpdateSuccess(1L, json, "아이스 아메리카노", 3000L);

        json = """
                { "name":"딸기라떼", "price":"5000" }
                """;
        assertUpdateSuccess(2L, json, "딸기라떼", 5000L);

        json = """
                { "name":"", "price":"1000" }
                """;
        assertUpdateFail(1L, json);

        // delete
        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isNoContent());
        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isBadRequest());
    }

    void assertCreateSuccess(String json, Long expectedId, String expectedName, Long expectedPrice) throws Exception {
        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(expectedId))
                .andExpect(jsonPath("$.name").value(expectedName))
                .andExpect(jsonPath("$.price").value(expectedPrice));
    }

    void assertCreateFail(String json) throws Exception {
        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    void assertUpdateSuccess(Long id, String json, String expectedName, Long expectedPrice) throws Exception {
        mockMvc.perform(patch("/products/"+id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(expectedName))
                .andExpect(jsonPath("$.price").value(expectedPrice));
    }

    void assertUpdateFail(Long id, String json) throws Exception {
        mockMvc.perform(patch("/products/"+id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
}
