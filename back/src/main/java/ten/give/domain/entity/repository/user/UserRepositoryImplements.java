package ten.give.domain.entity.repository.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ten.give.domain.entity.user.User;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryImplements implements UserRepository {

    private final UserJpaRepository jpaRepository;
    private final UserJpaQueryRepository queryRepository;

    @Override
    public User saveUser(User user) {
        return jpaRepository.save(user);
    }


    @Override
    @Transactional
    public void updateUser(Long userId, User updateParam) {
        Optional<User> target = jpaRepository.findById(userId);

        log.info("updateParam : {} ", updateParam.getName());

        if (!target.isEmpty()) {
            User targetUser = target.get();
            log.info("targetUser : {} ", targetUser.getName());
            targetUser.setName(targetUser.getName());
            targetUser.setEmail(targetUser.getEmail());
            targetUser.setPassword(targetUser.getPassword());
            jpaRepository.save(targetUser);
        }
    }

    @Override
    public void deleteUserByUserId(Long userId) {
        jpaRepository.deleteById(userId);
    }

    @Override
    public Optional<User> findUserByUserId(Long userId) {
        return jpaRepository.findById(userId);
    }

    @Override
    public List<User> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return jpaRepository.findByEmail(email);
    }
}
