package ir.snappay.walletservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDto {

    @NotBlank(message="mobilenumber should be filled")
    @Pattern(regexp="^[0]{1}[9]{1}[0-9]{9}$",message="mobile number should start with 09 , for example: 09913121356")
    private String mobileNumber;

    @NotBlank(message = "password should be filled")
    @Pattern(regexp= "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{4,20}$",
            message="password should contains at least 4 letters, including lowercase and uppercase letters , numbers and signs. ")
    private String password;
}
