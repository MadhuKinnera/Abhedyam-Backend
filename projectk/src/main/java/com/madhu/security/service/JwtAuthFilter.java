package com.madhu.security.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	@Autowired
	private JwtAuthProvider jwtProvider;

	@Autowired
	private LoadUserImpl loadUser;


	private String jwtSecretKey = "sIoVC8OFOgmxbk9XRYtY2zMKXuYXBGL2d3x1IV37";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// getToken form req
		
	

	        response.setHeader("Access-control-Allow-Origin", "*");
	        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
	        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, x-auth-token");
	        response.setHeader("Access-Control-Max-Age", "3600");
	        response.setHeader("Access-Control-Allow-Credentials", "true");

		String token = null;
		try {
			token = getTokenFromRequest(request);

			System.out.println("validating token" + token);

			if (token != null && jwtProvider.validateToken(token, jwtSecretKey)) {

				// validate token and get Username

				System.out.println("token validated");

				String email = jwtProvider.getUsernameFromToken(token);

				// loaduser by username

				UserDetails userDetails = loadUser.loadUserByUsername(email);

				System.out.println("user details found " + userDetails.getUsername());

				UsernamePasswordAuthenticationToken authtoken = new UsernamePasswordAuthenticationToken(userDetails,
						null, userDetails.getAuthorities());

				authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// set to context path

				System.out.println("Setting Auth Object");
				SecurityContextHolder.getContext().setAuthentication(authtoken);

			}
		} catch (Exception e) {

			System.out.println("failed in fetching jwt token ");

		}

		filterChain.doFilter(request, response);
	}

	private String getTokenFromRequest(HttpServletRequest req) {

		String header = req.getHeader(HttpHeaders.AUTHORIZATION);

		System.out.println("the token is " + header);

		if (!StringUtils.isEmpty(header) && header.startsWith("Bearer ")) {
			return header.substring(7);
		}
		return "Token Not Found";

	}
}
