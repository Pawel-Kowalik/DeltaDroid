package com.pawel14157.deltadroid.service

class ResolveService {
    private val DOUBLE_TWO = 2.0
    private val DOUBLE_FOUR = 4.0

    fun resolveDelta(a: Double, b: Double,c: Double): Double {
        return b.times(b).minus(DOUBLE_FOUR.times(a).times(c))
    }

    fun deltaIsGreaterThenZeroX1(a: Double, b: Double, sqrtDelta: Double): Double {
        return -(b.minus(sqrtDelta).div(DOUBLE_TWO.times(a)))
    }

    fun deltaIsGreaterThenZeroX2(a: Double, b: Double, sqrtDelta: Double): Double {
        return -(b.plus(sqrtDelta).div(DOUBLE_TWO.times(a)))
    }

    fun deltaIsEqualsZero(a: Double, b: Double): Double {
        return -(b.div(DOUBLE_TWO.times(a)))
    }

    fun resolveTopP(a: Double, b: Double): Double {
        return -(b.div(DOUBLE_TWO.times(a)))
    }

    fun resolveTopQ(a: Double, delta: Double): Double {
        return -(delta.div(DOUBLE_FOUR.times(a)))
    }
}