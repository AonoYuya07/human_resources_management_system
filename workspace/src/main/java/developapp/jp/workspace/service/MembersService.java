package developapp.jp.workspace.service;

import developapp.jp.workspace.dataAccessObject.entity.Members;
import developapp.jp.workspace.dataAccessObject.repository.MembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MembersService {

    @Autowired
    private MembersRepository membersRepository;

    // ユーザーを作成する
    @Transactional
    public Members createMember(Members member) {
        try {
            // ここでパスワードはエンティティにセットする際にハッシュ化されます
            return membersRepository.save(member);
        } catch (Exception e) {
            // ここでトランザクションは自動的にロールバックされます
            throw new RuntimeException("ユーザーの作成に失敗しました。", e);
        }
    }
}
