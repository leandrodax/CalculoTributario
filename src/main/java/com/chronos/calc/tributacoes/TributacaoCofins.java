/* 
 * The MIT License
 *
 * Copyright 2017 Chronusinfo.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.chronos.calc.tributacoes;

import com.chronos.calc.CalcularBaseCalculoCofins;
import com.chronos.calc.dto.ITributavel;
import com.chronos.calc.enuns.TipoDesconto;
import com.chronos.calc.resultados.IResultadoCalculoCofins;
import com.chronos.calc.resultados.imp.ResultadoCalculoCofins;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author John Vanderson M L
 */
public class TributacaoCofins {

    private final ITributavel tributos;
    private final CalcularBaseCalculoCofins baseCalculoCofins;

    public TributacaoCofins(ITributavel tributos, TipoDesconto desconto) {
        this.tributos = tributos;
        baseCalculoCofins = new CalcularBaseCalculoCofins(tributos, desconto);
    }

    public IResultadoCalculoCofins calcular() {
        return calcularCofins();
    }

    private IResultadoCalculoCofins calcularCofins() {
        BigDecimal baseCalculo = baseCalculoCofins.getBaseCalculo();

        BigDecimal valorIcms = calcularCofins(baseCalculo);

        return new ResultadoCalculoCofins(baseCalculo, valorIcms);
    }

    private BigDecimal calcularCofins(BigDecimal baseCalculo) {
        return baseCalculo.multiply(tributos.getPercentualCofins()).divide(BigDecimal.valueOf(100), RoundingMode.DOWN).setScale(2, RoundingMode.DOWN);

    }

}
