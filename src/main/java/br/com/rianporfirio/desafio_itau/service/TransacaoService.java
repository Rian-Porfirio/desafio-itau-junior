package br.com.rianporfirio.desafio_itau.service;

import br.com.rianporfirio.desafio_itau.domain.model.Transacao;
import br.com.rianporfirio.desafio_itau.dto.TransacaoDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TransacaoService {

  private final List<Transacao> transacoes;

  public TransacaoService(List<Transacao> transacoes) {
    this.transacoes = transacoes;
  }

  public void create(TransacaoDto transacaoDto) {
    transacoes.add(new Transacao(transacaoDto.valor(), transacaoDto.dataHora()));
  }
}
