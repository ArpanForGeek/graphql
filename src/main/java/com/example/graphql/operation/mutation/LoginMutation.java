package com.example.graphql.operation.mutation;

import com.example.graphql.model.response.JwtResponse;
import com.example.graphql.model.userprofile.CustomUserProfile;
import com.example.graphql.services.serviceprovider.impl.userprofile.UserProfileService;
import com.example.graphql.utils.JwtTokenUtil;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginMutation implements GraphQLMutationResolver {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserProfileService userProfileService;

    public JwtResponse login(String username, String password) throws Exception {
        authenticate(username,password);
        CustomUserProfile userDetails = userProfileService
                .loadUserByUsername(username);

        final String token = jwtTokenUtil.generateToken(userDetails);

        return new JwtResponse(token);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
