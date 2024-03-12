package developapp.jp.workspace.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import developapp.jp.workspace.dataAccessObject.request.RegistMemberRequest;
import org.springframework.ui.Model;
import javax.validation.Valid;
import org.springframework.validation.Validator;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Controller
@SpringBootApplication
public class UsersApplication {
	public static void main(String[] args) {
		SpringApplication.run(UsersApplication.class, args);
	}

	// フォームの初期表示用
	@GetMapping("/user/list")
	public String showForm(RegistMemberRequest form, Model model) {
		model.addAttribute("registMemberRequest", form);
		form.setUserName("山田太郎");
		return "index"; // フォームを表示するビュー名（register.htmlなど）
	}

	// フォーム送信時の処理
	@PostMapping("/user/complete")
	public String registMember(
			@ModelAttribute @Validated RegistMemberRequest registMemberRequest,
			BindingResult result, Model model) {
		System.out.println("bindingResult：" + result);
		System.out.println("バリデーションエラーがあるかどうか：" + result.hasErrors());
		System.out.println("formの内容：" + registMemberRequest);
		System.out.println("modelの内容：" + model);
		System.out.println("getUserName()の内容：" + registMemberRequest.getUserName());
		System.out.println("Errors: " + result.getAllErrors());
		if (result.hasErrors()) {
			// バリデーションエラーがある場合、フォームを再表示
			model.addAttribute("registMemberRequest", registMemberRequest);
			return "index"; // エラーがある場合はフォームを再表示するビュー名
		} else {
			// バリデーションエラーがない場合、登録処理などを行う
			// 登録処理後、完了ページまたは別のページにリダイレクト
			model.addAttribute("infoMessage", "登録が完了しました。");
			return "index"; // 登録完了後に表示するビュー名
		}
	}
}
