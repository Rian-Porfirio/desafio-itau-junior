package br.com.rianporfirio.desafio_itau.service;

import br.com.rianporfirio.desafio_itau.domain.model.Transacao;
import br.com.rianporfirio.desafio_itau.dto.TransacaoDto;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransacaoService {

  public static final List<Transacao> transacoes = new ArrayList<>();

  public void create(TransacaoDto transacaoDto) {
    transacoes.add(new Transacao(transacaoDto.valor(), transacaoDto.dataHora()));
    log.info("Transação criada: valor= {}, dataHora= {}", transacaoDto.valor(), transacaoDto.dataHora());
  }

  public void delete() {
    transacoes.clear();
    log.info("Todas as transações foram removidas com sucesso.");
  }

}
