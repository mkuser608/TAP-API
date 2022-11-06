package com.tapcell.mukesh.database.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.tapcell.mukesh.forUserEmail;
import com.tapcell.mukesh.database.service.JwtService;
import com.tapcell.mukesh.database.util.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private forUserEmail forUserEmail;

	private String username = null;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String header = request.getHeader("Authorization");
		String jwtToken = null;

		if (header != null && header.startsWith("Bearer ")) {
			jwtToken = header.substring(7);

			try {
				username = jwtUtil.getUsernameFromToken(jwtToken);
				forUserEmail.setUserEmail(username);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to fetch tocken");
			} catch (ExpiredJwtException e) {
				System.out.println("jwt Token Expired");
			}
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = this.jwtService.loadUserByUsername(username);

				if (jwtUtil.validateToken(jwtToken, userDetails)) {

					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							jwtToken, null, userDetails.getAuthorities());

					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
		} else {
			System.out.println("jwt token does not starts with Bearer");
		}

		filterChain.doFilter(request, response);
	}

}
