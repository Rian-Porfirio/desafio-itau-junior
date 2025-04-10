package br.com.rianporfirio.desafio_itau.domain.model;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transacao {
  private double valor;
  private OffsetDateTime dataHora;
}
