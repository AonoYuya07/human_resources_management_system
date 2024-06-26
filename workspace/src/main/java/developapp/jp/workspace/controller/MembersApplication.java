package developapp.jp.workspace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import developapp.jp.workspace.dataAccessObject.request.RegistMemberRequest;
import org.springframework.ui.Model;
import developapp.jp.workspace.service.MembersService;
import developapp.jp.workspace.dataAccessObject.entity.Members;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
			BindingResult result, Model model, RedirectAttributes redirectAttributes) {
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
			redirectAttributes.addFlashAttribute("infoMessage", "登録が完了しました。");
			return "redirect:/member/list"; // リダイレクト先
		}
	}

	@GetMapping("/member/delete/{id}")
	public String deleteMember(@PathVariable("id") int id, Model model) {
		// IDをもとに削除処理を行う
		membersService.deleteMember(id);
		// 削除処理後、完了ページまたは別のページにリダイレクト
		model.addAttribute("infoMessage", "削除が完了しました。");
		// 一覧表示用のデータを取得
		Iterable<Members> members = membersService.getAllMembers();
		// 一覧表示用のデータをModelに設定
		model.addAttribute("members", members);
		return "list"; // 登録完了後に表示するビュー名
	}

	@GetMapping("/member/edit/{id}")
	public String editMember(@PathVariable("id") int id, RegistMemberRequest form, Model model) {
		// 編集画面に遷移するために、IDから対象データを取得してフォームに設定する処理を行う
		Members member = membersService.getMember(id);
		model.addAttribute("registMemberRequest", form);
		model.addAttribute("id", id);
		// フォームにデータを設定
		form.setUserName(member.getName());
		form.setGender(member.getGender());
		form.setPlatform(member.getPlatform());
		form.setUrl(member.getUrl());
		form.setAge(member.getAge());
		return "edit"; // 登録完了後に表示するビュー名
	}

	@PostMapping("/member/edit/{id}/complete")
	public String updateMember(@ModelAttribute @Validated RegistMemberRequest registMemberRequest,
			BindingResult result, Model model, @PathVariable("id") int id, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			// バリデーションエラーがある場合、フォームを再表示
			model.addAttribute("registMemberRequest", registMemberRequest);
			model.addAttribute("id", id);
			return "edit"; // エラーがある場合はフォームを再表示するビュー名
		} else {
			// バリデーションエラーがない場合、更新処理などを行う
			// Membersエンティティにリクエストの値をセットし、MembersServiceのupdateMemberメソッドを呼び出す
			Members member = new Members();
			member.setId(id);
			member.setName(registMemberRequest.getUserName());
			member.setAge(registMemberRequest.getAge());
			member.setGender(registMemberRequest.getGender());
			member.setPlatform(registMemberRequest.getPlatform());
			member.setUrl(registMemberRequest.getUrl());
			membersService.updateMember(member);
			// 登録処理後、完了ページまたは別のページにリダイレクト
			redirectAttributes.addFlashAttribute("infoMessage", "更新が完了しました。");
			return "redirect:/member/list"; // リダイレクト先
		}
	}
}
