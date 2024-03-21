package developapp.jp.workspace.controller;

// import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import developapp.jp.workspace.dataAccessObject.request.RegistMemberRequest;
import org.springframework.ui.Model;
// import jakarta.validation.Valid;
// import org.springframework.validation.Validator;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
import developapp.jp.workspace.service.MembersService;
import developapp.jp.workspace.dataAccessObject.entity.Members;
// import developapp.jp.workspace.dataAccessObject.repository.MembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
// import developapp.jp.workspace.service.MembersService;

@Controller
public class MembersApplication {

	@Autowired
	private MembersService membersService;

	// 一覧
	@GetMapping("/member/list")
	public String list(Model model) {
		// 一覧表示用のデータを取得
		Iterable<Members> members = membersService.getAllMembers();
		// 一覧表示用のデータをModelに設定
		model.addAttribute("members", members);
		return "list";
	}

	// フォームの初期表示用
	@GetMapping("/member/regist")
	public String showForm(RegistMemberRequest form, Model model) {
		model.addAttribute("registMemberRequest", form);
		form.setAge(20); // 年齢の初期値を20に設定
		return "regist";
	}

	// フォーム送信時の処理
	@PostMapping("/member/complete")
	public String registMember(
			@ModelAttribute @Validated RegistMemberRequest registMemberRequest,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			// バリデーションエラーがある場合、フォームを再表示
			model.addAttribute("registMemberRequest", registMemberRequest);
			return "regist"; // エラーがある場合はフォームを再表示するビュー名
		} else {
			// バリデーションエラーがない場合、登録処理などを行う
			// Membersエンティティにリクエストの値をセットし、MembersServiceのcreateMemberメソッドを呼び出す
			Members member = new Members();
			member.setName(registMemberRequest.getUserName());
			member.setAge(registMemberRequest.getAge());
			member.setGender(registMemberRequest.getGender());
			member.setPlatform(registMemberRequest.getPlatform());
			member.setUrl(registMemberRequest.getUrl());
			membersService.createMember(member);
			// 登録処理後、完了ページまたは別のページにリダイレクト
			model.addAttribute("infoMessage", "登録が完了しました。");
			// 一覧表示用のデータを取得
			Iterable<Members> members = membersService.getAllMembers();
			// 一覧表示用のデータをModelに設定
			model.addAttribute("members", members);
			return "list"; // 登録完了後に表示するビュー名
		}
	}
}
