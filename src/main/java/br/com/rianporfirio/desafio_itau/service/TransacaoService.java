package br.com.rianporfirio.desafio_itau.service;

import br.com.rianporfirio.desafio_itau.domain.model.Transacao;
import br.com.rianporfirio.desafio_itau.dto.TransacaoDto;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.rianporfirio.desafio_itau.dto.TransacaoEstatisticasDto;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransacaoService {

  private final List<Transacao> transacoes;

  public TransacaoService(List<Transacao> transacoes) {
    this.transacoes = transacoes;
  }

  public void create(TransacaoDto transacaoDto) {

    transacoes.add(new Transacao(transacaoDto.valor(), transacaoDto.dataHora()));
    log.info("Transação criada: valor= {}, dataHora= {}", transacaoDto.valor(), transacaoDto.dataHora());
  }

  public void delete() {
    transacoes.clear();
    log.info("Todas as transações foram removidas com sucesso.");
  }

  @Timed(value = "transacao.geracao.estatisticas.tempo", description = "Tempo para calcular estatísticas de transações")
  public TransacaoEstatisticasDto getEstatisticas(int intervaloEmSegundos) {
    var transacoesColetadas = getTransacoesRegistradasEmTempoInformado(intervaloEmSegundos);
    var estatisticas =
            transacoesColetadas
            .stream()
            .collect(Collectors.summarizingDouble(Transacao::getValor));

    if (transacoesColetadas.isEmpty()) {
      log.warn("Nenhuma transação encontrada no tempo informado.");
      return new TransacaoEstatisticasDto();
    } else {
      log.info("Encontrado {} transações feitas nos últimos {} minutos e {} segundos.",
              transacoesColetadas.size(), intervaloEmSegundos / 60, intervaloEmSegundos % 60);
      log.info("Estatísticas calculadas: quantidade= {} soma= {}, média= {}, min= {}, max= {}",
             estatisticas.getCount(), estatisticas.getSum(), estatisticas.getAverage(), estatisticas.getMin(), estatisticas.getMax());
      return new TransacaoEstatisticasDto(estatisticas);
    }
  }

  @Timed(value = "transacao.busca.transacoes.para.estatistica", description = "Tempo para realizar a busca de das transações no tempo informado")
  public List<Transacao> getTransacoesRegistradasEmTempoInformado(int intervaloEmSegundos) {
    OffsetDateTime horarioLimiteBusca = OffsetDateTime.now().minusSeconds(intervaloEmSegundos);
    log.info("Realizando buscas de transações");
    return transacoes
            .stream()
            .filter(t -> t.getDataHora().isAfter(horarioLimiteBusca))
            .toList();
  }

}
