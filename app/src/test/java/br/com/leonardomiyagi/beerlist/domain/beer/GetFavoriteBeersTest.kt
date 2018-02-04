package br.com.leonardomiyagi.beerlist.domain.beer

import br.com.leonardomiyagi.beerlist.base.BaseTest
import br.com.leonardomiyagi.beerlist.data.utils.RequestException
import br.com.leonardomiyagi.beerlist.domain.model.Beer
import br.com.leonardomiyagi.beerlist.domain.repository.BeerRepository
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by lmiyagi on 2/4/18.
 */
@RunWith(MockitoJUnitRunner::class)
class GetFavoriteBeersTest : BaseTest() {

    @Mock
    public lateinit var beerRepository: BeerRepository
    public lateinit var expectedBeers: MutableList<Beer>
    public lateinit var getFavoriteBeers: GetFavoriteBeers

    @Before
    fun setUp() {
        setupExpectedBeers()

        getFavoriteBeers = GetFavoriteBeers(beerRepository)
    }

    @Test
    fun getStoredBeers_success() {
        Mockito.`when`(beerRepository.getStoredBeers()).thenReturn(Single.just(expectedBeers))

        val testObserver = TestObserver<List<Beer>>()
        getFavoriteBeers.execute().subscribe(testObserver)

        testObserver.assertComplete()
        testObserver.assertValues(expectedBeers)
    }

    @Test
    fun getStoredBeers_error() {
        val errorMessage = "Internal Server Error"
        Mockito.`when`(beerRepository.getStoredBeers()).thenReturn(Single.error(RequestException.httpError(500, errorMessage)))

        val testObserver = TestObserver<List<Beer>>()
        getFavoriteBeers.execute().subscribe(testObserver)

        testObserver.assertNotComplete()
        testObserver.assertError(RequestException::class.java)
        testObserver.assertErrorMessage(errorMessage)
    }

    private fun setupExpectedBeers() {
        val beer = Mockito.mock(Beer::class.java)

        expectedBeers = ArrayList()
        expectedBeers.add(beer)
    }
}