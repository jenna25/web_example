package cn.cisdigital.bitmagic.web;

import cn.cisdigital.bitmagic.web.common.profile.Environment;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;
import java.util.Objects;


/**
 * 基础测试类
 * <pre>{@code
 * @Test
 * public void xxTest() {
 *   Map<String, String> param = new HashMap<>();
 *   param.put("pID", pId);
 *   ResponseEntity<String> response = testRestTemplate.exchange(
 *     "/xx/xx?pID={pID}",
 *     HttpMethod.GET,
 *     new HttpEntity<>(tokenWith(bd1())),
 *     String.class,
 *     param
 *   );
 *   Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
 *   System.out.println(response.getBody());
 * }
 * }<pre>
 *
 * @author mno
 * @date 2020/07/02 17:29
 */
@Slf4j
@EnableAsync
@RunWith(SpringRunner.class)
@ActiveProfiles(Environment.Development.NAME)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class OAuthLocalhostTest {

    @Resource
    protected TestRestTemplate testRestTemplate;

    @Resource
    protected ObjectMapper objectMapper;

    private volatile TokenInfo token;

    private static final String OAUTH_URI = "https://auth.segma.tech/oauth/token";

    @Autowired
    protected MockMvc mockMvc;

    @Test
    public void tokenTest() {
        System.out.println(bd1());
    }

    public byte[] content(Object o) throws JsonProcessingException {
        return objectMapper.writeValueAsBytes(o);
    }

    /**
     * 获取请求头
     *
     * @return 请求头，并带上认证信息
     */
    public HttpHeaders headerWithToken() {
        return tokenWith(getToken());
    }

    /**
     * 获取请求头
     *
     * @return 请求头，并带上认证信息
     */
    public HttpHeaders tokenWith(TokenInfo token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "bearer " + token.getAccessToken());
        return headers;
    }


    /**
     * 获取segma的token
     *
     * @return token
     */
    public TokenInfo getToken() {
        return bd1();
    }

    public TokenInfo bd1() {
        return getToken(SegmaAuthRequest.of("bd1@segma.tech", "325a2cc052914ceeb8c19016c091d2ac"));
    }

    public TokenInfo bd2() {
        return getToken(SegmaAuthRequest.of("bd2@segma.tech", "325a2cc052914ceeb8c19016c091d2ac"));
    }

    protected TokenInfo getToken(SegmaAuthRequest auth) {
        if (this.token == null) {
            synchronized (OAuthLocalhostTest.class) {
                if (this.token == null) {
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

                    ResponseEntity<TokenInfo> response = this.testRestTemplate.exchange(OAUTH_URI,
                            HttpMethod.POST, new HttpEntity<>(auth.toBody(), headers), TokenInfo.class);
                    this.token = Objects.requireNonNull(response.getBody());
                }
            }

        }
        return this.token;
    }

    /**
     * {
     * "access_token": "63a29229-005b-4279-b1e2-87b129c4aa25",
     * "token_type": "bearer",
     * "refresh_token": "116a9c69-3a5d-43da-9431-8c3ac38a4666",
     * "expires_in": 604751,
     * "scope": "read write"
     * }
     */
    @Getter
    @ToString
    public static class TokenInfo {

        @JsonProperty(value = "access_token", access = JsonProperty.Access.WRITE_ONLY)
        private String accessToken;

        @JsonProperty(value = "token_type", access = JsonProperty.Access.WRITE_ONLY)
        private String tokenType;

        @JsonProperty(value = "refresh_token", access = JsonProperty.Access.WRITE_ONLY)
        private String refreshToken;

        @JsonProperty(value = "expires_in", access = JsonProperty.Access.WRITE_ONLY)
        private long expiresIn;

        @JsonProperty(value = "scope", access = JsonProperty.Access.WRITE_ONLY)
        private String scope;

    }

    /**
     *
     */
    @Getter
    @ToString
    public static class SegmaAuthRequest {

        private final String clientId;
        private final String clientSecret;
        private final String grantType;
        private final String username;
        private final String password;

        public SegmaAuthRequest(String clientId, String clientSecret, String grantType, String username, String password) {
            this.clientId = clientId;
            this.clientSecret = clientSecret;
            this.grantType = grantType;
            this.username = username;
            this.password = password;
        }

        public static SegmaAuthRequest of(String clientId, String clientSecret, String username, String password) {
            return of(clientId, clientSecret, "password", username, password);
        }

        public static SegmaAuthRequest of(String account, String password) {
            return of("segma_data_assets", "Kos4Qg6opZcxGDQa", account, password);
        }

        public static SegmaAuthRequest of(String clientId, String clientSecret, String grantType, String username, String password) {
            return new SegmaAuthRequest(clientId, clientSecret, grantType, username, password);
        }

        public String toBody() {
            return String.format("username=%s&password=%s&grant_type=%s&client_id=%s&client_secret=%s",
                    username,
                    password,
                    grantType,
                    clientId,
                    clientSecret);
        }
    }
}
