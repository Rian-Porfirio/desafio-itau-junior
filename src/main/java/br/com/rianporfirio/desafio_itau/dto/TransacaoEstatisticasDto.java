package br.com.rianporfirio.desafio_itau.dto;

import java.util.DoubleSummaryStatistics;

public record TransacaoEstatisticasDto(
    long count, double sum, double min, double max, double average) {

  public TransacaoEstatisticasDto(DoubleSummaryStatistics stats) {
    this(stats.getCount(), stats.getSum(), stats.getMin(), stats.getMax(), stats.getAverage());
  }

  public TransacaoEstatisticasDto() {
    this(0, 0.0, 0.0, 0.0, 0.0);
  }
}
