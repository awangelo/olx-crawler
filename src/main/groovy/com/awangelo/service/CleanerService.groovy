package com.awangelo.service

import com.awangelo.model.Produto

final class CleanerService {

    static List<Produto> limparDados(List<Produto> produtos, BigDecimal media) {
        if (!produtos) return null

        produtos.findAll { it.valor >= media * 0.3 }
    }

    static List<Produto> produtosAbaixo(List<Produto> produtos, BigDecimal media) {
        if (!produtos) return null

        produtos.findAll { it.valor <= media }
    }

    static BigDecimal calcularMedia(List<Produto> produtos) {
        if (!produtos) return null

        BigDecimal sum = produtos*.valor.sum() as BigDecimal

        sum / produtos.size()
    }
}
