package com.gabor.gistlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.gabor.gistlist.models.Item
import org.junit.*
import org.junit.rules.TestRule

class ViewModelTest {

    private lateinit var repository: Repository
    private lateinit var viewModel: FeedViewModel
    private lateinit var apiObserver: Observer<List<Item>>
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    companion object {
        @ClassRule
        @JvmField
        val testCoroutineRule = TestRxRule()
    }

    @Before
    fun setup() {
        repository = Repository(FakeApi())
        viewModel = FeedViewModel(repository)
    }

    @Test
    fun getUserInfo_returns_true() {
        apiObserver = Observer {
            Assert.assertTrue(it.size > 1)
        }
        viewModel.state.observeForever(apiObserver)
        viewModel.loadFeed()
        viewModel.state.removeObserver(apiObserver)
    }
}