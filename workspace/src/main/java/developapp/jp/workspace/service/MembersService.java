package developapp.jp.workspace.service;

import developapp.jp.workspace.dataAccessObject.entity.Members;
import developapp.jp.workspace.dataAccessObject.repository.MembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.sql.Timestamp;

@Service
public class MembersService {

    @Autowired
    private MembersRepository membersRepository;

    // ユーザーを作成する
    @Transactional
    public Members createMember(Members member) {
        try {
            Date date = new Date();
            long time = date.getTime();
            Timestamp nowTs = new Timestamp(time);
            member.setCreated_at(nowTs);
            member.setUpdated_at(nowTs);
            return membersRepository.save(member);
        } catch (Exception e) {
            // エラーの場合はロールバックされる
            throw new RuntimeException("ユーザーの作成に失敗しました。", e);
        }
    }

    // ユーザーを取得する
    public Members getMember(int id) {
        return membersRepository.findById(id).orElse(null);
    }

    // ユーザーを更新する
    @Transactional
    public Members updateMember(Members member) {
        try {
            return membersRepository.save(member);
        } catch (Exception e) {
            throw new RuntimeException("ユーザーの更新に失敗しました。", e);
        }
    }

    // ユーザーを削除する
    @Transactional
    public void deleteMember(int id) {
        try {
            membersRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("ユーザーの削除に失敗しました。", e);
        }
    }

    // すべてのユーザーを取得する
    public Iterable<Members> getAllMembers() {
        System.out.println("membersRepository = " + membersRepository);
        System.out.println("membersRepository.findAll() = " + membersRepository.findAll());
        return membersRepository.findAll();
    }
}
