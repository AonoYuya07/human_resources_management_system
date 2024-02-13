package developapp.jp.workspace.restController;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RestController;
import developapp.jp.workspace.dataAccessObject.request.LoginRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class LoginApiController {

    public static void main(String[] args) {
        SpringApplication.run(LoginApiController.class, args);
    }

    @PostMapping("/api/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        // 以下、処理を仮置き
        // workspace/src/main/java/developapp/jp/workspace/service/ApiCustomAuthenticationProvider.java
        //上記で実装した認証処理を呼び出すようにしたい
        boolean authenticationSuccess = true; // 実際の認証ロジックに置き換える必要あり

        if (authenticationSuccess) {
            // 認証成功
            return ResponseEntity.ok().body("Authentication successful!");
        } else {
            // 認証失敗
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed!");
        }
    }
}
