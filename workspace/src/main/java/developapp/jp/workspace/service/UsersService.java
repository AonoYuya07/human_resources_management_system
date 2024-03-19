package developapp.jp.workspace.service;

import developapp.jp.workspace.dataAccessObject.entity.Users;
import developapp.jp.workspace.dataAccessObject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsersService {

    @Autowired
    private UserRepository userRepository;

    // ユーザーを作成する
    @Transactional
    public Users createUser(Users user) {
        try {
            // ここでパスワードはエンティティにセットする際にハッシュ化されます
            return userRepository.save(user);
        } catch (Exception e) {
            // ここでトランザクションは自動的にロールバックされます
            throw new RuntimeException("ユーザーの作成に失敗しました。", e);
        }
    }
}
