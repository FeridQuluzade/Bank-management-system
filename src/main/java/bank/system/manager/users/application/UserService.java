package bank.system.manager.users.application;


import bank.system.manager.users.application.dto.UserCreateDto;
import bank.system.manager.users.application.dto.UserDto;
import bank.system.manager.users.application.dto.UserPasswordDto;
import bank.system.manager.users.application.dto.UserUpdateDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface UserService {
    List<UserDto> retrieveAll();

    UserDto retrieveById(long id);

    UserPasswordDto retrieveByEmail(String email);

    long create(UserCreateDto userCreateDto);

    Set<Long> createAll(List<UserCreateDto> userCreateDtoList);

    void update(UserUpdateDto UserUpdateDto);

    void updatePassword(long id, String password);

    void updateRefreshToken(long id, String refreshToken);

    void deleteById(long id, long deletedBy, LocalDateTime deletedDate);

    void deleteAll(Set<Long> ids, long deletedBy, LocalDateTime deletedDate);
}
