package ir.snappay.walletservice.entity;


import ir.snappay.walletservice.enums.RoleEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(indexes = @Index(name = "mobileNumber",columnList = "mobile_number",unique = true))
public class User extends BaseEntity implements UserDetails {
    @Column(name = "mobile_number",unique = true,nullable = false)
    @NotBlank(message="mobile number should be filled")
    @Pattern(regexp="^[0]{1}[9]{1}[0-9]{9}$",message="mobile number should start with 09 , for example: 09913121356")
    private String mobileNumber;
    @Column(nullable = false)
    @NotBlank(message ="password should be filled")
    private String password;
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.getRole().name()));
    }

    @Override
    public String getUsername() {
        return this.getMobileNumber();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
