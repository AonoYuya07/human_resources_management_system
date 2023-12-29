package developapp.jp.workspace.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession; // ライブラリの役割: セッション管理

@SpringBootApplication
@Controller
public class UsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersApplication.class, args);
	}

	@GetMapping("/user/list")
	public String index() {
		return "index";
	}
}
