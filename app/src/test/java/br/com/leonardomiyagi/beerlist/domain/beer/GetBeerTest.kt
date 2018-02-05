package br.com.leonardomiyagi.beerlist.domain.beer

import br.com.leonardomiyagi.beerlist.base.BaseTest
import br.com.leonardomiyagi.beerlist.data.utils.RequestException
import br.com.leonardomiyagi.beerlist.domain.model.Beer
import br.com.leonardomiyagi.beerlist.domain.repository.BeerRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by lmiyagi on 2/5/18.
 */
@RunWith(MockitoJUnitRunner::class)
class GetBeerTest : BaseTest() {

    @Mock
    public lateinit var beerRepository: BeerRepository
    @Mock
    public lateinit var mockBeer: Beer

    public lateinit var getBeer: GetBeer

    @Before
    fun setUp() {
        getBeer = GetBeer(beerRepository)
    }

    @Test
    fun getBeer_success() {
        `when`(beerRepository.getBeer(1L)).thenReturn(Single.just(mockBeer))

        val testObserver = getBeer.execute(1L).test()

        testObserver.assertComplete()
        testObserver.assertValue(mockBeer)
    }

    @Test
    fun getBeer_error() {
        val errorMessage = "Bad Request"
        `when`(beerRepository.getBeer(1L)).thenReturn(Single.error(RequestException.httpError(400, errorMessage)))

        val testObserver = getBeer.execute(1L).test()

        testObserver.assertNotComplete()
        testObserver.assertError(RequestException::class.java)
        testObserver.assertErrorMessage(errorMessage)
    }
}