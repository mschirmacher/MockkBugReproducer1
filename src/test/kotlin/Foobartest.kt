import assertk.assertThat
import assertk.assertions.isEqualTo
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

class Outer(val inner: Inner) {
    fun someMethod(value: String): Boolean = TODO()
}

class Inner(val value: String)

class Foobartest {

    @Test
    fun testOuterInner() {
        val inner = Inner("test")
        val outer = mockk<Outer>()

        every { outer.inner } returns inner
        every { outer.someMethod(outer.inner.value) } returns true

        assertThat(outer.inner.value).isEqualTo("test")
    }
}
