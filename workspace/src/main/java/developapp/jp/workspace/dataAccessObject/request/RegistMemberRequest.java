package developapp.jp.workspace.dataAccessObject.request;

import javax.validation.constraints.*;

import lombok.Data;

@Data
public class RegistMemberRequest {
    @NotEmpty
    private String userName;

    @NotEmpty
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

    @AssertTrue
    //検証用メソッド
    public boolean isSameValueInput() {
        System.out.println("性別：" + this.gender);
        return this.gender.equals("男性");
    }
}
