package developapp.jp.workspace.dataAccessObject.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegistMemberRequest {
    @NotEmpty(message = "ユーザ名は必須入力")
    private String userName;

    @NotNull(message = "年齢は必須入力")
    @Positive(message = "年齢は正の数を入力してください")
    private int age;

    @NotEmpty(message = "性別は必須入力")
    private String gender;

    @NotEmpty(message = "プラットフォームは必須入力")
    private String platform;

    @NotEmpty(message = "URLは必須入力")
    @Pattern(regexp = "^(http://www\\.|https://www\\.|http://|https://)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(/.*)?$",
            message = "無効なURL形式です")
    private String url;

    // ゲッターとセッター
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
