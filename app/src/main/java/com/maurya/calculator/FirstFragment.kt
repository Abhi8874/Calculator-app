package com.maurya.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.maurya.calculator.databinding.FragmentFirstBinding
import org.mariuszgromada.math.mxparser.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.textViewEqual.setOnClickListener {
//            if (binding.showNum.text.toString() == "5522"){
//                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//            }
//        }



        binding.showNum.setOnClickListener {
            getActivity()!!.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

            if (getString(R.string.display) == binding.showNum.text.toString()) {
                binding.showNum.setText("")
            }


        }

        binding.textView0.setOnClickListener {
            updateText("0")
        }
        binding.textView1.setOnClickListener {
            updateText("1")
        }
        binding.textView2.setOnClickListener {
            updateText("2")
        }
        binding.textView3.setOnClickListener {
            updateText("3")
        }
        binding.textView4.setOnClickListener {
            updateText("4")
        }
        binding.textView5.setOnClickListener {
            updateText("5")
        }
        binding.textView6.setOnClickListener {
            updateText("6")
        }
        binding.textView7.setOnClickListener {
            updateText("7")
        }
        binding.textView8.setOnClickListener {
            updateText("8")
        }
        binding.textView9.setOnClickListener {
            updateText("9")
        }
        binding.textViewAC.setOnClickListener {
            binding.showNum.setText("")
            binding.resultTv.text = ""
        }
        binding.textViewAdd.setOnClickListener {
            var a = binding.showNum.text
            updateText("+")
//            binding.showNum.setText("")
        }
        binding.textViewSub.setOnClickListener {
            updateText("-")
        }
        binding.textViewMulti.setOnClickListener {
            updateText("×")
        }
        binding.textViewDiv.setOnClickListener {
            updateText("÷")
        }
        binding.textView00.setOnClickListener {
            updateText("00")
            val cursorPos = binding.showNum.selectionStart
            binding.showNum.setSelection(cursorPos + 1)
        }
        binding.textViewPoint.setOnClickListener {
            updateText(".")
        }
        binding.textViewPercent.setOnClickListener {
            updateText("%")
        }

        binding.textViewC.setOnClickListener {
            if (binding.showNum.text.toString().trim().isNotEmpty()) {
                if (binding.showNum.text.toString()
                        .charAt(binding.showNum.text.toString().trim().length - 1) !== '-'
                ) {
                    val result: String = binding.showNum.text.toString()
                        .substring(0, binding.showNum.text.toString().length - 1)
                    binding.showNum.setText(result)
                    binding.showNum.setSelection(result.length)
                }
            }

        }

        binding.textViewEqual.setOnClickListener {
            if (binding.showNum.text.toString() == "5522"){
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }

            if (binding.showNum.text.isNullOrEmpty()) {
                binding.resultTv.text = ""
            } else {
                var result: String = binding.showNum.text.toString()

                result = result.replace("÷", "/")
                result = result.replace("×", "*")

                val exp = Expression(result)
                binding.resultTv.text = exp.calculate().toString()

            }
        }
    }

    private fun updateText(setStr: String) {
        val oldStr: String = binding.showNum.text.toString()
        val cursorPos = binding.showNum.selectionStart
        val leftStr: String = oldStr.substring(0, cursorPos)
        val rightStr: String = oldStr.substring(cursorPos)
        binding.showNum.setText(String.format("%S%S%S", leftStr, setStr, rightStr))
        binding.showNum.setSelection(cursorPos + 1)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

private fun String.charAt(i: Int): Char {
    return this[0 + i]
}
