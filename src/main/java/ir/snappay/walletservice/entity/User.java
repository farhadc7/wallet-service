package ir.snappay.walletservice.entity;


import ir.snappay.walletservice.enums.RoleEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(indexes = @Index(name = "mobileNumber",columnList = "mobile_number",unique = true))
public class User extends BaseEntity{
    @Column(name = "mobile_number",unique = true,nullable = false)
    @NotBlank(message="mobile number should be filled")
    @Pattern(regexp="^[0]{1}[9]{1}[0-9]{9}$",message="mobile number should start with 09 , for example: 09913121356")
    private String mobileNumber;
    @Column(nullable = false)
    @NotBlank(message ="password should be filled")
    private String password;
    @Enumerated(EnumType.STRING)
    private RoleEnum role;
}
