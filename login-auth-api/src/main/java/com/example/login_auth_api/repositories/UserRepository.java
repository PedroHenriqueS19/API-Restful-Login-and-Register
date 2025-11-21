package com.example.login_auth_api.repositories;

import com.example.login_auth_api.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, String> {
    Optional<User> findByEmail(String email);
    //criado para declararmos os repositories sendo uma JPA, definindo somente assinatura da classe e dos seus métodos
    //Sempre passaremos a entidade que será manipulado e o tipo da chave primária (Nesse caso sendo o Id)
}
