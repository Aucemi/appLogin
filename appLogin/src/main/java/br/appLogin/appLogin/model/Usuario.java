package br.appLogin.appLogin.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "USUARIO")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Long idUsuario;

    @Column(name = "NOME", nullable = false, length = 50)
    private String nome;

    @Column(name = "EMAIL", nullable = false, length = 50, unique = true)
    private String email;

    @Column(name = "SENHA", nullable = false, length = 50)
    private String senha;

    @Column(name = "DATA_CADASTRO")
    private String dataCadastro;

    @Column(name = "PONTUACAO_ACUMULADA")
    private Integer pontuacaoAcumulada;
}
