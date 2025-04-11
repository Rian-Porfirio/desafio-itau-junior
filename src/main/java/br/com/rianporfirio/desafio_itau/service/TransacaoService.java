package br.com.rianporfirio.desafio_itau.service;

import br.com.rianporfirio.desafio_itau.domain.model.Transacao;
import br.com.rianporfirio.desafio_itau.dto.TransacaoDto;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.rianporfirio.desafio_itau.dto.TransacaoEstatisticasDto;
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

  public void delete() {
    transacoes.clear();
  }

  public TransacaoEstatisticasDto getEstatisticas() {
    var transacoesUltimoMinuto = getTransacoesFeitasNoUltimoMinuto();
    var estatisticas =
            transacoesUltimoMinuto
            .stream()
            .collect(Collectors.summarizingDouble(Transacao::getValor));

    if (transacoesUltimoMinuto.isEmpty()) {
      return new TransacaoEstatisticasDto();
    } else {
      return new TransacaoEstatisticasDto(estatisticas);
    }
  }

  public List<Transacao> getTransacoesFeitasNoUltimoMinuto() {
    OffsetDateTime horarioLimiteBusca = OffsetDateTime.now().minusSeconds(60);
    return transacoes
            .stream()
            .filter(t -> t.getDataHora().isAfter(horarioLimiteBusca))
            .toList();
  }
}
