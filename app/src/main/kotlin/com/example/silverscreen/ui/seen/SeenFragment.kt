package com.example.silverscreen.ui.seen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.silverscreen.databinding.FragmentSeenBinding

class SeenFragment : Fragment() {

    private var _binding: FragmentSeenBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val seenViewModel =
            ViewModelProvider(this).get(SeenViewModel::class.java)

        _binding = FragmentSeenBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textSeen
        seenViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}