package pl.kostrzynkamien.lab.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kostrzynkamien.lab.model.VerificationToken;

public interface TokenRepo extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByValue(String value);
}
