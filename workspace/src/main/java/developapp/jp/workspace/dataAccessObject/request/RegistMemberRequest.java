package developapp.jp.workspace.dataAccessObject.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegistMemberRequest {
    @NotEmpty(message = "ユーザ名は必須入力")
    private String userName;

    @NotEmpty(message = "性別は必須入力")
    private String gender;

    // ゲッターとセッター
    public String getUserName() {
        System.out.println("ユーザ名(get)：" + this.userName);
        return userName;
    }

    public void setUserName(String userName) {
        System.out.println("ユーザ名(set)：" + userName);
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
