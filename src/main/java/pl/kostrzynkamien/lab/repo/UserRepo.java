package pl.kostrzynkamien.lab.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kostrzynkamien.lab.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findAllByUsername(String userName);
}
