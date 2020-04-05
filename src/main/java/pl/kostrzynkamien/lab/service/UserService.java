package pl.kostrzynkamien.lab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kostrzynkamien.lab.model.User;
import pl.kostrzynkamien.lab.model.VerificationToken;
import pl.kostrzynkamien.lab.repo.TokenRepo;
import pl.kostrzynkamien.lab.repo.UserRepo;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class UserService {
    private TokenRepo tokenRepo;
    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;
    private MailSenderService mailSenderService;

    @Autowired
    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder, TokenRepo tokenRepo, MailSenderService mailSenderService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepo = tokenRepo;
        this.mailSenderService = mailSenderService;
    }

    public void addNewUser(User user, HttpServletRequest httpServletRequest) throws MessagingException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(user, token);
        tokenRepo.save(verificationToken);
        String url = "http://" + httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort() + httpServletRequest.getContextPath()
                + "/verify-token?token=" + token;
        //mailSenderService.sendMail(user.getUsername(), "Verification Token", url, false);
    }

    public void verifyToken(String token) {
        User user = tokenRepo.findByValue(token).getUser();
        user.setEnabled(true);
        userRepo.save(user);
    }

}
