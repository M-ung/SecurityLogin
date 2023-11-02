package securitylogin.securitylogin.dto;

import lombok.*;
import securitylogin.securitylogin.entity.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
    private Long userId;
    private String userName;
    private String userEmail;
    private String userPassword;

    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUserName(user.getUserName());
        userDto.setUserEmail(user.getUserEmail());
        userDto.setUserPassword(user.getUserPassword());
        return userDto;
    }
}
