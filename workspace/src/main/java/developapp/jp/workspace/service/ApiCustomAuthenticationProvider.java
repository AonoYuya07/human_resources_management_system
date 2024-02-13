package developapp.jp.workspace.service;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import developapp.jp.workspace.dataAccessObject.entity.Users;
import developapp.jp.workspace.dataAccessObject.repository.UserRepository;
import java.util.Optional;

// @Componentをつけることで、このクラスがSpringのコンテナにBeanとして登録される
@Component
public class ApiCustomAuthenticationProvider implements AuthenticationProvider {

    private UserRepository repository;

    public ApiCustomAuthenticationProvider(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 入力したユーザ名・パスワードを取得
        String userId = authentication.getName();
        String password = (String) authentication.getCredentials();
        // userIdをキーにDBからユーザ情報を取得
        Optional<Users> user = repository.findByUserId(userId);
        // ユーザ情報が取得できなかった場合
        if (user == null) {
            throw new BadCredentialsException("1000");
        }
        // DBから取得したパスワードと入力したパスワードを比較
        if (!new BCryptPasswordEncoder().matches(password, user.get().getPassword())) {
            throw new BadCredentialsException("1000");
        }
        // ユーザ情報を取得できた場合は認証成功として、ユーザ情報を設定
        return new UsernamePasswordAuthenticationToken(userId, password);
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
