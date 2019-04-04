package com.example.rappiinterview.ui.activity.main

import com.example.rappiinterview.RxTrampolineSchedulerRule
import com.example.rappiinterview.infrastructure.util.MovieCategory.POPULAR
import com.example.rappiinterview.infrastructure.util.MovieCategory.UPCOMING
import com.example.rappiinterview.infrastructure.util.MovieCategory.TOP_RATED
import com.example.rappiinterview.infrastructure.util.MovieCategoryUtils
import com.example.rappiinterview.infrastructure.repository.interfaces.MoviesRepository
import com.example.rappiinterview.infrastructure.rest.RestConstants
import com.example.rappiinterview.infrastructure.manager.interfaces.MoviesManager
import com.example.rappiinterview.domain.model.Item
import com.example.rappiinterview.infrastructure.networking.services.responses.MoviesServiceResponse
import com.example.rappiinterview.infrastructure.networking.services.responses.SelectedMoviesServiceResponse
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Single
import io.realm.RealmList
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import retrofit2.Response

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class MainPresenterTest {

    @get:Rule
    val testSchedulerRule = RxTrampolineSchedulerRule()
    @get:Rule
    val rule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var view: MainContract.View
    @Mock
    lateinit var moviesManager: MoviesManager
    @Mock
    lateinit var moviesRepository: MoviesRepository
    @Mock
    lateinit var movieCategoryUtils: MovieCategoryUtils
    @Mock
    lateinit var generalMoviesResponse: Response<MoviesServiceResponse>
    @Mock
    lateinit var serviceResponse: MoviesServiceResponse
    @Mock
    lateinit var selectedMoviesServiceResponse: SelectedMoviesServiceResponse
    @Mock
    lateinit var selectedMoviesResponse: Response<SelectedMoviesServiceResponse>
    @Mock
    lateinit var realmList: RealmList<Int>
    @InjectMocks
    lateinit var presenter: MainPresenter

    private lateinit var itemGeneral: Item
    private lateinit var itemPopularMovies: Item
    private lateinit var itemTopRatedMovies: Item
    private lateinit var itemUpcomingMovies: Item

    private lateinit var itemsList: List<Item>
    @Before
    fun setUp() {
        itemGeneral = Item(
            false, "samplepath.jpg", realmList, 123, "video", "En-US",
            "original_title", "overview", 5.0, "postersample.jpg", "12/12/2019", "title", false, 7.0, 5000,
            "general"
        )
        itemPopularMovies = Item(
            false, "samplepath.jpg", realmList, 123, "video", "En-US",
            "original_title", "overview", 5.0, "postersample.jpg", "12/12/2019", "title", false, 7.0, 5000,
            "popular"
        )
        itemTopRatedMovies = Item(
            false, "samplepath.jpg", realmList, 123, "video", "En-US",
            "original_title", "overview", 5.0, "postersample.jpg", "12/12/2019", "title", false, 7.0, 5000,
            "top_rated"
        )
        itemUpcomingMovies = Item(
            false, "samplepath.jpg", realmList, 123, "video", "En-US",
            "original_title", "overview", 5.0, "postersample.jpg", "12/12/2019", "title", false, 7.0, 5000,
            "upcoming"
        )
        itemsList = arrayListOf(itemGeneral, itemPopularMovies, itemTopRatedMovies, itemUpcomingMovies)
        given(serviceResponse.items).willReturn(itemsList)
        given(selectedMoviesServiceResponse.results).willReturn(itemsList)
        given(generalMoviesResponse.body()).willReturn(serviceResponse)
        given(selectedMoviesResponse.body()).willReturn(selectedMoviesServiceResponse)
        given(moviesManager.getMovies(1, 1, RestConstants.API_KEY_V3)).willReturn(Single.just(generalMoviesResponse))
    }

    @Test
    fun refreshMovies() {
        given(generalMoviesResponse.code()).willReturn(200)
        given(generalMoviesResponse.body()).willReturn(serviceResponse)
        given(generalMoviesResponse.isSuccessful).willReturn(true)

        presenter.refreshMovies()
        verify(view).showProgress()
        verify(moviesRepository).getMovies()
    }

    @Test
    fun onMovieClicked() {
        presenter.onMovieClicked(itemPopularMovies)
        verify(view).showOnMovieClickedMessage(itemPopularMovies)
    }

    @Test
    fun onMovieLongClicked() {
        presenter.onMovieLongClicked(itemPopularMovies)
        verify(view).showMovieDetail(itemPopularMovies)
    }

    @Test
    fun onPopularMoviesButtonClicked() {
        given(
            moviesManager.getPopularMovies(
                1,
                RestConstants.API_KEY_V3
            )
        ).willReturn(Single.just(selectedMoviesResponse))
        presenter.onPopularMoviesButtonClicked()
        verify(view).showProgress()
        verify(moviesRepository).getMoviesWithCategory(POPULAR)
    }

    @Test
    fun onDestroy() {
        presenter.onDestroy()
        verify(moviesRepository).close()
    }

    @Test
    fun onPopularFABClicked() {
        presenter.onPopularFABClicked()
        verify(moviesRepository).getMoviesWithCategory(POPULAR)
    }

    @Test
    fun onTopRatedFABClicked() {
        presenter.onTopRatedFABClicked()
        verify(moviesRepository).getMoviesWithCategory(TOP_RATED)
    }

    @Test
    fun onUpcomingFABClicked() {
        presenter.onUpcomingFABClicked()
        verify(moviesRepository).getMoviesWithCategory(UPCOMING)
    }
}