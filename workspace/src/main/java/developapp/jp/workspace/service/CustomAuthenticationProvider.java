package developapp.jp.workspace.service;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import java.sql.Connection; // ライブラリの役割: データベース接続
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.context.annotation.Bean;

// @Componentをつけることで、このクラスがSpringのコンテナにBeanとして登録される
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private DatabaseConnector databaseConnector;

    public CustomAuthenticationProvider(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // ブラウザから入力したユーザ名・パスワードを取得
        String userId = authentication.getName();
        String password = (String) authentication.getCredentials();

        String dbUserId = null;
        String dbPassword = null;

        // DBからユーザー情報を取得
        String sql = "SELECT userId, password FROM Users WHERE userId = ?";
        // //自作のDB接続クラスをインスタンス化
        // DatabaseConnector databaseConnector = new DatabaseConnector();
        // 外部ファイルに作成した処理でDBへの接続を行う。またSQLインジェクション対策にPreparedStatementを使用
        try (Connection connection = this.databaseConnector.connect()) {
            // トランザクションの開始
            connection.setAutoCommit(false);
            try {
                // トランザクションを行う場合はこのようにエラーハンドリングをネストさせる
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
                // 2つ目のクエリを実行する場合は上記のようなものをいかに続けて記載。
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
        // String encodedPassword = this.passwordEncoder().encode(password);
        System.out.println("passwordEncoder().matches:" + passwordEncoder().matches(password, dbPassword));
        System.out.println("password: " + password);
        System.out.println("dbPassword: " + dbPassword);
        // this.passwordEncoder().matchesは第一引数に平文のパスワードを入れてマッチするか判定する
        if (userId.equals(dbUserId) && this.passwordEncoder().matches(password, dbPassword)) {
            // 認証成功時は、認証トークン(ユーザ名、パスワード、権限)を作成
            return new UsernamePasswordAuthenticationToken(userId, password, null);
        } else {
            // 認証失敗は、エラーを返す
            throw new BadCredentialsException("Authentication failed");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // authentication(認証方式)がUsernamePasswordAuthenticationToken.class(ユーザー名とパスワード認証)か判定
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
