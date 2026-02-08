package com.awangelo.parser

import com.awangelo.model.Produto
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

final class OlxHtmlParser {

    static List<Produto> extrairProdutos(Document document) {
        List<Produto> produtos = []

        document.select('section.olx-adcard').each { Element card ->
            Produto produto = parseCard(card)

            if (produto && produto.titulo && produto.valor != null) {
                produtos.add(produto)
            }
        }

        produtos
    }

    private static Produto parseCard(Element card) {
        // URL e titulo.
        Element linkElement = card.select("a.olx-adcard__link").first()
        if (!linkElement) return null

        String url = linkElement.attr("href")
        String titulo = linkElement.attr("title")

        // Valor
        String valorTexto = card.select("h3.olx-adcard__price").text()
        BigDecimal valor = limparPreco(valorTexto)

        // Endereco
        String endereco = card.select("p.olx-adcard__location").text()

        [titulo, valor, endereco, url]
    }

    private static BigDecimal limparPreco(String texto) {
        if (!texto) return null

        String limpo = texto.replaceAll("[^0-9,]", "")
        if (!limpo) return null

        limpo = limpo.replace(',', '.')

        limpo as BigDecimal
    }
}
