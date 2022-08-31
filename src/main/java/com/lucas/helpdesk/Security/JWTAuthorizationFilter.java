package com.lucas.helpdesk.Security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	
	
	private JWTUtil jwtUtil;
	
	private UserDetailsService userDetailsService;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}

	
	//FILTRO
	@Override
	protected void doFilterInternal( HttpServletRequest request,
									HttpServletResponse response,
									FilterChain chain
									) throws IOException, ServletException {
		
		// PEGA O VALOR DO CABEÇALHO
		String header = request.getHeader("Authorization");

				
		
		// VERIFICA SE OBJ É NULO
		if(header != null && header.startsWith("Bearer ")) {
			UsernamePasswordAuthenticationToken auth = getAuthentication(request, header.substring(7));
			// SE OBJ DIFERENTE DE NULO LIBERA ACESSO
			if(auth != null) {
				SecurityContextHolder.getContext().setAuthentication(auth);

			}
				
		}
		

		//CONTINUA A EXECUÇÃO NORMAL
		chain.doFilter(request, response);

	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, String token) {
		if(jwtUtil.tokenValido(token)) {
			String username = jwtUtil.getUsername(token);
			UserDetails user = userDetailsService.loadUserByUsername(username);
			return new UsernamePasswordAuthenticationToken (user.getUsername() , null, user.getAuthorities());
			
		}
		return null;
	} 
	
		
}
