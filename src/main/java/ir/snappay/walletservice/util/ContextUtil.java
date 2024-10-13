package ir.snappay.walletservice.util;

import ir.snappay.walletservice.entity.User;
import ir.snappay.walletservice.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ContextUtil {
    private final UserService userService;

    public ContextUtil(UserService userService) {
        this.userService = userService;
    }

    public static User getUser(){
        Object principles =SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principles != null && principles instanceof User){
            return ((User) principles);
        }else{
            throw new UsernameNotFoundException("user not found");
        }
    }

//    public User getUser(){
//            return userService.findByMobileNumber(getUsername()).orElseThrow(()->new UsernameNotFoundException("user not found"));
//
//    }
}
