package com.example.login_auth_api.domain.user;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users") //assim criaremos uma tabela no banco de dados, que a entidade users irá mapear
@Getter //usado pelo Lambok para reduzir repetição no código
@Setter //serve para definir ou atualizar o valor de um atributo privado
@AllArgsConstructor //serve para gerar automaticamente um construtor que recebe todos os atributos da classe como argumentos
@NoArgsConstructor //serve para gerar automaticamente um construtor sem argumentos, ou seja, vazio para a nossa classe

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
//objeto criado com os atributos citados acima



