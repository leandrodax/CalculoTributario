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
package com.chronos.calc;

import com.chronos.calc.dto.ITributavel;
import com.chronos.calc.enuns.TipoDesconto;
import java.math.BigDecimal;

/**
 *
 * @author John Vanderson M L
 */
public class CalcularBaseCalculoIssqn extends CalcularBaseCalculoBase {

    private final ITributavel tributos;
    private final TipoDesconto desconto;

    public CalcularBaseCalculoIssqn(ITributavel tributos, TipoDesconto desconto) {
        super(tributos);
        this.tributos = tributos;
        this.desconto = desconto;
    }

    public BigDecimal getBaseCalculo() {
        BigDecimal baseCalculo = calcularBaseCalculo();

        return desconto == TipoDesconto.Condincional ? calculaIcmsComDescontoCondicional(baseCalculo) : calculaIcmsComDescontoIncondicional(baseCalculo);
    }

    private BigDecimal calculaIcmsComDescontoCondicional(BigDecimal baseCalculo) {
        return baseCalculo.add(tributos.getDesconto());
    }

    private BigDecimal calculaIcmsComDescontoIncondicional(BigDecimal baseCalculo) {
        return baseCalculo.subtract(tributos.getDesconto());
    }

}
