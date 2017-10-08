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

package br.com.hiltonws.labscalatests


import org.scalatest.exceptions.TestFailedException
import org.scalatest.{FreeSpec, Matchers}

/**
  * Cenários de testes para calculo de IMC por meio de peso e altura.
  *
  * @author hilton
  */
class TestCalcularIMC extends FreeSpec with Matchers {

  /**
    * Ultilitário para fazer a contagem dos números de uma casa decimal que recebe
    * @param minimo valor minimo
    * @param maximo valor máximo
    * @param resultado o valor que deve ser comparado com o calculoIMC
    */

  def contadorCalculoIMC(minimo: Double, maximo: Double, resultado: String) {
    var count = minimo

    while (count !==  maximo) {
      CalcularIMC.classificar(count) shouldBe resultado
      count = BigDecimal(count + 0.1).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
    }
  }

  "Cenário 1: Calcular o IMC, com base na fórmula peso/altura²" - {
    "Dado a Altura igual a 1,75m" - {
      "E Peso igual a 80Kg" - {
        "Então o resultado deve ser 26,1 kg/m2" in {

          CalcularIMC.calcularIMC(80, 1.75) shouldBe 26.1
        }
      }
    }
  }

  "Cenário 2: Classificar IMC" - {
    "Dado o IMC menor que 16 kg/m² " - {
      "Então deve classificar como Magreza Grau III" in {

        contadorCalculoIMC(0.0, 15.9, "Magreza Grau III")
      }

    }

    "Dado o IMC entre 16 a 16,9 kg/m² " - {
      "Então deve classificar como Magreza Grau II" in {

        contadorCalculoIMC(16.0, 16.9, "Magreza Grau II")
      }

    }

    "Dado o IMC entre 17 a 18,4 kg/m² " - {
      "Então deve classificar como Magreza Grau I" in {

       contadorCalculoIMC(17.0, 18.4, "Magreza Grau I")
      }

    }

    "Dado o IMC entre 18,5 a 24,9 kg/m² " - {
      "Então deve classificar como Eutrofia" in {

        contadorCalculoIMC(18.5, 24.9, "Eutrofia")
      }

    }

    "Dado o IMC entre 25 a 29,9 kg/m² " - {
      "Então deve classificar como Sobrepeso" in {

        contadorCalculoIMC(25.0, 29.9, "Sobrepeso")
      }

    }

    "Dado o IMC entre 30 a 34,9 kg/m² " - {
      "Então deve classificar como Obesidade Grau I" in {

        contadorCalculoIMC(30.0, 34.9, "Obesidade Grau I")
      }

    }

    "Dado o IMC entre 35 a 40 kg/m² " - {
      "Então deve classificar como Obesidade Grau II" in {

        contadorCalculoIMC(35.0, 40.0, "Obesidade Grau II")
      }

    }

    "Dado o IMC maior que 40 kg/m² " - {
      "Então deve classificar como Obesidade Grau III" in {

        contadorCalculoIMC(40.0, 50.0, "Obesidade Grau III")
      }

    }

  }

}