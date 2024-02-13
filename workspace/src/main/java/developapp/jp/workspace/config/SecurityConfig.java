package developapp.jp.workspace.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import developapp.jp.workspace.service.CustomAuthenticationProvider;
import developapp.jp.workspace.service.ApiCustomAuthenticationProvider;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	// CustomAuthenticationProvider Beanをこのクラスに注入する
	private final CustomAuthenticationProvider customAuthenticationProvider;

	// API用の認証処理クラス
	private final ApiCustomAuthenticationProvider apiCustomAuthenticationProvider;

	public SecurityConfig(CustomAuthenticationProvider customAuthenticationProvider,
			ApiCustomAuthenticationProvider apiCustomAuthenticationProvider) {
		this.customAuthenticationProvider = customAuthenticationProvider;
		this.apiCustomAuthenticationProvider = apiCustomAuthenticationProvider;
	}

	// API用のセキュリティ設定
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// 新しいバージョンの処理に変更すること
		/* TODO */
		// CSRFは有効にする
		// ログインエンドポイントは「/api/login」
		// ログイン成功時にはトークンを返す
		// ログインエンドポイント以外は認証が必要
		// ログインエンドポイントは認証済みの場合はアクセス不可
		// 認証には作成した「apiCustomAuthenticationProvider」を使用したい
		// ユーザ情報はDBから取得する（「Users」テーブル）
		// CORSは有効にする
		/* --------------- */
		http.authenticationProvider(apiCustomAuthenticationProvider)
				.authorizeHttpRequests(authz -> authz
						.requestMatchers("/api/login").permitAll() // API用のログインエンドポイント
						// 静的リソースへのアクセスは全て許可
						.requestMatchers(PathRequest.toStaticResources().atCommonLocations())
						.permitAll()
						.requestMatchers("/css/**", "/images/**", "/js/**", "/storage/**")
						.permitAll()
						.anyRequest().authenticated() // それ以外は認証が必要
				);

		return http.build();
	}

	// Web用のセキュリティ設定
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authenticationProvider(customAuthenticationProvider) // 認証処理を行うクラスを指定
				.formLogin(login -> login
						.loginPage("/login").permitAll() // ログインページのURLを指定
						.defaultSuccessUrl("/user/list") // ログイン認証成功後の遷移先URLを指定
						.usernameParameter("user") // ログインページで指定したユーザ名を入力する項目を指定
						.passwordParameter("password") // ログインページで指定したパスワードを入力する項目を指定
				)
				.logout(logout -> logout
						.logoutSuccessUrl("/login?logout") // ログアウトが成功した際の遷移先
						.invalidateHttpSession(true) // ログアウト時にHTTPセッションを無効にする
				)
				// 認可の設定
				.authorizeHttpRequests(authz -> authz
						// 静的リソースへのアクセスは全て許可
						.requestMatchers(PathRequest.toStaticResources().atCommonLocations())
						.permitAll()
						.requestMatchers("/css/**", "/images/**", "/js/**", "/storage/**",
								"/login/dummy", "/api/login")
						.permitAll()
						.requestMatchers("/admin/**").hasRole("ADMIN") // /admin/以下はADMINロールのみアクセス可能
						.anyRequest().authenticated() // それ以外は認証が必要
				);
		return http.build();
	}

}
