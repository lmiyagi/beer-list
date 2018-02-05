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
 * Created by lmiyagi on 2/5/18.
 */
@RunWith(MockitoJUnitRunner::class)
class DeleteBeerTest : BaseTest() {

    @Mock
    public lateinit var beerRepository: BeerRepository
    @Mock
    public lateinit var mockBeer: Beer

    public lateinit var deleteBeer: DeleteBeer

    @Before
    fun setUp() {
        deleteBeer = DeleteBeer(beerRepository)
    }

    @Test
    fun deleteBeer_success() {
        `when`(beerRepository.deleteBeer(mockBeer)).thenReturn(Completable.complete())

        val testObserver = deleteBeer.execute(mockBeer).test()

        testObserver.assertComplete()
        testObserver.assertNoErrors()
    }

    @Test
    fun deleteBeer_error() {
        `when`(beerRepository.deleteBeer(mockBeer)).thenReturn(Completable.error(mock(Throwable::class.java)))

        val testObserver = deleteBeer.execute(mockBeer).test()

        testObserver.assertNotComplete()
        testObserver.assertError(Throwable::class.java)
    }
}