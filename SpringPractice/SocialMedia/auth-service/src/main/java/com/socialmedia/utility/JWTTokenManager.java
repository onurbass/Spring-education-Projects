package com.socialmedia.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.socialmedia.exception.AuthManagerException;
import com.socialmedia.exception.ErrorType;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JWTTokenManager {
  String secretKey = "secretKey";
  String issuer = "onurbas";

  public Optional<String> createToken(Long id) {
	String token = null;
	Date date = new Date(System.currentTimeMillis() + (1000L * 60 * 5));
	try {
	  token = JWT.create()
				 .withClaim("myId",id)
				 .withIssuedAt(new Date())
				 .withExpiresAt(date)
				 .sign(Algorithm.HMAC512(secretKey));

	} catch (Exception e) {
	  System.out.println(e.getMessage());
	}
	return Optional.ofNullable(token);

  }

  public Optional<Long> getIdFromToken(String token) {
	try {
	  Algorithm algorithm = Algorithm.HMAC512(secretKey);
	  JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
	  DecodedJWT decodedJWT = verifier.verify(token);
	  if (decodedJWT == null) {
		throw new AuthManagerException(ErrorType.INVALID_TOKEN);
	  }
	  Long id = decodedJWT.getClaim("myId").asLong();
	  return Optional.of(id);
	} catch (Exception e) {
	  throw new AuthManagerException(ErrorType.INVALID_TOKEN);
	}

  }
}