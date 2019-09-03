package com.tdd.shop.domain

import com.tdd.shop.domain.ProductTypeEnum.ProductType

case class BasketProduct(`type`: ProductType,
                         price: BigDecimal = BigDecimal(0.0),
                         total: BigDecimal = BigDecimal(0.0),
                         quantity: Int = 1) {
  def calculateTotal = this.copy(total = quantity * price)
}
