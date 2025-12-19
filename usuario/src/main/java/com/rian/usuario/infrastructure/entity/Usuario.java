package com.rianbreno.apredendospring.infrastructure.entity; // Organiza a classe no pacote de infraestrutura/entidade.

import java.util.Collection; // Importa a interface para lidar com grupos de objetos (usado nas permissões).
import java.util.List; // Importa a lista para armazenar os relacionamentos de endereços e telefones.
import jakarta.persistence.*; // Importa anotações da JPA para persistência de dados no banco.
import lombok.AllArgsConstructor; // Lombok: gera construtor com todos os campos.
import lombok.Getter; // Lombok: gera métodos de leitura (getters).
import lombok.NoArgsConstructor; // Lombok: gera construtor vazio (essencial para o Hibernate).
import lombok.Setter; // Lombok: gera métodos de escrita (setters).
import org.jspecify.annotations.Nullable; // Indica que um retorno pode ser nulo, ajudando na segurança do código.
import org.springframework.security.core.GrantedAuthority; // Representa uma permissão/autoridade do usuário.
import org.springframework.security.core.userdetails.UserDetails; // Interface do Spring Security para representar o usuário logado.

@Getter // Gera automaticamente os métodos get para todos os campos
@Setter // Gera automaticamente os métodos set para todos os campos
@AllArgsConstructor // Gera construtor para facilitar a criação do objeto com dados
@NoArgsConstructor // Permite que o Spring/Hibernate instancie a classe sem dados iniciais
@Entity // Define que esta classe será uma tabela chamada "usuario" no banco de dados

@Table(name = "usuario") // Especifica o nome exato da tabela no banco de dados

public class Usuario implements UserDetails { // A classe implementa UserDetails para ser reconhecida pelo Spring Security
      @Id // Define a chave primária da tabela
      @GeneratedValue(strategy = GenerationType.IDENTITY) // Define que o banco gera o ID automaticamente (1, 2, 3...)
      private Long id;

      @Column(name = "nome", length = 100) // Mapeia a coluna 'nome' com limite de 100 caracteres
      private String nome;

      @Column(name = "email", length = 100) // Mapeia o 'email'. Geralmente usado como login
      private String email;

      @Column(name = "senha") // Mapeia a 'senha'. No banco, esta senha deve estar criptografada
      private String senha;

      @OneToMany(cascade = CascadeType.ALL) // Relacionamento Um-para-Muitos, deletar usuário deleta seus endereços
      @JoinColumn(name = "usuario_id", referencedColumnName = "id") // Cria a Chave Estrangeira na tabela Endereco
      private List<Endereco> enderecos; // Lista de endereços associados a este usuário específico

      @OneToMany(cascade = CascadeType.ALL) // Relacionamento Um-para-Muitos; o ciclo de vida depende do usuário
      @JoinColumn(name = "usuario_id", referencedColumnName = "id") // Liga o telefone ao ID deste usuário
      private List<Telefone> telefones; // Lista de telefones associados

      @Override // Sobrescreve método do UserDetails para retornar as permissões (ex: ROLE_ADMIN).
      public Collection<? extends GrantedAuthority> getAuthorities() {
            return List.of(); // Retorna lista vazia por enquanto (usuário sem permissões específicas)
      }

      @Override // Sobrescreve para informar ao Spring Security qual campo é a senha
      public @Nullable String getPassword() {
            return senha; // Retorna o atributo senha da nossa classe
      }

      @Override // Sobrescreve para informar qual campo é o identificador único (login)
      public String getUsername() {
            return email; // Retorna o email, definido aqui como o nome de usuário para login
      }
}