package com.sugrado.rentacar.business.concretes;

import com.sugrado.rentacar.business.abstracts.UserService;
import com.sugrado.rentacar.business.dtos.requests.RegisterRequest;
import com.sugrado.rentacar.business.messages.AuthMessages;
import com.sugrado.rentacar.core.services.JwtService;
import com.sugrado.rentacar.core.utilities.exceptions.types.BusinessException;
import com.sugrado.rentacar.core.utilities.mapping.ModelMapperService;
import com.sugrado.rentacar.dataAccess.abstracts.UserRepository;
import com.sugrado.rentacar.entities.concretes.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserManager implements UserService {
    private final UserRepository userRepository;
    private final ModelMapperService modelMapperService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(RegisterRequest request) {
        // TODO: Business Rule, Validation
        User user = modelMapperService.forRequest().map(request, User.class);
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new BusinessException(AuthMessages.LOGIN_FAILED));
    }
}
