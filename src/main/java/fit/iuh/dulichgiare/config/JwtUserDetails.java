package fit.iuh.dulichgiare.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class JwtUserDetails extends User {

	private static final long serialVersionUID = -2810212241360878982L;

	public final Long id;

	public JwtUserDetails(final Long id, final String username, final String hash,
			final Collection<? extends GrantedAuthority> authorities) {
		super(username, hash, authorities);
		this.id = id;
	}

}
