package developapp.jp.workspace.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletRequest; // ライブラリの役割: リクエストを受け取る
import javax.servlet.http.HttpServletResponse; // ライブラリの役割: レスポンスを返す
import javax.servlet.http.HttpSession; // ライブラリの役割: セッション管理
import java.io.IOException; // ライブラリの役割: 例外処理
import java.sql.Connection; // ライブラリの役割: データベース接続
import java.sql.DriverManager; // ライブラリの役割: データベース接続

@SpringBootApplication
@Controller
public class LoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginApplication.class, args);
    }

    @GetMapping("/")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/login/auth")
    public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // リクエストからユーザーIDとパスワードを取得
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        // データベースに登録済みのユーザーIDとパスワードを取得
    }
}
