package com.tdd.shop.domain

import com.tdd.shop.domain.ProductTypeEnum.ProductType

case class Product(name: ProductType, price: BigDecimal)
