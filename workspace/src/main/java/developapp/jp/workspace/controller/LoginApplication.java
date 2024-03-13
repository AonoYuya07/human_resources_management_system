package developapp.jp.workspace.controller;

import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginApplication {
    @GetMapping("/login")
    public String login() {
        System.out.println("AuthController.login()");
        return "login";
    }

    @GetMapping("/login/dummy")
    public String loginDummy() {
        System.out.println("AuthController.login()");
        return "login";
    }

    // @GetMapping("/login")
    // public String showLoginForm(HttpServletRequest request, HttpServletResponse
    // response) throws IOException {
    //// // セッションを開始
    //// HttpSession session = request.getSession();
    //// //ログイン済みの場合はメニュー画面にリダイレクトさせ、未ログインの場合はログイン画面を表示させる
    //// if (session.getAttribute("userId") != null) {
    //// response.sendRedirect("/user/list");
    //// }
    // System.out.println("AuthController.login()");
    // return "login";
    // }

    // @GetMapping("/login/auth")
    // public String login(HttpServletRequest request, HttpServletResponse response)
    // throws IOException {
    // // リクエストからユーザーIDとパスワードを取得
    // String userId = request.getParameter("userId");
    // String password = request.getParameter("password");
    // //ハッシュ化されたパスワードの判定に使用する
    // PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    // // データベースからユーザーIDとパスワードを取得
    // String dbUserId = "";
    // String dbPassword = "";
    // String sql = "SELECT userId, password FROM Users WHERE userId = ?";
    // //自作のDB接続クラスをインスタンス化
    // DatabaseConnector databaseConnector = new DatabaseConnector();
    // //外部ファイルに作成した処理でDBへの接続を行う。またSQLインジェクション対策にPreparedStatementを使用
    // try (Connection connection = databaseConnector.connect()) {
    // // トランザクションの開始
    // connection.setAutoCommit(false);
    // try {
    // //トランザクションを行う場合はこのようにエラーハンドリングをネストさせる
    // try (PreparedStatement preparedStatement1 = connection.prepareStatement(sql))
    // {
    // // ユーザーIDをクエリにセット
    // preparedStatement1.setString(1, userId);
    // // クエリを実行
    // ResultSet resultSet = preparedStatement1.executeQuery();
    // // ユーザーIDとパスワードを取得
    // if (resultSet.next()) {
    // dbUserId = resultSet.getString("userId");
    // dbPassword = resultSet.getString("password");
    // }
    // }
    // //2つ目のクエリを実行する場合は上記のようなものをいかに続けて記載。
    // // トランザクションのコミット
    // connection.commit();
    // } catch (SQLException e) {
    // // トランザクションのロールバック
    // connection.rollback();
    // throw e;
    // }
    // } catch (SQLException e) {
    // // エラー処理
    // e.printStackTrace();
    // }
    // // ユーザーIDとパスワードが一致した場合はログイン成功
    // //パスワードはバッシュ化したものと認証を行う
    // //passwordEncoder.matches(平文パスワード, ハッシュ化されたパスワード)
    // String digest = passwordEncoder.encode(password);
    // Logger logger = LoggerFactory.getLogger(LoginApplication.class);
    // logger.warn("ハッシュ値 = " + digest);
    // //変数をデバッグ
    // logger.warn("userId = " + userId);
    // logger.warn("dbUserId = " + dbUserId);
    //
    // if (userId.equals(dbUserId) && passwordEncoder.matches(password, dbPassword))
    // {
    // // セッションを開始
    // HttpSession session = request.getSession();
    // // セッションにユーザーIDをセット
    // session.setAttribute("userId", userId);
    // // メニュー画面にリダイレクト
    // response.sendRedirect("/user/list");
    // }
    // // 認証失敗の場合はログイン画面にリダイレクト
    // return "login";
    // }
}
