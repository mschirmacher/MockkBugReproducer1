import assertk.assertThat
import assertk.assertions.isEqualTo
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

class Outer(val inner: Inner)

class Inner(val value: String)

class SomeService {
    fun someMethod(value: String): Boolean = TODO()
}

class Foobartest {

    @Test
    fun testOuterInner() {
        val inner = Inner("test")
        val outer = mockk<Outer>()
        val service = mockk<SomeService>()

        every { outer.inner } returns inner
        every { service.someMethod(outer.inner.value) } returns true

        assertThat(outer.inner.value).isEqualTo("test")
    }
}
