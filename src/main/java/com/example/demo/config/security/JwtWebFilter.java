package com.example.demo.config.security;

import java.security.KeyFactory;

import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import reactor.core.publisher.Mono;

@Configuration(proxyBeanMethods = false)
@Component
public class JwtWebFilter implements WebFilter {
	private static final Logger logger = LoggerFactory.getLogger(JwtWebFilter.class);
	private static final String PROBE_TOKEN = "Se va ha realizar la validación del token";
	private static final String VALID_TOKEN = "Se ha validado el token JWT correctamente";
	private static final String INVALID_TOKEN = "No Se ha podido verificar el token JWT";
	private static final String PK = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnzyis1ZjfNB0bBgKFMSvvkTtwlvBsaJq7S5wA+kzeVOVpVWwkWdVha4s38XM/pa/yr47av7+z3VTmvDRyAHcaT92whREFpLv9cj5lTeJSibyr/Mrm/YtjCZVWgaOYIhwrXwKLqPr/11inWsAkfIytvHWTxZYEcXLgAXFuUuaS3uF9gEiNQwzGTU1v0FqkqTBr4B8nW3HCN47XUu0t8Y0e+lf4s4OxQawWD79J9/5d3Ry0vbV3Am1FtGJiJvOwRsIfVChDpYStTcHTCMqtvWbV6L11BWkpzGXSW4Hv43qa+GSYOD2QU68Mb59oSk2OB+BtOLpJofmbGEGgvmwyCI9MwIDAQAB-----END PUBLIC KEY-----";
	private static final String BEGIN_KEY = "-----BEGIN PUBLIC KEY-----";
	private static final String END_KEY = "-----END PUBLIC KEY-----";
	private static final String BLANK = "";
	private static final String AUTHORIZATION_TOKEN = "Authorization";
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		logger.info(PROBE_TOKEN);
		String token = null;
		if ((exchange.getRequest().getHeaders().get(AUTHORIZATION_TOKEN)) != null
				&& (!exchange.getRequest().getHeaders().get(AUTHORIZATION_TOKEN).isEmpty()))
			token = exchange.getRequest().getHeaders().get(AUTHORIZATION_TOKEN).get(0);

		String publicKeyContent = PK;
		publicKeyContent = publicKeyContent.replaceAll("\\n", BLANK).replace(BEGIN_KEY, BLANK).replace(END_KEY, BLANK);

		KeyFactory kf = null;
		try {
			kf = KeyFactory.getInstance("RSA");
			X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyContent));
			RSAPublicKey pubKey = null;
			pubKey = (RSAPublicKey) kf.generatePublic(keySpecX509);
			Algorithm algorithmRS = Algorithm.RSA256(pubKey, null);
			JWTVerifier verifier = JWT.require(algorithmRS).withIssuer("santander").build();
			DecodedJWT jwt = verifier.verify(token);
			logger.info(VALID_TOKEN);
		} catch (Exception e) {
			logger.error(INVALID_TOKEN);
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, INVALID_TOKEN);
		}

		return chain.filter(exchange);
	}

}
