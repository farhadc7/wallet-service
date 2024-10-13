package ir.snappay.walletservice.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginUserDto {
    @NotBlank(message="fill the inputs")
    @Pattern(regexp = "^[0]{1}[9]{1}[0-9]{9}$", message = "mobile number or password is wrong!")
    private String mobileNumber;

    @NotBlank(message= "fill the inputs")
    @Pattern(regexp ="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{4,20}$", message= "mobile number or password is wrong!" )
    private String password;
}
