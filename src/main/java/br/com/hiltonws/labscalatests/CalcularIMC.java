

/*
 * MIT License
 *
 * Copyright (c) 2017 Hilton W. Silva
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package br.com.hiltonws.labscalatests;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Cacula o IMC por peso e altura e o classifica
 *
 * @author hilton
 */
public class CalcularIMC {

    private CalcularIMC() {
        //singleton
    }

    /**
     * Calcula o IMC com base em
     * @param peso peso do indivíduo
     * @param altura altura do indivíduo
     * @return o IMC do indivíduo
     */
    public static double calcularIMC(double peso, double altura) {
        double resultado = peso / Math.pow(altura, 2);
        BigDecimal bigDecimal = BigDecimal.valueOf(resultado);
        bigDecimal = bigDecimal.setScale(1, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }

    /**
     * Classifica o IMC conforme o
     * @param imc imc do indivíduo
     * @return a classificação do IMC
     */
    public static String classificar(double imc){
        if(imc < 16.0){
            return Classificacao.MAGREZA_GRAU_III.value();
        }

        if (imc >= 16.0 && imc <= 16.9){
            return Classificacao.MAGREZA_GRAU_II.value();
        }

        if(imc >= 17.0 && imc <= 18.4){
            return Classificacao.MAGREZA_GRAU_I.value();
        }

        if(imc >= 18.5 && imc <= 24.9){
            return Classificacao.EUTROFIA.value();
        }

        if(imc >= 25.0 && imc <= 29.9){
            return Classificacao.SOBREPESO.value();
        }

        if(imc >= 30.0 && imc <= 34.9){
            return Classificacao.OBESIDADE_GRAU_I.value();
        }

        if(imc >= 35.0 && imc < 40.0){
            return Classificacao.OBESIDADE_GRAU_II.value();
        }

        if(imc >= 40.0){
            return Classificacao.OBESIDADE_GRAU_III.value();
        }

        return "";

    }

    /**
     * Enum que representa as classificações disponíveis
     */
    enum Classificacao {
        MAGREZA_GRAU_III("Magreza Grau III"),
        MAGREZA_GRAU_II("Magreza Grau II"),
        MAGREZA_GRAU_I("Magreza Grau I"),
        EUTROFIA("Eutrofia"),
        SOBREPESO("Sobrepeso"),
        OBESIDADE_GRAU_I("Obesidade Grau I"),
        OBESIDADE_GRAU_II("Obesidade Grau II"),
        OBESIDADE_GRAU_III("Obesidade Grau III");

        private String value;

        Classificacao(String value) {
            this.value = value;

        }

        public String value(){
            return value;
        }
    }


}


