package com.awangelo

import com.awangelo.model.Produto
import com.awangelo.model.SearchConfig
import com.awangelo.service.CrawlerService
import com.awangelo.service.CleanerService
import com.awangelo.service.CsvService

static void main(String[] args) {
    SearchConfig searchConfig = new SearchConfig(
            estado: 'ce',
            regiao: 'grande-fortaleza',
            termoBusca: 'iphone+11',
            menorPreco: true,
            paginasParaLer: 3
    )
    CrawlerService crawler = new CrawlerService(searchConfig)

    List<Produto> produtos = crawler.coletarDados()
    BigDecimal mediaComInvalidos = CleanerService.calcularMedia(produtos)
    List<Produto> produtosLimpo = CleanerService.limparDados(produtos, mediaComInvalidos)

    Produto.media = CleanerService.calcularMedia(produtosLimpo)
    List<Produto> produtosAbaixoDaMedia = CleanerService.produtosAbaixo(produtosLimpo, Produto.media)

    println '===================='
    println "Média dos valor: ${String.format("%.2f", Produto.media)}"
    println "Produto com menor valor: ${produtosAbaixoDaMedia.min { it.valor }.url}"
    println "Produto com maior valor: ${produtosAbaixoDaMedia.max { it.valor }.url}"

    CsvService.gerarRelatorio(produtosAbaixoDaMedia, 'relatorio-olx.csv')
}