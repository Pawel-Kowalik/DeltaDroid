package com.pawel14157.deltadroid

import com.pawel14157.deltadroid.service.ResolveService
import org.junit.Assert
import org.junit.Test

class ResolveServiceTest {
    private val service = ResolveService()

    @Test
    fun shouldResolveDelta() {
        //given
        val a = -2.0
        val b = 5.0
        val c = 3.0

        // when
        val result = service.resolveDelta(a, b, c)

        //then
        Assert.assertTrue(49.0.equals(result))
    }

    @Test
    fun shouldResolveX1() {
        //given
        val a = -2.0
        val b = 5.0
        val sqrtDelta = 7.0

        // when
        val result = service.deltaIsGreaterThenZeroX1(a, b, sqrtDelta)

        //then
        Assert.assertTrue((-0.5).equals(result))
    }

    @Test
    fun shouldResolveX2() {
        //given
        val a = -2.0
        val b = 5.0
        val sqrtDelta = 7.0

        // when
        val result = service.deltaIsGreaterThenZeroX2(a, b, sqrtDelta)

        //then
        Assert.assertTrue(3.0.equals(result))
    }

    @Test
    fun shouldResolveXForDeltaEqualsZero() {
        //given
        val a = 4.0
        val b = 4.0

        // when
        val result = service.deltaIsEqualsZero(a, b)

        //then
        Assert.assertTrue((-0.5).equals(result))
    }

    @Test
    fun shouldResolveXTopP() {
        //given
        val a = 4.0
        val b = 4.0

        // when
        val result = service.resolveTopP(a, b)

        //then
        Assert.assertTrue((-0.5).equals(result))
    }

    @Test
    fun shouldResolveTopQ() {
        //given
        val a = -2.0
        val delta = 49.0

        // when
        val result = service.resolveTopQ(a, delta)

        //then
        Assert.assertTrue(6.125.equals(result))
    }

}