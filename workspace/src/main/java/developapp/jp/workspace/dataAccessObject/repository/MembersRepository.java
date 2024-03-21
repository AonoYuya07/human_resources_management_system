package developapp.jp.workspace.dataAccessObject.repository;

import developapp.jp.workspace.dataAccessObject.entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MembersRepository extends JpaRepository<Members, Integer> {
    //CRUDメソッドは自動的に生成されている
}
