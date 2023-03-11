package fit.iuh.dulichgiare.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenService jwtTokenService;

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@PostMapping("/authenticate")
	public AuthenticationResponse authenticate(@RequestBody @Valid final AuthenticationRequest authenticationRequest) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getLogin(),
					authenticationRequest.getPassword()));
		} catch (final BadCredentialsException ex) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}

		final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getLogin());
		final AuthenticationResponse authenticationResponse = new AuthenticationResponse();
		authenticationResponse.setAccessToken(jwtTokenService.generateToken(userDetails));
		return authenticationResponse;
	}

}

@Data
class AuthenticationRequest {

	@NotNull
	@Size(max = 255)
	private String login;

	@NotNull
	@Size(max = 255)
	private String password;

}

@Data
class AuthenticationResponse {

	private String accessToken;

}