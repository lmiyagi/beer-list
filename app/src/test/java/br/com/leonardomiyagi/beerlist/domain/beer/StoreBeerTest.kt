package br.com.leonardomiyagi.beerlist.domain.beer

import br.com.leonardomiyagi.beerlist.base.BaseTest
import br.com.leonardomiyagi.beerlist.domain.model.Beer
import br.com.leonardomiyagi.beerlist.domain.repository.BeerRepository
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by lmiyagi on 2/4/18.
 */
@RunWith(MockitoJUnitRunner::class)
class StoreBeerTest : BaseTest() {

    @Mock
    lateinit var beerRepository: BeerRepository
    @Mock
    lateinit var mockBeer: Beer

    private lateinit var storeBeer: StoreBeer

    @Before
    fun setUp() {
        storeBeer = StoreBeer(beerRepository)
    }

    @Test
    fun storeBeer_success() {
        `when`(beerRepository.storeBeer(mockBeer)).thenReturn(Completable.complete())

        val testObserver = storeBeer.execute(mockBeer).test()

        testObserver.assertComplete()
        testObserver.assertNoErrors()
    }

    @Test
    fun storeBeer_error() {
        val mockError = mock(Throwable::class.java)
        `when`(beerRepository.storeBeer(mockBeer)).thenReturn(Completable.error(mockError))

        val testObserver = storeBeer.execute(mockBeer).test()

        testObserver.assertNotComplete()
        testObserver.assertError(mockError)
    }
}