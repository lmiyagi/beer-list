package br.com.leonardomiyagi.beerlist.domain.beer

import br.com.leonardomiyagi.beerlist.data.utils.RequestException
import br.com.leonardomiyagi.beerlist.domain.model.Beer
import br.com.leonardomiyagi.beerlist.domain.repository.BeerRepository
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by lmiyagi on 01/02/18.
 */
@RunWith(MockitoJUnitRunner::class)
class GetBeersTest {

    @Mock
    public lateinit var beerRepository: BeerRepository
    public lateinit var expectedBeers: MutableList<Beer>
    public lateinit var getBeers: GetBeers

    @Before
    fun setUp() {
        setupExpectedBeers()

        getBeers = GetBeers(beerRepository)
    }

    @Test
    fun getBeers_successful() {
        `when`(beerRepository.getBeers()).thenReturn(Single.just(expectedBeers))

        val testObserver = TestObserver<List<Beer>>()
        getBeers.execute().subscribe(testObserver)

        testObserver.assertComplete()
        testObserver.assertValues(expectedBeers)
    }

    @Test
    fun getBeer_error() {
        val errorMessage = "Not Found"
        `when`(beerRepository.getBeers()).thenReturn(Single.error(RequestException.httpError(404, errorMessage)))

        val testObserver = TestObserver<List<Beer>>()
        getBeers.execute().subscribe(testObserver)

        testObserver.assertNotComplete()
        testObserver.assertError(RequestException::class.java)
        testObserver.assertErrorMessage(errorMessage)
    }

    private fun setupExpectedBeers() {
        val beer = mock(Beer::class.java)

        expectedBeers = ArrayList()
        expectedBeers.add(beer)
    }
}