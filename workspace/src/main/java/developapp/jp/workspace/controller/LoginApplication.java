package developapp.jp.workspace.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletRequest; // ライブラリの役割: リクエストを受け取る
import javax.servlet.http.HttpServletResponse; // ライブラリの役割: レスポンスを返す
import javax.servlet.http.HttpSession; // ライブラリの役割: セッション管理
import java.io.IOException; // ライブラリの役割: 例外処理
import java.sql.Connection; // ライブラリの役割: データベース接続
import java.sql.DriverManager; // ライブラリの役割: データベース接続
import java.sql.ResultSet;
import java.sql.SQLException;
import developapp.jp.workspace.service.DatabaseConnector; //自作のDB接続クラス
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.PreparedStatement;

@SpringBootApplication
@Controller
public class LoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginApplication.class, args);
    }

    @GetMapping("/")
    public String showLoginForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // セッションを開始
        HttpSession session = request.getSession();
        //ログイン済みの場合はメニュー画面にリダイレクトさせ、未ログインの場合はログイン画面を表示させる
        if (session.getAttribute("userId") != null) {
            response.sendRedirect("/user/list");
        }
        return "login";
    }

    @GetMapping("/login/auth")
    public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // リクエストからユーザーIDとパスワードを取得
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        //ハッシュ化されたパスワードの判定に使用する
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // データベースからユーザーIDとパスワードを取得
        String dbUserId = "";
        String dbPassword = "";
        String sql = "SELECT userId, password FROM Users WHERE userId = ?";
        //自作のDB接続クラスをインスタンス化
        DatabaseConnector databaseConnector = new DatabaseConnector();
        //外部ファイルに作成した処理でDBへの接続を行う。またSQLインジェクション対策にPreparedStatementを使用
        try (Connection connection = databaseConnector.connect()) {
            // トランザクションの開始
            connection.setAutoCommit(false);
            try {
                //トランザクションを行う場合はこのようにエラーハンドリングをネストさせる
                try (PreparedStatement preparedStatement1 = connection.prepareStatement(sql)) {
                    // ユーザーIDをクエリにセット
                    preparedStatement1.setString(1, userId);
                    // クエリを実行
                    ResultSet resultSet = preparedStatement1.executeQuery();
                    // ユーザーIDとパスワードを取得
                    if (resultSet.next()) {
                        dbUserId = resultSet.getString("userId");
                        dbPassword = resultSet.getString("password");
                    }
                }
                //2つ目のクエリを実行する場合は上記のようなものをいかに続けて記載。
                // トランザクションのコミット
                connection.commit();
            } catch (SQLException e) {
                // トランザクションのロールバック
                connection.rollback();
                throw e;
            }
        } catch (SQLException e) {
            // エラー処理
            e.printStackTrace();
        }
        // ユーザーIDとパスワードが一致した場合はログイン成功
        //パスワードはバッシュ化したものと認証を行う
        //passwordEncoder.matches(平文パスワード, ハッシュ化されたパスワード)
        String digest = passwordEncoder.encode(password);
        Logger logger = LoggerFactory.getLogger(LoginApplication.class);
        logger.warn("ハッシュ値 = " + digest);
        //変数をデバッグ
        logger.warn("userId = " + userId);
        logger.warn("dbUserId = " + dbUserId);

        if (userId.equals(dbUserId) && passwordEncoder.matches(password, dbPassword)) {
            // セッションを開始
            HttpSession session = request.getSession();
            // セッションにユーザーIDをセット
            session.setAttribute("userId", userId);
            // メニュー画面にリダイレクト
            response.sendRedirect("/user/list");
        }
        // 認証失敗の場合はログイン画面にリダイレクト
        return "login";
    }
}
