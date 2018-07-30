package com.scalabigdata.syntax


/**
  * only need to implement minus and equals and everything works =) !!!
  */
trait CustomComparator {
  def < (that: Any): Boolean
  def <= (that: Any): Boolean = this < that || this == that
  def > (that: Any): Boolean = !(this <= that)
  def >= (that: Any): Boolean = !(this < that)
}

class CustomDate(y: Int, m: Int, d: Int) extends CustomComparator {

  // getters
  def year = y
  def month = m
  def day = d

  override def toString: String = {
    year + "-" + month + "-" + day
  }

  override def equals(that: Any): Boolean =
    that.isInstanceOf[CustomDate] && {
      val o = that.asInstanceOf[CustomDate]
      o.year == this.year && o.month == this.month && o.day == this.day
    }

  override def <(that: Any): Boolean = {
    if (!that.isInstanceOf[CustomDate])
      sys.error("cannot compare " + that + "with CustomDate")

    val o = that.asInstanceOf[CustomDate]

    o.year < year ||
    (o.year == year && (o.month < month ||
                       (o.month == month && o.day < day)))
  }
}
