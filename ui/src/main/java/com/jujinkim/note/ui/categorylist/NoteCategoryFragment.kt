package com.jujinkim.note.ui.categorylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.jujinkim.note.core.AppState
import dagger.hilt.android.AndroidEntryPoint
import org.reduxkotlin.Store
import javax.inject.Inject

@AndroidEntryPoint
class NoteCategoryFragment : Fragment() {

    @Inject
    lateinit var store: Store<AppState>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent { NoteCategoryFragmentContent(categories = store.state.categories) }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(columnCount: Int) = NoteCategoryFragment()
    }
}