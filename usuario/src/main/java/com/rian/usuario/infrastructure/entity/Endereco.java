package com.rianbreno.apredendospring.infrastructure.entity; // Define o pacote para organizar o código e evitar conflitos de nomes.

import jakarta.persistence.*; // Importa a especificação JPA, necessária para mapear classes Java para tabelas de banco de dados.
import lombok.AllArgsConstructor; // Importa a anotação do Lombok² para gerar automaticamente um construtor com todos os campos.
import lombok.Getter; // Importa a anotação do Lombok para gerar os métodos Getter de todos os atributos.
import lombok.NoArgsConstructor; // Importa a anotação do Lombok para gerar um construtor vazio (exigido pela JPA).
import lombok.Setter; // Importa a anotação do Lombok para gerar os métodos Setter de todos os atributos.

@Getter // Indica que o Lombok deve criar métodos get
@Setter // Indica que o Lombok deve criar métodos set
@AllArgsConstructor // Cria um construtor que recebe todos os atributos como argumento
@NoArgsConstructor // Cria um construtor sem argumentos
@Entity // Define que esta classe é uma entidade persistente, ou seja, ela representa uma linha em uma tabela do banco

@Table(name = "endereco") // Especifica explicitamente o nome da tabela no banco de dados para evitar que o Java use o nome da classe

public class Endereco {
    @Id // Marca este campo como a Chave Primária da tabela, garantindo que cada endereço seja único
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Delega ao banco de dados a responsabilidade de gerar o ID (auto-incremento)
    private Long id; // O uso do tipo Long é padrão para IDs, pois suporta um volume massivo de registros

    @Column(name = "rua") // Mapeia o atributo para a coluna "rua"; útil se o nome no Java for diferente do nome no banco
    private String rua;

    @Column(name = "numero") // Define o campo para o número; usa-se String para suportar casos como "102-A" ou "S/N"
    private String numero;

    @Column(name = "complemento", length = 30) // O parâmetro 'length' define o tamanho máximo da coluna (VARCHAR) no banco de dados
    private String complemento;

    @Column(name = "cidade", length = 100) // Limita o nome da cidade a 100 caracteres, otimizando o armazenamento no disco
    private String cidade;

    @Column(name = "estado", length = 2) // Define tamanho 2 para forçar o uso de siglas (ex: SP, RJ, MG)
    private String estado;

    @Column(name = "cep", length = 9) // Define tamanho 9 para comportar o formato "00000-000"
    private String cep;
}