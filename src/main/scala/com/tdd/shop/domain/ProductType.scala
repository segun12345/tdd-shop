package com.tdd.shop.domain

object ProductTypeEnum {
  sealed trait ProductType
  case object Apple extends ProductType
  case object Orange extends ProductType
}
