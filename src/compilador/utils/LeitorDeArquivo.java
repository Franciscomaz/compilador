/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author comp15
 */
public class LeitorDeArquivo {
    public static String ler(String path) throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(path));) {
            String linha;
            StringBuilder conteudo = new StringBuilder();
            while ((linha = reader.readLine()) != null) {
                conteudo.append(linha);
            }
            return conteudo.toString();
        } catch (IOException e) {
            throw new IOException("Arquivo não encontrado.");
        }
    }
}
