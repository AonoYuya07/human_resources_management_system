package developapp.jp.workspace.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers("/webjars/**", "/css/**", "/").permitAll() // 静的リソースとルートへのアクセス許可
                .anyRequest().authenticated() // その他のリクエストは認証が必要
                .and()
                .formLogin()
                .loginProcessingUrl("/login/auth") // ログイン処理のパス
                .loginPage("/") // ログインページ
                .failureUrl("/login?error") // ログインエラー時の遷移先
                .defaultSuccessUrl("/user/list", true) // ログイン成功時の遷移先
                .usernameParameter("userId") // ユーザーIDパラメーター
                .passwordParameter("password") // パスワードパラメーター
                .and()
                .logout()
                .logoutSuccessUrl("/") // ログアウト時の遷移先
                .and()
                .csrf().disable(); // CSRF保護を無効化

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
