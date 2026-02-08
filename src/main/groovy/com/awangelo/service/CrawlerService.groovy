package com.awangelo.service

import com.awangelo.model.Produto
import com.awangelo.model.SearchConfig
import com.awangelo.parser.OlxHtmlParser
import groovyx.net.http.HttpBuilder
import org.jsoup.nodes.Document

import static groovyx.net.http.HttpBuilder.configure

final class CrawlerService {
    private static final String BASE_URL = 'https://www.olx.com.br'
    private static final String USER_AGENT = 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/143.0.0.0 Safari/537.36'
    private HttpBuilder httpBuilder
    private SearchConfig config

    CrawlerService(SearchConfig searchConfig) {
        if (!searchConfig.isValid()) throw new IllegalArgumentException("Configurações de busca inválidas: ${searchConfig}")

        this.config = searchConfig

        StringBuilder path = new StringBuilder()
        if (config.estado) path.append('/estado-').append(config.estado)
        if (config.regiao) path.append('/').append(config.regiao)

        this.httpBuilder = configure {
            request.uri = BASE_URL
            request.uri.path = path.toString()
            request.headers['User-Agent'] = USER_AGENT
        }
    }

    List<Produto> coletarDados() {
        List<Produto> todosProdutos = []

        (1..config.paginasParaLer).each { paginaAtual ->
            println "--> Processando página ${paginaAtual} de ${config.paginasParaLer}..."

            Document document = httpBuilder.get {
                request.uri.query = [
                        q: config.termoBusca,
                        sp: config.menorPreco ? '2' : null,
                        o: paginaAtual
                ]

                println "URL de acesso: ${request.uri.toURI()}"

            } as Document

            List<Produto> produtos = OlxHtmlParser.extrairProdutos(document)
            todosProdutos.addAll(produtos)

            Thread.sleep(2000)
        }

        todosProdutos
    }
}