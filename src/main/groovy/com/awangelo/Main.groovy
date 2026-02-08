package com.awangelo

import com.awangelo.model.SearchConfig
import com.awangelo.service.CrawlerService

static void main(String[] args) {
    SearchConfig searchConfig = new SearchConfig(
        estado: 'ce',
        regiao: 'grande-fortaleza',
        termoBusca: 'iphone+11',
        menorPreco: true,
        paginasParaLer: 3
    )
    CrawlerService crawler = new CrawlerService(searchConfig)

    println "SearchConfig: ${searchConfig}"
    println "Crawler: ${crawler}"
    println "Crawler initialized for base URI (paginasParaLer=${searchConfig.paginasParaLer})"
}