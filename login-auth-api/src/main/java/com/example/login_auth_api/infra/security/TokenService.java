package com.example.login_auth_api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.login_auth_api.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

//Tudo referente à geração e validação do token de segurança ficará aqui
@Service //indicando que é um componente do string para fazermos a injeção de dependência de forma correta
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;
    public String generateToken(User user){//Função de geração de token do objeto usuário
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);//Algoritimo de cripitografia (chave personalizada para criptografarmos e descriptografarmos os dados, assmi tendo a certeza de que foi eu que emiti o token válido para o usuário)
            String token = JWT.create()
                    .withIssuer("login-auth-api")//fazendo a identificação de onde está sendo gerado o token
                    .withSubject(user.getEmail())
                    .withExpiresAt(generateExpirationDate()) //Função para expirar a hora de geração do token
                    .sign(algorithm);//enfim criamos o token
            return token;//Retornamos o token para exibição
        }catch(JWTCreationException exception){//trabalhamos com exceção caso retorne um erro ao executar
                throw new RuntimeException("Error while autenticating");
        }
    }
    public String validateToken(String token){//Função de Validação de token
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("login-auth-api")
                    .build()//montamos o objeto para fazer a verificação em seguida
                    .verify(token)//verificaremos o token
                    .getSubject();//pegaremos o valor que foi salvo na requisição de geração de token

        }catch (JWTVerificationException exception){
            return null;//dessa forma estamos apenas validando o token, retornando assim somente o e-mail referente ao token gerado
        }
    }

    private Instant generateExpirationDate(){
            return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
