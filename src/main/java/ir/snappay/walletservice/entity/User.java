package ir.snappay.walletservice.entity;


import ir.snappay.walletservice.enums.RoleEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity{
    @Column(unique = true,nullable = false)
    @NotBlank("mobile number should be filled")
    @Pattern(regexp="^[0]{1}[9]{1}[0-9]{9}$",message="mobile number should start with 09 , for example: 09913121356")
    private String mobileNumber;
    @Column(nullable = false)
    @NotBlank("password should be filled")
    private String password;
    @Enumerated(EnumType.STRING)
    private RoleEnum role;
}
