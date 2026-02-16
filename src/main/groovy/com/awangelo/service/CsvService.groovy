package com.awangelo.service

import com.awangelo.model.Produto

final class CsvService {
    static void gerarRelatorio(List<Produto> produtos, String nomeArquivo) {
        if (!produtos) {
            println "Nenhum produto para gerar relatório."
            return
        }

        File file = new File(nomeArquivo)
        file.withWriter('UTF-8') { writer ->
            writer.writeLine('Título;Valor;Endereço;URL')
            produtos.each { p ->
                String valorFormatado = String.format("%.2f", p.valor)
                // Substitui ponto e virgula por virgula para nao quebrar o CSV
                String titulo = (p.titulo ?: "").replaceAll(";", ",")
                String endereco = (p.endereco ?: "").replaceAll(";", ",")

                writer.writeLine("${titulo};${valorFormatado};${endereco};${p.url ?: ''}")
            }
        }

        println "Relatório gerado em: ${file.absolutePath}"
    }
}

