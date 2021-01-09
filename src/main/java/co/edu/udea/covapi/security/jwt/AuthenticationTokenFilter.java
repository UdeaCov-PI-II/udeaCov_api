package co.edu.udea.covapi.security.jwt;

import co.edu.udea.covapi.security.SecurityConstants;
import co.edu.udea.covapi.security.services.DefaultCovApiUserDetailsService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationTokenFilter extends OncePerRequestFilter {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationTokenFilter.class);

    @Autowired
    private DefaultCovApiUserDetailsService userDetailsService;

    @Autowired
    private JwtHelper jwtHelper;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        try {
            String jwt = parseJwt(request);
            if (StringUtils.isNotEmpty(jwt) && jwtHelper.validateJwtToken(jwt)){
                String username = jwtHelper.getUserNameFromToken(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken auth =  new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }catch (Exception e) {
            LOG.error("Cannot perform user authentication:. Cause: ", e);
        }

        chain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String authHeader = request.getHeader(SecurityConstants.AUTH_HEADER);
        if (StringUtils.isNotEmpty(authHeader) && authHeader.startsWith(SecurityConstants.AUTH_TOKEN_PREFIX)) {
            return authHeader.substring(7);
        }

        return null;
    }

}
