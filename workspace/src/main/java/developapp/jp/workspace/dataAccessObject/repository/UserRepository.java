package developapp.jp.workspace.dataAccessObject.repository;

import developapp.jp.workspace.dataAccessObject.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    public Optional<Users> findByUserId(String userId);
}
